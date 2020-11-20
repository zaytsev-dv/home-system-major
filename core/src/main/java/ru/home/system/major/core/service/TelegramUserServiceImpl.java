package ru.home.system.major.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.repository.TelegramUserRepository;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;

@Service
@Slf4j
public class TelegramUserServiceImpl extends BaseSqlServiceImpl<TelegramUser, Long> implements TelegramUserService
{
	private final TelegramUserRepository telegramUserRepository;

	public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository)
	{
		this.telegramUserRepository = telegramUserRepository;
	}

	@Override
	protected BaseSqlRepository<TelegramUser, Long> getRepository()
	{
		return telegramUserRepository;
	}

	@Override
	public TelegramUser checkWithSaveByExternalId(TelegramUser telegramUser)
	{
		TelegramUser existed = telegramUserRepository.getByExternalId(telegramUser.getExternalId());
		if (existed == null)
		{
			return telegramUserRepository.save(telegramUser);
		}
		else
		{
			log.info("user with externalId: {} already saved", telegramUser.getExternalId());
		}

		return existed;
	}

	@Override
	public TelegramUser getByExternalId(Long externalId)
	{
		return telegramUserRepository.getByExternalId(externalId);
	}

	@Override
	public TelegramUser getByExternalIdOrId(Long id)
	{
		return telegramUserRepository.getByExternalIdOrId(id, id);
	}

	@Override
	public TelegramUser getByUsername(String username)
	{
		return telegramUserRepository.getByUsername(username);
	}
}
