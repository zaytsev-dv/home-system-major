package ru.home.system.major.api.notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.home.system.major.core.dto.NotesCreate;
import ru.home.system.major.core.dto.NotesDto;
import ru.home.system.major.core.service.notes.NotesService;
import ru.home.system.major.core.service.util.TryCatchService;

import java.util.HashMap;
import java.util.List;

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

	@GetMapping
	public List<NotesDto> getWithFilter(@RequestParam String userId, @RequestParam(required = false) String category)
	{
		return TryCatchService.runReturnedTraced(
				() -> notesService.getAllByTelegramUserDTOS(userId, category),
				"getWithFilter",
				new HashMap<String, String>()
				{
					{
						put("userId", userId);
					}
				}
		);
	}
}
