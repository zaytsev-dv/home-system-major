package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.exceptions.HandlerNotFoundException;

import java.util.List;

@Component
@Slf4j
public abstract class BaseTelegramAnswerHandler
{
	List<TelegramAnswerHandler> handlers;

	public BaseTelegramAnswerHandler(List<TelegramAnswerHandler> handlers)
	{
		this.handlers = handlers;
	}

	TelegramAnswerHandler getMessageHandler(TelegramQuestion lastQuestion)
	{
		return handlers.stream()
				.filter(handler -> handler.getType().equalsIgnoreCase(lastQuestion.getType()))
				.findFirst().orElseThrow(() -> new HandlerNotFoundException(lastQuestion.getType()));
	}

	TelegramAnswerHandler getMessageHandler(String handlerType)
	{
		return handlers.stream()
				.filter(handler -> handler.getType().equalsIgnoreCase(handlerType))
				.findFirst().orElseThrow(() -> new HandlerNotFoundException(handlerType));
	}
}
