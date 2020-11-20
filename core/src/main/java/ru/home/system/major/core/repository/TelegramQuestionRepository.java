package ru.home.system.major.core.repository;


import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.domain.TelegramQuestion;

import java.util.List;

public interface TelegramQuestionRepository extends BaseSqlRepository<TelegramQuestion, Long>
{
	TelegramQuestion findTopByExternalIdOrderByCreatedAtDesc(Long userId);

	List<TelegramQuestion> getAllByExternalIdAndType(Long externalId, String type);
}
