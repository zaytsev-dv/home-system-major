package ru.home.system.major.core.repository;


import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.repository.base.BaseSqlRepository;

public interface TelegramUserRepository extends BaseSqlRepository<TelegramUser, Long>
{
	TelegramUser getByExternalId(Long externalId);
	TelegramUser getByExternalIdOrId(Long externalId, Long id);
	TelegramUser getByUsername(String username);
}
