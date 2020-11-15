package ru.home.system.major.core.repository;


import ru.home.system.major.core.domain.Notes;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.repository.base.BaseSqlRepository;

import java.util.List;

public interface NotesRepository extends BaseSqlRepository<Notes, Long>
{
	List<Notes> getAllByTelegramUser(TelegramUser telegramUser);
}
