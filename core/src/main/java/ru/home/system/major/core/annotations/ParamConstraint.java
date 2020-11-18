package ru.home.system.major.core.annotations;

import ru.home.system.major.core.validators.ParamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ParamValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamConstraint
{
	String message() default "Default validation message";

	String conditionType();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
