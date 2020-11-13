package ru.home.system.major.core.adapters.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.service.TelegramUserService;

@Component
public class TelegramKeyboardHandler
{
	private final TelegramUserService telegramUserService;

	public TelegramKeyboardHandler(TelegramUserService telegramUserService)
	{
		this.telegramUserService = telegramUserService;
	}

	SendMessage handle(Update update)
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
}
