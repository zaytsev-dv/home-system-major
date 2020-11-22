package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.MsgCallback;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.service.msgCallback.MsgCallbackService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TelegramAnswerEngine extends BaseTelegramAnswerHandler
{
	private final MsgCallbackService msgCallbackService;

	public TelegramAnswerEngine(List<TelegramAnswerHandler> handlers, MsgCallbackService msgCallbackService)
	{
		super(handlers);
		this.msgCallbackService = msgCallbackService;
	}

	SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion)
	{
		TelegramAnswerHandler handler = getMessageHandler(lastQuestion);
		return handler.questionNextAsk(update, lastQuestion);
	}

	void saveQuestion(Message message)
	{
		Map<String, String> callbackMap = msgCallbackService.findAll().stream()
				.collect(Collectors.toMap(MsgCallback::getValue, MsgCallback::getType));

		String handlerType = callbackMap.get(message.getText());

		if (StringUtils.hasText(handlerType))
		{
			TelegramAnswerHandler handler = getMessageHandler(handlerType);
			handler.saveQuestion(message);
		}
	}

	SendMessage unknownMessage(Update update)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));
		response.setText("Неизвестная команда");
		return response;
	}
}
