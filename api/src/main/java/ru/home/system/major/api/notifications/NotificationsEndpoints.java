package ru.home.system.major.api.notifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.home.system.major.core.dto.NotificationCreateDTO;
import ru.home.system.major.core.dto.NotificationCreateDelayedDTO;
import ru.home.system.major.core.service.NotificationService;
import ru.home.system.major.core.service.util.TryCatchService;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationsEndpoints
{
	private final NotificationService notificationService;

	public NotificationsEndpoints(NotificationService notificationService)
	{
		this.notificationService = notificationService;
	}

	@PostMapping
	public void send(@RequestBody NotificationCreateDTO notificationCreateDTO)
	{
		TryCatchService.runVoid(() -> notificationService.sendMsg(notificationCreateDTO));
	}

	@PostMapping("/delay")
	public void sendDelayed(@RequestBody NotificationCreateDelayedDTO notificationCreateDTO)
	{
		TryCatchService.runVoid(() -> notificationService.sendMsgDelayed(notificationCreateDTO));
	}

	//TODO: create only for test
	@GetMapping("/test")
	public String test()
	{
		return TryCatchService.runReturned(() -> "String");
	}
}
