package ru.home.system.major.core.repository;


import org.springframework.data.jpa.repository.Query;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.repository.base.BaseSqlRepository;

public interface TelegramQuestionRepository extends BaseSqlRepository<TelegramQuestion, Long>
{
	@Query("select t from TelegramQuestion t where t.externalId=?1 and t.createdAt = max(t.createdAt) order by t.id desc")
	TelegramQuestion getLastRecordByUserAndLastDate(Long userId);

	TelegramQuestion findTopByExternalIdOrderByCreatedAtDesc(Long userId);

}
