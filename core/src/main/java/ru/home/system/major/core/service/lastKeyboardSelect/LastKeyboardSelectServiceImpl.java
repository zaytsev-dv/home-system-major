package ru.home.system.major.core.service.lastKeyboardSelect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.LastKeyboardSelect;
import ru.home.system.major.core.repository.LastKeyboardSelectRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LastKeyboardSelectServiceImpl extends BaseSqlServiceImpl<LastKeyboardSelect, Long> implements LastKeyboardSelectService
{
	private final LastKeyboardSelectRepository lastKeyboardSelectRepository;

	public LastKeyboardSelectServiceImpl(LastKeyboardSelectRepository lastKeyboardSelectRepository)
	{
		this.lastKeyboardSelectRepository = lastKeyboardSelectRepository;
	}

	@Override
	protected BaseSqlRepository<LastKeyboardSelect, Long> getRepository()
	{
		return lastKeyboardSelectRepository;
	}

	@Override
	public LastKeyboardSelect findTopByExternalIdOrderByCreatedAtDesc(Long userId)
	{
		return lastKeyboardSelectRepository.findTopByExternalIdOrderByCreatedAtDesc(userId);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void create(Update update, String type)
	{
		LastKeyboardSelect lastKeyboardSelect = LastKeyboardSelect.builder()
				.type(type)
				.value(update.getCallbackQuery().getData())
				.externalId(Long.valueOf(update.getCallbackQuery().getFrom().getId()))
				.createdAt(LocalDateTime.now())
				.build();
		this.save(lastKeyboardSelect);
	}
}
