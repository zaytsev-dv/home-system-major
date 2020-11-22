package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.service.TelegramQuestionService;
import ru.home.system.major.core.service.TelegramUserService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TelegramRealmKeyboardHandler implements TelegramKeyboardHandler, TelegramAnswerHandler
{
	static List<String> realmStart = Collections.singletonList("Какой товар тебя интересует?");
	static List<String> realmPrice = Collections.singletonList("Какой бюджет?");

	private final TelegramQuestionService telegramQuestionService;
	private final TelegramUserService telegramUserService;

	public TelegramRealmKeyboardHandler(TelegramQuestionService telegramQuestionService, TelegramUserService telegramUserService)
	{
		this.telegramQuestionService = telegramQuestionService;
		this.telegramUserService = telegramUserService;
	}

	@Override
	public SendMessage handle(Update update)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
		response.setText("Какой товар тебя интересует?");
		return response;
	}

	@Override
	public String getType()
	{
		return "REALM";
	}

	@Override
	public SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));

		TelegramUser telegramUser = syncTelegramUser(update);
		switch (lastQuestion.getSubType())
		{
			case "START":
			{
				response.setText("Какой бюджет?");
				break;
			}

			case "PRICE":
			{
				response.setText("Я нашел следующие товары: ");
				break;
			}

		}

		telegramUserService.save(telegramUser);


		lastQuestion.setAnswered(true);
		telegramQuestionService.save(lastQuestion);

		return response;
	}

	private TelegramUser syncTelegramUser(Update update)
	{
		log.info("sync telegram user");
		User from = update.getMessage().getFrom();
		return telegramUserService.checkWithSaveByExternalId(
				TelegramUser.builder()
						.externalId(Long.valueOf(from.getId()))
						.firstname(from.getFirstName())
						.lastname(from.getLastName())
						.username(Optional.ofNullable(from.getUserName()).orElse(null))
						.build()
		);
	}

	@Override
	public void saveQuestion(Message message)
	{
		boolean needSave = false;
		String type = null;
		String subType = null;

		if (realmStart.contains(message.getText()))
		{
			needSave = true;
			type = "REALM";
			subType = "START";
		}

		if (realmPrice.contains(message.getText()))
		{
			needSave = true;
			type = "REALM";
			subType = "PRICE";
		}

		if (needSave)
		{
			TelegramQuestion question = TelegramQuestion.builder()
					.externalId(message.getChat().getId())
					.value(message.getText())
					.type(type)
					.subType(subType)
					.createdAt(LocalDateTime.now())
					.build();
			telegramQuestionService.save(question);
		}
	}
}
