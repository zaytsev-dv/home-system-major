package ru.home.system.major.core.repository;


import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.repository.base.BaseSqlRepository;

import java.util.List;

public interface TelegramQuestionRepository extends BaseSqlRepository<TelegramQuestion, Long>
{
	TelegramQuestion findTopByExternalIdOrderByCreatedAtDesc(Long userId);

	List<TelegramQuestion> getAllByExternalIdAndType(Long externalId, String type);
}
