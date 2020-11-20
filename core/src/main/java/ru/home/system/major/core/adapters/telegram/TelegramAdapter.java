package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.system.artifactory.exceptions.CustomNotFoundException;
import ru.home.system.major.core.adapters.base.Adapter;
import ru.home.system.major.core.adapters.base.NotificationAdapter;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.service.TelegramUserService;

import java.util.Optional;

@Component
@Slf4j
public class TelegramAdapter implements NotificationAdapter
{
	private final TelegramBot telegramBot;
	private final TelegramUserService telegramUserService;

	public TelegramAdapter(TelegramBot telegramBot, TelegramUserService telegramUserService)
	{
		this.telegramBot = telegramBot;
		this.telegramUserService = telegramUserService;
	}

	@Override
	public void sendNotification(String subject, String message, String recipient)
	{
		SendMessage msg = new SendMessage();
		TelegramUser telegramUser = Optional.ofNullable(
				telegramUserService.getByUsername(recipient)
		).orElseThrow(
				() -> new CustomNotFoundException("Telegram user with username", recipient)
		);
		msg.setChatId(String.valueOf(telegramUser.getExternalId()));
		msg.setText(message);
		telegramBot.sendMsgToChat(msg);
	}

	@Override
	public Adapter getNotificationType()
	{
		return Adapter.TELEGRAM;
	}
}
