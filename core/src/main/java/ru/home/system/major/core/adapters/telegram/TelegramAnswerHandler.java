package ru.home.system.major.core.adapters.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.TelegramQuestion;

public interface TelegramAnswerHandler
{
	String getType();
	SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion);
	void saveQuestion(Message message);
}
