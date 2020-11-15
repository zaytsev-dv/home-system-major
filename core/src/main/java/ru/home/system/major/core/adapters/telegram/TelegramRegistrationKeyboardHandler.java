package ru.home.system.major.core.adapters.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.service.TelegramQuestionService;
import ru.home.system.major.core.service.TelegramUserService;

import java.util.Arrays;
import java.util.List;

@Component
public class TelegramRegistrationKeyboardHandler implements TelegramKeyboardHandler
{
	static List<String> registrationCommands = Arrays.asList(
			"/register_confirm_yes",
			"/register_confirm_no",
			"/register_confirm_final_yes",
			"/register_confirm_final_no"
	);

	private final TelegramUserService telegramUserService;
	private final TelegramQuestionService telegramQuestionService;

	public TelegramRegistrationKeyboardHandler(TelegramUserService telegramUserService, TelegramQuestionService telegramQuestionService)
	{
		this.telegramUserService = telegramUserService;
		this.telegramQuestionService = telegramQuestionService;
	}

	@Override
	public SendMessage handle(Update update)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
		TelegramUser telegramUser = telegramUserService.getByExternalId(Long.valueOf(update.getCallbackQuery().getFrom().getId()));
		switch (update.getCallbackQuery().getData())
		{
			case "/register_confirm_yes":
				response.setText("Супер!!!Введи свой email");
				break;
			case "/register_confirm_no":
			{
				response.setText("Что ж очень жаль. Тогда до новых встреч и удачи тебе!");
				break;
			}
			case "/register_confirm_final_yes":
			{
				telegramUser.setConfirm(true);
				telegramUserService.save(telegramUser);
				telegramQuestionService.deleteAll(telegramQuestionService.getAllByExternalIdAndType(telegramUser.getExternalId(), "REGISTRATION"));
				response.setText(
						String.format(
								"Добро пожаловать \"%s\":)",
								telegramUser.getFirstname() + " " + telegramUser.getLastname()
						)
				);
				break;
			}

			case "/register_confirm_final_no":
			{
				telegramUserService.delete(telegramUser);
				response.setText("/start");
				break;
			}

			default:
				throw new UnsupportedOperationException("Not impl");
		}

		return response;
	}

	@Override
	public String getType()
	{
		return "REGISTRATION";
	}
}
