package ru.home.system.major.core.adapters.base;

public interface NotificationAdapter extends AdapterInfo
{
	void sendNotification(String subject, String message, String recipient);
}
