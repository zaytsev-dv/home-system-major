package ru.home.system.major.core.service;


import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.service.base.BaseService;

import java.util.List;

public interface TelegramQuestionService extends BaseService<TelegramQuestion, Long>
{
	TelegramQuestion findTopByOrderByCreatedAtDesc(Long userId);
	List<TelegramQuestion> getAllByExternalIdAndType(Long externalId, String type);
}
