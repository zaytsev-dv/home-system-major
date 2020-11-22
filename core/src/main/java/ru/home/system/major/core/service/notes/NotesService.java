package ru.home.system.major.core.service.notes;


import ru.home.system.major.core.domain.Notes;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.dto.NotesCreate;
import ru.home.system.artifactory.service.base.BaseService;
import ru.home.system.major.core.dto.NotesDto;

import java.util.List;

public interface NotesService extends BaseService<Notes, Long>
{
	void create(NotesCreate body);
	List<Notes> getAllByTelegramUser(TelegramUser telegramUser);
	List<Notes> getAllByTelegramUser(Long externalId);
	List<NotesDto> getAllByTelegramUserDTOS(String userId);
}
