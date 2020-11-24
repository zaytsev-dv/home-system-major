package ru.home.system.major.core.security;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.home.system.major.core.domain.UserApp;
import ru.home.system.major.core.exceptions.UserNotActivatedException;
import ru.home.system.major.core.repository.UserAppRepository;

import java.util.List;
import java.util.stream.Collectors;


@Component("userDetailsService")
@Slf4j
public class UserModelDetailsService implements UserDetailsService
{
	private final UserAppRepository userAppRepository;

	public UserModelDetailsService(UserAppRepository userAppRepository)
	{
		this.userAppRepository = userAppRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login)
	{
		log.debug("Authenticating user '{}'", login);
		if (new EmailValidator().isValid(login, null))
		{
			return userAppRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
					.map(userApp -> createSpringSecurityUser(login, userApp))
					.orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
		}

		String lowercaseLogin = login.toLowerCase();
		return userAppRepository.findOneWithAuthoritiesByUsername(lowercaseLogin)
				.map(userApp -> createSpringSecurityUser(lowercaseLogin, userApp))
				.orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

	}

	private User createSpringSecurityUser(String lowercaseLogin, UserApp userApp)
	{
		if (!userApp.isActivated())
		{
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
		}
		List<GrantedAuthority> grantedAuthorities = userApp.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
		return new User(userApp.getUsername(),
				userApp.getPassword(),
				grantedAuthorities);
	}
}
