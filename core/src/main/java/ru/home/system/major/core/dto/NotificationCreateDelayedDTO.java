package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationCreateDelayedDTO extends NotificationCreateDTO
{
	private String dateTime;

	public String getDateTime()
	{
		return dateTime;
	}
}
