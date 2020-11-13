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
import java.util.Optional;

@Component
@Slf4j
public class TelegramMessageHandler
{
	private final TelegramQuestionService telegramQuestionService;
	private final TelegramUserService telegramUserService;

	public TelegramMessageHandler(TelegramQuestionService telegramQuestionService, TelegramUserService telegramUserService)
	{
		this.telegramQuestionService = telegramQuestionService;
		this.telegramUserService = telegramUserService;
	}

	SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));

		if (lastQuestion.getType().equalsIgnoreCase("REGISTRATION"))
		{
			TelegramUser telegramUser = syncTelegramUser(update);

			switch (lastQuestion.getSubType())
			{
				case "EMAIL":
				{
					response.setText("Супер! А теперь Номер телефона");
					telegramUser.setEmail(update.getMessage().getText());
					break;
				}

				case "PHONE_NUMBER":
				{
					response.setText(
							String.format(
									"Добро пожаловать \"%s\":)",
									telegramUser.getFirstname() + " " + telegramUser.getLastname()
							)
					);
					telegramUser.setPhoneNumber(update.getMessage().getText());
					break;
				}
			}

			telegramUserService.save(telegramUser);
		}


		lastQuestion.setAnswered(true);
		telegramQuestionService.save(lastQuestion);

		return response;
	}

	void saveQuestion(Message message)
	{
		boolean needSave = false;
		String type = null;
		String subType = null;

		if (MessageToSave.registrationEMAIL.contains(message.getText()))
		{
			needSave = true;
			type = "REGISTRATION";
			subType = "EMAIL";
		}

		if (MessageToSave.registrationPHONE.contains(message.getText()))
		{
			needSave = true;
			type = "REGISTRATION";
			subType = "PHONE_NUMBER";
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
}
