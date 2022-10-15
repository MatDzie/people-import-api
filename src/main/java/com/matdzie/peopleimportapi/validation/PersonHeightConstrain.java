package com.matdzie.peopleimportapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonHeightValidator.class)
public @interface PersonHeightConstrain {

    String message() default "{com.matdzie.peopleimportapi.validation.PersonHeightConstrain.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
