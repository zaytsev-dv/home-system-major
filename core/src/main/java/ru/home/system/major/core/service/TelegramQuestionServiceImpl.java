package ru.home.system.major.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.repository.TelegramQuestionRepository;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;

import java.util.List;

@Service
@Slf4j
public class TelegramQuestionServiceImpl extends BaseSqlServiceImpl<TelegramQuestion, Long> implements TelegramQuestionService
{
	private final TelegramQuestionRepository telegramQuestionRepository;

	public TelegramQuestionServiceImpl(TelegramQuestionRepository telegramQuestionRepository)
	{
		this.telegramQuestionRepository = telegramQuestionRepository;
	}

	@Override
	protected BaseSqlRepository<TelegramQuestion, Long> getRepository()
	{
		return telegramQuestionRepository;
	}


	@Override
	public TelegramQuestion findTopByExternalIdOrderByCreatedAtDesc(Long userId)
	{
		return telegramQuestionRepository.findTopByExternalIdOrderByCreatedAtDesc(userId);
	}

	@Override
	public List<TelegramQuestion> getAllByExternalIdAndType(Long externalId, String type)
	{
		return telegramQuestionRepository.getAllByExternalIdAndType(externalId, type);
	}
}
