package ru.home.system.major.core.annotations;

import ru.home.system.major.core.validators.DateTimeFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
		validatedBy = {DateTimeFormatValidator.class}
)
public @interface DateTimeFormat
{
	String message() default "wrong date format";

	String format() default "yyyy-MM-dd'T'HH:mm";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
