package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;
import ru.home.system.major.core.service.TelegramUserService;

import java.util.Arrays;
import java.util.Optional;

import static ru.home.system.major.core.adapters.telegram.KeyboardCreator.getKeyboard;
import static ru.home.system.major.core.adapters.telegram.TelegramAdapterCommand.START;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot
{
	@Value("${telegram-bot.token}")
	private String token;

	@Value("${telegram-bot.username}")
	private String username;

	private final TelegramUserService telegramUserService;

	public TelegramBot(TelegramUserService telegramUserService)
	{
		this.telegramUserService = telegramUserService;
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
		boolean isCommand = update.getMessage() != null;
		boolean isAnswerOnCommand = update.getCallbackQuery() != null;

		SendMessage response = new SendMessage();
		Long chatId = 0L;
		String text = "";

		if (isCommand)
		{
			chatId = update.getMessage().getChatId();

			switch (update.getMessage().getText())
			{
				case START:
					text = "Привет " + "\uD83D\uDE01" + "\n" + "Для начала необходимо зарегистрироваться. Ты готов?";
					response.setReplyMarkup(getKeyboard(Arrays.asList(
							new TelegramButtonCallbackData("Да", "/register_confirm_yes"),
							new TelegramButtonCallbackData("Нет", "/register_confirm_no")
					)));
					break;

				default:
					text = update.getMessage().getText();
			}
		}
		else if (isAnswerOnCommand)
		{
			chatId = update.getCallbackQuery().getMessage().getChatId();
			switch (update.getCallbackQuery().getData())
			{
				case "/register_confirm_yes":
				{
					text = "Супер!!!Введи свою фамилию и имя";
					break;
				}
				case "/register_confirm_no":
				{
					text = "Что ж очень жаль. Тогда до новых встреч и удачи тебе!";
					break;
				}
			}
		}
		else
		{
			throw new UnsupportedOperationException("not impl");
		}

		response.setChatId(String.valueOf(chatId));
		response.setText(text);

		sendMsgToChat(response);
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
					System.out.println("twts");
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

	private void syncTelegramUser(Update update)
	{
		log.info("sync telegram user");
		User from = update.getMessage().getFrom();
		telegramUserService.checkWithSaveByExternalId(
				TelegramUser.builder()
						.externalId(Long.valueOf(from.getId()))
						.firstname(from.getFirstName())
						.lastname(from.getLastName())
						.username(Optional.ofNullable(from.getUserName()).orElse(null))
						.build()
		);
	}
}
