package ru.home.system.major.core.service.notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.artifactory.exceptions.CustomNotFoundException;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;
import ru.home.system.major.core.domain.NotesCategory;
import ru.home.system.major.core.repository.NotesCategoryRepository;

import java.util.Optional;

@Service
@Slf4j
public class NotesCategoryServiceImpl extends BaseSqlServiceImpl<NotesCategory, Long> implements NotesCategoryService
{
	private final NotesCategoryRepository notesCategoryRepository;

	public NotesCategoryServiceImpl(NotesCategoryRepository notesCategoryRepository)
	{
		this.notesCategoryRepository = notesCategoryRepository;
	}

	@Override
	protected BaseSqlRepository<NotesCategory, Long> getRepository()
	{
		return notesCategoryRepository;
	}

	@Override
	public NotesCategory getByValueIgnoreCase(String category)
	{
		return Optional.ofNullable(notesCategoryRepository.getByValueIgnoreCase(category))
				.orElseThrow(() -> new CustomNotFoundException("Category with name", category));
	}
}
