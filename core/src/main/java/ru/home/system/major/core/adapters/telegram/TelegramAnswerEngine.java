package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.TelegramQuestion;

import java.util.List;

@Component
@Slf4j
public class TelegramAnswerEngine extends BaseTelegramAnswerHandler
{
	public TelegramAnswerEngine(List<TelegramAnswerHandler> handlers)
	{
		super(handlers);
	}

	SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion)
	{
		TelegramAnswerHandler handler = getMessageHandler(lastQuestion);
		return handler.questionNextAsk(update, lastQuestion);
	}

	void saveQuestion(Message message)
	{
		String handlerType = "";
		if (TelegramRegistrationHandler.registration.contains(message.getText()))
		{
			handlerType = "REGISTRATION";
		}

		if (StringUtils.hasText(handlerType)) {
			TelegramAnswerHandler handler = getMessageHandler(handlerType);
			handler.saveQuestion(message);
		}
	}

	SendMessage unknownMessage(Update update) {
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));
		response.setText("Неизвестная команда");
		return response;
	}
}
