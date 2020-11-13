package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;
import ru.home.system.major.core.service.TelegramQuestionService;
import ru.home.system.major.core.service.TelegramUserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.home.system.major.core.adapters.telegram.KeyboardCreator.getKeyboard;

@Component
@Slf4j
public class TelegramRegistrationHandler implements TelegramAnswerHandler
{
	static List<String> registrationEMAIL = Collections.singletonList("Супер!!!Введи свой email");
	static List<String> registrationPHONE = Collections.singletonList("Супер! А теперь Номер телефона");
	static List<String> registration = Arrays.asList("Супер!!!Введи свой email", "Супер! А теперь Номер телефона");

	private final TelegramQuestionService telegramQuestionService;
	private final TelegramUserService telegramUserService;

	public TelegramRegistrationHandler(TelegramQuestionService telegramQuestionService, TelegramUserService telegramUserService)
	{
		this.telegramQuestionService = telegramQuestionService;
		this.telegramUserService = telegramUserService;
	}

	@Override
	public SendMessage questionNextAsk(Update update, TelegramQuestion lastQuestion)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getMessage().getChatId()));

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
				telegramUser.setPhoneNumber(update.getMessage().getText());
				response.setText(
						String.format(
								"Все ли верно?\n Email: \"%s\"\n Номер телефона: \"%s\"",
								telegramUser.getEmail(), telegramUser.getPhoneNumber()
						)
				);

				response.setReplyMarkup(getKeyboard(Arrays.asList(
						new TelegramButtonCallbackData("Да", "/register_confirm_final_yes"),
						new TelegramButtonCallbackData("Нет (заполнить заново)", "/register_confirm_final_no")
				)));

				break;
			}
		}

		telegramUserService.save(telegramUser);


		lastQuestion.setAnswered(true);
		telegramQuestionService.save(lastQuestion);

		return response;
	}

	@Override
	public void saveQuestion(Message message)
	{
		boolean needSave = false;
		String type = null;
		String subType = null;

		if (registrationEMAIL.contains(message.getText()))
		{
			needSave = true;
			type = "REGISTRATION";
			subType = "EMAIL";
		}

		if (registrationPHONE.contains(message.getText()))
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

	@Override
	public String getType()
	{
		return "REGISTRATION";
	}
}
