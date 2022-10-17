package com.matdzie.peopleimportapi.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PersonHeightValidator implements ConstraintValidator<PersonHeightConstrain, String> {

    @Value("${person.constrain.height.min}")
    protected String personMinHeight;

    @Override
    public boolean isValid(String height, ConstraintValidatorContext context) {
        boolean isValid = false;
        formatMessage(context);

        try {
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            isValid = height != null && (height.equals("unknown") || (format.parse(height).intValue() >= Integer.parseInt(personMinHeight)));
        } catch (ParseException ignored) {
            isValid = false;
        }

        return isValid;
    }

    private void formatMessage(ConstraintValidatorContext context) {
        String formattedMsg = String.format("Person height must be equal or greater than %s", this.personMinHeight);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(formattedMsg).addConstraintViolation();
    }
}