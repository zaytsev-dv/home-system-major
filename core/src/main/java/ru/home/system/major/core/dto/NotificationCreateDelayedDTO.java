package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.home.system.artifactory.annotations.DateTimeFormat;
import ru.home.system.artifactory.annotations.NullAndEmptySafe;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationCreateDelayedDTO extends NotificationCreateDTO
{
	@NullAndEmptySafe(message = "field: \"dateTime\" not be null or empty")
	@DateTimeFormat(message = "wrong \"dateTime field format\". Need is \"yyyy-MM-dd'T'HH:mm\"")
	private String dateTime;

	public String getDateTime()
	{
		return dateTime;
	}
}
