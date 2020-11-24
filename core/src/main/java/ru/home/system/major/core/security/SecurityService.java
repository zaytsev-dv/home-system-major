package ru.home.system.major.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.domain.UserApp;
import ru.home.system.major.core.dto.JWTToken;
import ru.home.system.major.core.dto.LoginDto;
import ru.home.system.major.core.dto.UserAppDTO;
import ru.home.system.major.core.security.jwt.TokenProvider;
import ru.home.system.major.core.service.UserAppService;

import java.util.Optional;

@Service
@Slf4j
public class SecurityService
{
	private final UserAppService userAppService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public SecurityService(
			UserAppService userAppService,
			TokenProvider tokenProvider,
			AuthenticationManagerBuilder authenticationManagerBuilder
	)
	{
		this.userAppService = userAppService;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	public JWTToken getToken(LoginDto loginDto)
	{
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new JWTToken(tokenProvider.createToken(authentication));
	}

	public UserAppDTO getCurrentUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null)
		{
			log.debug("no authentication in security context found");
			return null;
		}

		String username = null;
		if (authentication.getPrincipal() instanceof UserDetails)
		{
			UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
			username = springSecurityUser.getUsername();
		}
		else if (authentication.getPrincipal() instanceof String)
		{
			username = (String) authentication.getPrincipal();
		}

		log.debug("found username '{}' in security context", username);

		UserApp userApp = userAppService.findOneWithAuthoritiesByUsername(username);
		return Optional.ofNullable(userApp)
				.map(orm -> {
					return UserAppDTO.builder()
							.activated(orm.isActivated())
							.email(orm.getEmail())
							.firstname(orm.getFirstname())
							.lastname(orm.getLastname())
							.username(orm.getUsername())
							.build();
				})
				.orElse(null);
	}
}
