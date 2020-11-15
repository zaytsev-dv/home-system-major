package ru.home.system.major.core.service.notes;


import ru.home.system.major.core.domain.Notes;
import ru.home.system.major.core.dto.NotesCreate;
import ru.home.system.major.core.service.base.BaseService;

public interface NotesService extends BaseService<Notes, Long>
{
	void create(NotesCreate body);
}
