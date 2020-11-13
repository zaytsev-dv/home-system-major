package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.service.base.BaseService;

public interface TelegramUserService extends BaseService<TelegramUser, Long>
{
	void checkWithSaveByExternalId(TelegramUser telegramUser);
}