package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.artifactory.service.base.BaseService;

public interface TelegramUserService extends BaseService<TelegramUser, Long>
{
	TelegramUser checkWithSaveByExternalId(TelegramUser telegramUser);
	TelegramUser getByExternalId (Long externalId);
	TelegramUser getByExternalIdOrId(Long id);
	TelegramUser getByUsername(String username);
}
