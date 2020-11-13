package ru.home.system.major.core.adapters.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TelegramKeyboardHandler
{
	SendMessage handle(String command, Long chatId)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(chatId));

		switch (command)
		{
			case "/register_confirm_yes":
				response.setText("Супер!!!Введи свою фамилию и имя");
				break;
			case "/register_confirm_no":
			{
				response.setText("Что ж очень жаль. Тогда до новых встреч и удачи тебе!");
				break;
			}

			default:
				throw new UnsupportedOperationException("Not impl");
		}

		return response;
	}
}
