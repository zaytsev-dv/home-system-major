package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.MsgCallback;
import ru.home.system.major.core.exceptions.HandlerNotFoundException;
import ru.home.system.major.core.service.msgCallback.MsgCallbackService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TelegramKeyboardEngine
{
	private final List<TelegramKeyboardHandler> handlers;
	private final MsgCallbackService msgCallbackService;

	public TelegramKeyboardEngine(List<TelegramKeyboardHandler> handlers, MsgCallbackService msgCallbackService)
	{
		this.handlers = handlers;
		this.msgCallbackService = msgCallbackService;
	}

	SendMessage handle(Update update)
	{
		Map<String, String> callbackMap = msgCallbackService.findAll().stream()
				.collect(Collectors.toMap(MsgCallback::getValue, MsgCallback::getType));

		String handlerType = callbackMap.get(update.getCallbackQuery().getData());
		if (!StringUtils.hasText(handlerType))
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
