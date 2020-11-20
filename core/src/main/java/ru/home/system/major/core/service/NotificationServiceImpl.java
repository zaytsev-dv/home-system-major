package ru.home.system.major.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.home.system.major.core.adapters.base.Adapter;
import ru.home.system.major.core.adapters.base.NotificationAdapter;
import ru.home.system.major.core.dto.NotificationCreateDTO;
import ru.home.system.major.core.dto.NotificationCreateDelayedDTO;
import ru.home.system.major.core.exceptions.AdapterNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ru.home.system.artifactory.service.util.DateTimeUtil.YYYY_MM_DD_T_HH_MM;
import static ru.home.system.artifactory.service.util.DateTimeUtil.strToDate;


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
		NotificationAdapter notificationAdapter = getNotificationAdapter(notificationCreateDTO.getNotificationType());

		log.debug("call notificationAdapter.sendNotification()");
		notificationAdapter.sendNotification(
				notificationCreateDTO.getSubject(),
				notificationCreateDTO.getMessage(),
				notificationCreateDTO.getRecipient()
		);
	}

	@Override
	public void sendMsgDelayed(NotificationCreateDelayedDTO notificationCreateDTO)
	{
		Date desiredDate = strToDate(notificationCreateDTO.getDateTime(), YYYY_MM_DD_T_HH_MM);
		Date now = new Date();
		long delay = desiredDate.getTime() - now.getTime();

		NotificationAdapter notificationAdapter = getNotificationAdapter(notificationCreateDTO.getNotificationType());
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.schedule(new Runnable()
		{
			@Override
			public void run()
			{
				log.info("Send msg");
				notificationAdapter.sendNotification(
						notificationCreateDTO.getSubject(),
						notificationCreateDTO.getMessage(),
						notificationCreateDTO.getRecipient()
				);
			}
		}, delay, TimeUnit.MILLISECONDS);
	}

	@Override
	public NotificationAdapter getNotificationAdapter(String type)
	{
		return adapters.stream()
				.filter(adapter -> adapter.getNotificationType().equals(Adapter.getByName(type)))
				.findFirst().orElseThrow(() -> new AdapterNotFoundException(type.toUpperCase()));
	}
}
