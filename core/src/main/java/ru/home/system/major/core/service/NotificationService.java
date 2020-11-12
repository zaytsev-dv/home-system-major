package ru.home.system.major.core.service;


import ru.home.system.major.core.dto.NotificationCreateDTO;

public interface NotificationService
{
	void sendMsg(NotificationCreateDTO notificationCreateDTO);
}
