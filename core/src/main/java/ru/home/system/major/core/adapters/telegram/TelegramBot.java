package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot
{
	@Value("${telegram-bot.token}")
	private String token;

	@Value("${telegram-bot.username}")
	private String username;

	private final TelegramCommandHandler commandHandler;
	private final TelegramKeyboardHandler keyboardHandler;
	private final TelegramMessageHandler messageHandler;

	public TelegramBot(
			TelegramCommandHandler commandHandler,
			TelegramKeyboardHandler keyboardHandler,
			TelegramMessageHandler messageHandler
	)
	{
		this.commandHandler = commandHandler;
		this.keyboardHandler = keyboardHandler;
		this.messageHandler = messageHandler;
	}

	@Override
	public String getBotUsername()
	{
		return username;
	}

	@Override
	public String getBotToken()
	{
		return token;
	}

	//TODO: run async
	@Override
	public void onUpdateReceived(Update update)
	{
		boolean isCommand = update.getMessage() != null && !CollectionUtils.isEmpty(update.getMessage().getEntities());
		boolean isAnswerOnKeyboardButton = update.getCallbackQuery() != null;

		SendMessage message = null;

		//пришла команда
		if (isCommand)
		{
			message = commandHandler.handle(update.getMessage().getText(), update.getMessage().getChatId());
		}

		//обработчик клика по кнопке клавиатуры
		else if (isAnswerOnKeyboardButton)
		{
			message = keyboardHandler.handle(update.getCallbackQuery().getData(), update.getCallbackQuery().getMessage().getChatId());
		}

		//ответ на вопрос заданный обычным сообщением (без клавиатуры и тд) ||
		//пользователь отправил сообщение руками сам. Не через такие средства как клавиатура и тд
		else
		{
			//TODO: доделать
			message = messageHandler.handle(null, update.getMessage().getChatId());
		}

		sendMsgToChat(message);
	}

	void sendMsgToChat(SendMessage response)
	{
		try
		{
			log.info("Sent message \"{}\" to {}", response.getText(), response.getChatId());
			executeAsync(response, new SentCallback<Message>()
			{
				@Override
				public void onResult(BotApiMethod<Message> method, Message response)
				{
					log.info(response.toString());
				}

				@Override
				public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException)
				{
					log.error(apiException.getMessage());
					log.error("Stacktrace:", apiException);
				}

				@Override
				public void onException(BotApiMethod<Message> method, Exception exception)
				{
					log.error(exception.getMessage());
					log.error("Stacktrace:", exception);
				}
			});
		}
		catch (TelegramApiException e)
		{
			log.error("Failed to send message \"{}\" to {} due to error: {}", response.getText(), response.getChatId(), e.getMessage());
		}
	}
}
