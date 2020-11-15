package ru.home.system.major.api.notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.system.major.core.dto.NotesCreate;
import ru.home.system.major.core.service.notes.NotesService;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NotesEndpoints
{
	private final NotesService notesService;

	public NotesEndpoints(NotesService notesService)
	{
		this.notesService = notesService;
	}

	@PostMapping
	public void send(@RequestBody NotesCreate body)
	{
		notesService.create(body);
	}
}
