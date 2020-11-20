package ru.home.system.major.core.service.notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.domain.Notes;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.dto.NotesCreate;
import ru.home.system.major.core.exceptions.TelegramUserNotFoundException;
import ru.home.system.major.core.repository.NotesRepository;
import ru.home.system.artifactory.repository.base.BaseSqlRepository;
import ru.home.system.major.core.service.TelegramUserService;
import ru.home.system.artifactory.service.base.BaseSqlServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NotesServiceImpl extends BaseSqlServiceImpl<Notes, Long> implements NotesService
{
	private final NotesRepository notesRepository;
	private final TelegramUserService telegramUserService;

	public NotesServiceImpl(NotesRepository notesRepository, TelegramUserService telegramUserService)
	{
		this.notesRepository = notesRepository;
		this.telegramUserService = telegramUserService;
	}

	@Override
	protected BaseSqlRepository<Notes, Long> getRepository()
	{
		return notesRepository;
	}

	@Override
	public void create(NotesCreate body)
	{
		TelegramUser telegramUser = Optional.ofNullable(telegramUserService.getByExternalIdOrId(body.getTelegramUserId()))
				.orElseThrow(() -> new TelegramUserNotFoundException(String.valueOf(body.getTelegramUserId())));

		notesRepository.save(Notes.builder()
				.value(body.getValue())
				.description(body.getDescription())
				.telegramUser(telegramUser)
				.build());
	}

	@Override
	public List<Notes> getAllByTelegramUser(TelegramUser telegramUser)
	{
		return notesRepository.getAllByTelegramUser(telegramUser);
	}

	@Override
	public List<Notes> getAllByTelegramUser(Long externalId)
	{
		return notesRepository.getAllByTelegramUser(telegramUserService.getByExternalId(externalId));
	}
}
