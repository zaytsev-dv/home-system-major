package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.artifactory.service.base.BaseService;

import java.util.List;

public interface TelegramQuestionService extends BaseService<TelegramQuestion, Long>
{
	TelegramQuestion findTopByExternalIdOrderByCreatedAtDesc(Long userId);
	List<TelegramQuestion> getAllByExternalIdAndType(Long externalId, String type);
}
