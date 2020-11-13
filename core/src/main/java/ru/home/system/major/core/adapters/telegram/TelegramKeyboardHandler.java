package ru.home.system.major.core.adapters.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramKeyboardHandler
{
	SendMessage handle(Update update)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));

		switch (update.getCallbackQuery().getData())
		{
			case "/register_confirm_yes":
				response.setText("Супер!!!Введи свой email");
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
