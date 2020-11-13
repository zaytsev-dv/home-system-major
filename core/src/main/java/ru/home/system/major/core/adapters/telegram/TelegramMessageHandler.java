package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.system.major.core.domain.TelegramQuestion;

@Component
@Slf4j
public class TelegramMessageHandler
{
	SendMessage handle(String text, Long chatId, TelegramQuestion lastQuestion)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(chatId));
		response.setText(text);
		return response;
	}
}
