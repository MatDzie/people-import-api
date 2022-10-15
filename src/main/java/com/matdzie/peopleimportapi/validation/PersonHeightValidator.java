package com.matdzie.peopleimportapi.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonHeightValidator implements ConstraintValidator<PersonHeightConstrain, Integer> {

    @Value("${person.constrain.height.min}")
    protected Integer personMinHeight;

    @Override
    public boolean isValid(Integer height, ConstraintValidatorContext context) {
        var isValid = height != null && height >= personMinHeight;
        if (!isValid) {
            formatMessage(context);
        }
        return isValid;
    }

    private void formatMessage(ConstraintValidatorContext context) {
        String formattedMsg = String.format("Person height must be equal or greater than %d", this.personMinHeight);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(formattedMsg).addConstraintViolation();
    }
}