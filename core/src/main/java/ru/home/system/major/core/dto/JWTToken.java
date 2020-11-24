package ru.home.system.major.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JWTToken
{
	private String accessToken;

	@JsonProperty("accessToken")
	String getAccessToken()
	{
		return accessToken;
	}

	void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
}
