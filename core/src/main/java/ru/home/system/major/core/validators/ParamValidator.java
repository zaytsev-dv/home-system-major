package ru.home.system.major.core.validators;

import org.springframework.util.StringUtils;
import ru.home.system.major.core.annotations.ParamConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParamValidator implements ConstraintValidator<ParamConstraint, String>
{
	private String conditionType;

	@Override
	public void initialize(ParamConstraint paramConstraint)
	{
		conditionType = paramConstraint.conditionType();
	}

	@Override
	public boolean isValid(String paramField, ConstraintValidatorContext cxt)
	{
		boolean result = true;
		if (conditionType.equalsIgnoreCase("regex"))
		{
			result = StringUtils.hasText(paramField);
		}
		return result;
	}
}
