package ru.home.system.major.core.service;


import ru.home.system.major.core.adapters.base.NotificationAdapter;
import ru.home.system.major.core.dto.NotificationCreateDTO;
import ru.home.system.major.core.dto.NotificationCreateDelayedDTO;

public interface NotificationService
{
	void sendMsg(NotificationCreateDTO notificationCreateDTO);
	void sendMsgDelayed(NotificationCreateDelayedDTO notificationCreateDTO);
	NotificationAdapter getNotificationAdapter(String type);
}
