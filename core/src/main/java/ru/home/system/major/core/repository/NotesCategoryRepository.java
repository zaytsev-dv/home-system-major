package ru.home.system.major.core.repository;


import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.domain.NotesCategory;

public interface NotesCategoryRepository extends BaseSqlRepository<NotesCategory, Long>
{
	NotesCategory getByValueIgnoreCase(String category);
}
