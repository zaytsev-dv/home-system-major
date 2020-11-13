package ru.home.system.major.core.adapters.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardCreator
{
	static InlineKeyboardMarkup getKeyboard() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
		inlineKeyboardButton.setText("Тык");
		inlineKeyboardButton.setCallbackData("Button \"Тык\" has been pressed");
		inlineKeyboardMarkup.setKeyboard(Arrays.asList(Arrays.asList(inlineKeyboardButton)));

		return inlineKeyboardMarkup;
	}

	static InlineKeyboardMarkup getKeyboard(List<TelegramButtonCallbackData> buttonNames) {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		List<InlineKeyboardButton> buttons = new ArrayList<>();
		buttonNames.forEach(but -> {
			InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
			inlineKeyboardButton.setText(but.getButtonName());
			inlineKeyboardButton.setCallbackData(but.getButtonCallbackData());
			buttons.add(inlineKeyboardButton);
		});

		inlineKeyboardMarkup.setKeyboard(Arrays.asList(buttons));

		return inlineKeyboardMarkup;
	}

	static ForceReplyKeyboard getForceReplyKeyboard() {
		ForceReplyKeyboard keyboardMarkup = new ForceReplyKeyboard();
		keyboardMarkup.setForceReply(true);
		return keyboardMarkup;
	}

	static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//		keyboardMarkup.setOneTimeKeyboard(true);
		keyboardMarkup.setOneTimeKeyboard(false);
		KeyboardRow keyboardButtons = new KeyboardRow();
		keyboardButtons.set(1, "TETETETTETE");
		keyboardMarkup.setKeyboard(Arrays.asList(keyboardButtons));
		return keyboardMarkup;
	}
}
