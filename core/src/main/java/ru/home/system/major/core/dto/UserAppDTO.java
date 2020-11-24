package ru.home.system.major.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAppDTO
{
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private boolean activated;
}
