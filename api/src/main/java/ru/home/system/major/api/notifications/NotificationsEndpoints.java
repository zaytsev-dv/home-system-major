package ru.home.system.major.api.notifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.home.system.artifactory.annotations.ParamConstraint;
import ru.home.system.major.core.dto.NotificationCreateDTO;
import ru.home.system.major.core.dto.NotificationCreateDelayedDTO;
import ru.home.system.major.core.service.NotificationService;
import ru.home.system.major.core.service.util.TryCatchService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/notifications")
@Slf4j
@Validated
public class NotificationsEndpoints
{
	private final NotificationService notificationService;

	public NotificationsEndpoints(NotificationService notificationService)
	{
		this.notificationService = notificationService;
	}

	@PostMapping
	public void send(@Valid @RequestBody NotificationCreateDTO notificationCreateDTO)
	{
		TryCatchService.runVoid(() -> notificationService.sendMsg(notificationCreateDTO));
	}

	@PostMapping("/delay")
	public void sendDelayed(@Valid @RequestBody NotificationCreateDelayedDTO notificationCreateDTO)
	{
		TryCatchService.runVoid(() -> notificationService.sendMsgDelayed(notificationCreateDTO));
	}

	//TODO: create only for test
	@GetMapping("/test{id}")
	public String test(@PathVariable
					   @NotEmpty(message = "variable: \"id\" can not be empty")
					   @NotNull(message = "variable: \"id\" can not be null") String id)
	{
		return TryCatchService.runReturned(() -> id);
	}

	@GetMapping("/test/2")
	public String test2(
			@Valid
			@ParamConstraint(conditionType = "regex", message = "error")
			@RequestParam String id)
	{
		return TryCatchService.runReturned(() -> id);
	}
}
