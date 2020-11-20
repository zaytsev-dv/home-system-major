package ru.home.system.major.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.domain.TelegramCommandKeys;
import ru.home.system.major.core.repository.TelegramCommandKeysRepository;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TelegramCommandKeysServiceImpl extends BaseSqlServiceImpl<TelegramCommandKeys, Long> implements TelegramCommandKeysService
{
	private final TelegramCommandKeysRepository telegramCommandKeysRepository;

	public TelegramCommandKeysServiceImpl(TelegramCommandKeysRepository telegramCommandKeysRepository)
	{
		this.telegramCommandKeysRepository = telegramCommandKeysRepository;
	}

	@Override
	protected BaseSqlRepository<TelegramCommandKeys, Long> getRepository()
	{
		return telegramCommandKeysRepository;
	}

	@Cacheable("allTelegramCommandKeys")
	@Override
	public Map<TelegramCommandMapKey, TelegramCommandKeys> findAllCacheable()
	{
		return telegramCommandKeysRepository.findAll().stream()
				.collect(Collectors.toMap(o -> new TelegramCommandMapKey(o.getName(), o.getType()), o -> o));
	}
}
