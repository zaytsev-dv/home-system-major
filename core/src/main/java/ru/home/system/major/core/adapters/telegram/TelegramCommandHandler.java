package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;

import java.util.Arrays;

import static ru.home.system.major.core.adapters.telegram.KeyboardCreator.getKeyboard;
import static ru.home.system.major.core.adapters.telegram.TelegramAdapterCommand.START;

@Component
@Slf4j
public class TelegramCommandHandler
{
	SendMessage handle(Update update)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));

		// в данном случае это команда
		switch (update.getMessage().getText())
		{
			case START:
				response.setText("Привет " + "\uD83D\uDE01" + "\n" + "Для начала необходимо зарегистрироваться. Ты готов?");
				response.setReplyMarkup(getKeyboard(Arrays.asList(
						new TelegramButtonCallbackData("Да", "/register_confirm_yes"),
						new TelegramButtonCallbackData("Нет", "/register_confirm_no")
				)));
				break;

			default:
				throw new UnsupportedOperationException("Not impl");
		}

		return response;
	}
}
