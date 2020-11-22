package ru.home.system.major.core.service.notes;


import ru.home.system.artifactory.service.base.BaseService;
import ru.home.system.major.core.domain.NotesCategory;

public interface NotesCategoryService extends BaseService<NotesCategory, Long>
{
	NotesCategory getByValueIgnoreCase(String category);
}
