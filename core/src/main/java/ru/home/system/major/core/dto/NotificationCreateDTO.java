package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.home.system.artifactory.annotations.NullAndEmptySafe;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateDTO
{
	@NullAndEmptySafe(message = "field: \"notificationType\" not be null or empty")
	private String notificationType;

	@NullAndEmptySafe(message = "field: \"subject\" not be null or empty")
	private String subject;

	@NullAndEmptySafe(message = "field: \"message\" not be null or empty")
	private String message;

	@NullAndEmptySafe(message = "field: \"recipient\" not be null or empty")
	private String recipient;
}
