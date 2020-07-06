package com.musalasoft.practicaltask.GatewayManager.db;
import com.musalasoft.practicaltask.GatewayManager.exceptions.InvalidDataException;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;


public class ValidatorDB<T> {

    public void validate(T t){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }
            throw new InvalidDataException("Data is not valid:\n" + violationMessages + "\n");
        }
    }

}

