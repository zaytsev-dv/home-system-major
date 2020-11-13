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
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.service.TelegramQuestionService;

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
	private final TelegramAnswerEngine answerHandler;
	private final TelegramQuestionService telegramQuestionService;

	public TelegramBot(
			TelegramCommandHandler commandHandler,
			TelegramKeyboardHandler keyboardHandler,
			TelegramAnswerEngine answerHandler,
			TelegramQuestionService telegramQuestionService
	)
	{
		this.commandHandler = commandHandler;
		this.keyboardHandler = keyboardHandler;
		this.answerHandler = answerHandler;
		this.telegramQuestionService = telegramQuestionService;
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

	/**
	 * @param update telegram library object. See -> org.telegram.telegrambots.meta.api.objects
	 * @implNote Income telegram message handler
	 */
	//TODO: run async
	@Override
	public void onUpdateReceived(Update update)
	{
		TelegramQuestion lastQuestion = null;
		if (update.getMessage() == null)
		{
			lastQuestion = telegramQuestionService.findTopByOrderByCreatedAtDesc(Long.valueOf(update.getCallbackQuery().getFrom().getId()));
		}
		else
		{
			lastQuestion = telegramQuestionService.findTopByOrderByCreatedAtDesc(Long.valueOf(update.getMessage().getFrom().getId()));
		}
		boolean isCommand = update.getMessage() != null && !CollectionUtils.isEmpty(update.getMessage().getEntities()) && update.getMessage().getText().startsWith("/");
		boolean isAnswerOnKeyboardButton = update.getCallbackQuery() != null;
		boolean isAnswerOnQuestion = lastQuestion != null && !lastQuestion.isAnswered();

		SendMessage message = null;

		//пришла команда
		if (isCommand)
		{
			message = commandHandler.handle(update);
		}

		//обработчик клика по кнопке клавиатуры
		else if (isAnswerOnKeyboardButton)
		{
			message = keyboardHandler.handle(update);
		}

		//ответ на вопрос заданный обычным сообщением (без клавиатуры и тд) ||
		else if (isAnswerOnQuestion)
		{
			message = answerHandler.questionNextAsk(update, lastQuestion);
		}

		//пользователь отправил сообщение руками сам. Не через такие средства как клавиатура и тд
		else
		{
			message = answerHandler.unknownMessage(update);
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
					answerHandler.saveQuestion(response);
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
