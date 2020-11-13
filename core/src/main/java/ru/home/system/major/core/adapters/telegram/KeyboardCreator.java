package ru.home.system.major.core.adapters.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.system.major.core.dto.TelegramButtonCallbackData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyboardCreator
{
	static InlineKeyboardMarkup getKeyboard(List<TelegramButtonCallbackData> buttonNames)
	{
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

		List<InlineKeyboardButton> buttons = new ArrayList<>();
		buttonNames.forEach(but -> {
			InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
			inlineKeyboardButton.setText(but.getButtonName());
			inlineKeyboardButton.setCallbackData(but.getButtonCallbackData());
			buttons.add(inlineKeyboardButton);
		});

		inlineKeyboardMarkup.setKeyboard(Collections.singletonList(buttons));

		return inlineKeyboardMarkup;
	}
}
