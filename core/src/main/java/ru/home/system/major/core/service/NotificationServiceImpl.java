package ru.home.system.major.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.adapters.base.Adapter;
import ru.home.system.major.core.adapters.base.NotificationAdapter;
import ru.home.system.major.core.dto.NotificationCreateDTO;
import ru.home.system.major.core.dto.NotificationCreateDelayedDTO;
import ru.home.system.major.core.exceptions.AdapterNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService
{
	private final List<NotificationAdapter> adapters;

	public NotificationServiceImpl(List<NotificationAdapter> adapters)
	{
		this.adapters = adapters;
	}

	@Override
	public void sendMsg(NotificationCreateDTO notificationCreateDTO)
	{
		log.debug("get notificationAdapter");
		NotificationAdapter notificationAdapter = adapters.stream()
				.filter(adapter -> adapter.getNotificationType().equals(Adapter.getByName(notificationCreateDTO.getNotificationType())))
				.findFirst().orElseThrow(() -> new AdapterNotFoundException(notificationCreateDTO.getNotificationType().toUpperCase()));

		log.debug("call notificationAdapter.sendNotification()");
		notificationAdapter.sendNotification(
				notificationCreateDTO.getSubject(),
				notificationCreateDTO.getMessage(),
				notificationCreateDTO.getRecipient());
	}

	@Override
	public void sendMsgDelayed(NotificationCreateDelayedDTO notificationCreateDTO)
	{
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		LocalDateTime now = LocalDateTime.now().plusMinutes(1L);
		ScheduledFuture<?> countdown = scheduledExecutorService.schedule(new Runnable()
		{
			@Override
			public void run()
			{
				log.info("Out of time!");
			}
		}, now.getSecond(), TimeUnit.SECONDS);
	}
}
