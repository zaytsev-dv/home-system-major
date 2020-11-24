package ru.home.system.major.core.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean
{
	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private final String base64Secret;
	private final long tokenValidityInMilliseconds;

	private Key key;


	public TokenProvider(
			@Value("${jwt.base64-secret}") String base64Secret,
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
	)
	{
		this.base64Secret = base64Secret;
		this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
	}

	@Override
	public void afterPropertiesSet()
	{
		byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(Authentication authentication)
	{
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);

		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(validity)
				.compact();
	}

	public Authentication getAuthentication(String token)
	{
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();

		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(String authToken)
	{
		try
		{
			Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			return true;
		}
		catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e)
		{
			log.error("Invalid JWT signature.");
		}
		catch (ExpiredJwtException e)
		{
			log.error("Expired JWT token.");
		}
		catch (UnsupportedJwtException e)
		{
			log.error("Unsupported JWT token.");
		}
		catch (IllegalArgumentException e)
		{
			log.error("JWT token compact of handler are invalid.");
		}
		return false;
	}
}
