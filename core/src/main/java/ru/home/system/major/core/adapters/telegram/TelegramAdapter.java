package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.system.major.core.adapters.base.Adapter;
import ru.home.system.major.core.adapters.base.NotificationAdapter;

@Component
@Slf4j
public class TelegramAdapter implements NotificationAdapter
{
	private final TelegramBot telegramBot;

	public TelegramAdapter(TelegramBot telegramBot)
	{
		this.telegramBot = telegramBot;
	}

	@Override
	public void sendNotification(String subject, String message, String recipient)
	{

		SendMessage msg = new SendMessage();

		//TODO: искать пользователя из базы
		msg.setChatId("300306454");
		msg.setText(message);
		telegramBot.sendMsgToChat(msg);
	}

	@Override
	public Adapter getNotificationType()
	{
		return Adapter.TELEGRAM;
	}
}
