package ru.home.system.major.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.system.artifactory.service.base.BaseService;
import ru.home.system.major.core.domain.UserApp;

@Service
@Transactional
public interface UserAppService extends BaseService<UserApp, Long>
{
	UserApp findOneWithAuthoritiesByUsername(String username);
}
