package ru.home.system.major.api.notifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.system.major.core.service.NotificationService;
import ru.home.system.major.core.dto.NotificationCreateDTO;

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
		notificationService.sendMsg(notificationCreateDTO);
	}
}
