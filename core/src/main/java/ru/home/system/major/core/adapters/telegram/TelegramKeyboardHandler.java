package ru.home.system.major.core.adapters.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramKeyboardHandler
{
	SendMessage handle(Update update);
	String getType();
}
