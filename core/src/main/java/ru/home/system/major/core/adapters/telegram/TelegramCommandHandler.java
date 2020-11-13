package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.home.system.major.core.domain.TelegramUser;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;
import ru.home.system.major.core.service.TelegramUserService;

import java.util.Arrays;
import java.util.Optional;

import static ru.home.system.major.core.adapters.telegram.KeyboardCreator.getKeyboard;
import static ru.home.system.major.core.adapters.telegram.TelegramAdapterCommand.START;

@Component
@Slf4j
public class TelegramCommandHandler
{
	private final TelegramUserService telegramUserService;

	public TelegramCommandHandler(TelegramUserService telegramUserService)
	{
		this.telegramUserService = telegramUserService;
	}

	SendMessage handle(String command, Long chatId) {
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(chatId));

		switch (command) {
			case START:
				response.setText("Привет " + "\uD83D\uDE01" + "\n" + "Для начала необходимо зарегистрироваться. Ты готов?");
				response.setReplyMarkup(getKeyboard(Arrays.asList(
						new TelegramButtonCallbackData("Да", "/register_confirm_yes"),
						new TelegramButtonCallbackData("Нет", "/register_confirm_no")
				)));
				break;

			default:
				throw new UnsupportedOperationException("Not impl");
		}

		return response;
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
