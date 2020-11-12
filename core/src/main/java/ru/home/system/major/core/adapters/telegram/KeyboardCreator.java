package ru.home.system.major.core.adapters.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;

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
}
