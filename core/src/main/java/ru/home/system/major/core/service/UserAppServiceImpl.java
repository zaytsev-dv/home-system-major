package ru.home.system.major.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.UserApp;
import ru.home.system.major.core.repository.UserAppRepository;

@Service
@Slf4j
public class UserAppServiceImpl extends BaseSqlServiceImpl<UserApp, Long> implements UserAppService
{
	private final UserAppRepository userAppRepository;

	public UserAppServiceImpl(UserAppRepository userAppRepository)
	{
		this.userAppRepository = userAppRepository;
	}

	@Override
	protected BaseSqlRepository<UserApp, Long> getRepository()
	{
		return userAppRepository;
	}

	@Override
	public UserApp findOneWithAuthoritiesByUsername(String username)
	{
		return userAppRepository.findOneWithAuthoritiesByUsername(username).orElse(null);
	}
}
