package ru.home.system.major.api.autentification;

import org.springframework.web.bind.annotation.*;
import ru.home.system.major.core.dto.JWTToken;
import ru.home.system.major.core.dto.LoginDto;
import ru.home.system.major.core.dto.UserAppDTO;
import ru.home.system.major.core.security.SecurityService;


@RestController
@RequestMapping
public class AuthenticationRestController
{
	private final SecurityService securityService;

	public AuthenticationRestController(SecurityService securityService)
	{
		this.securityService = securityService;
	}

	@PostMapping("/authenticate")
	public JWTToken authorize(@RequestBody LoginDto loginDto)
	{
		return securityService.getToken(loginDto);
	}

	//TODO: вытаскивание инфы по токену из header
	@GetMapping("/me")
	public UserAppDTO getMe()
	{
		return securityService.getCurrentUser();
	}
}
