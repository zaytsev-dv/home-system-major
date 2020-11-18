package ru.home.system.major.core.validators;

import org.springframework.util.StringUtils;
import ru.home.system.major.core.annotations.NullAndEmptySafe;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class NullAndEmptySafeValidator implements ConstraintValidator<NullAndEmptySafe, Object>
{
	public NullAndEmptySafeValidator()
	{
	}

	public boolean isValid(Object value, ConstraintValidatorContext context)
	{
		if (value == null)
		{
			return false;
		}
		else if (value.getClass().equals(String.class) && StringUtils.isEmpty(value))
		{
			return false;
		}
		else
		{
			return !value.getClass().equals(ArrayList.class) || !((List) value).isEmpty();
		}
	}
}
