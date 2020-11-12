package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateDTO
{
	private String notificationType;
	private String subject;
	private String message;
	private String recipient;
}
