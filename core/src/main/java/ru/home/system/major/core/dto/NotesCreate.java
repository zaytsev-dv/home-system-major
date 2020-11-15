package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesCreate
{
	private String value;
	private String description;
	private Long telegramUserId;
}
