package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.exceptions.HandlerNotFoundException;

import java.util.List;

@Component
@Slf4j
public class TelegramKeyboardEngine
{
	List<TelegramKeyboardHandler> handlers;

	public TelegramKeyboardEngine(List<TelegramKeyboardHandler> handlers)
	{
		this.handlers = handlers;
	}

	SendMessage handle(Update update)
	{
		String handlerType = "";
		if (TelegramRegistrationKeyboardHandler.registrationCommands.contains(update.getCallbackQuery().getData()))
		{
			handlerType = "REGISTRATION";
		}
		else
		{
			handlerType = "UNKNOWN";
		}

		return getHandler(handlerType).handle(update);
	}

	TelegramKeyboardHandler getHandler(String handlerType)
	{
		return handlers.stream()
				.filter(handler -> handler.getType().equalsIgnoreCase(handlerType))
				.findFirst().orElseThrow(() -> new HandlerNotFoundException(handlerType));
	}
}
