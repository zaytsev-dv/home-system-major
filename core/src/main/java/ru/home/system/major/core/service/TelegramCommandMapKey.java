package ru.home.system.major.core.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelegramCommandMapKey
{
	private String name;
	private String type;

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof TelegramCommandMapKey)) return false;
		TelegramCommandMapKey that = (TelegramCommandMapKey) o;
		return Objects.equals(getName(), that.getName()) &&
				Objects.equals(getType(), that.getType());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getName(), getType());
	}
}
