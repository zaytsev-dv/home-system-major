package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TelegramButtonCallbackData
{
	private String buttonName;
	private String buttonCallbackData;
}
