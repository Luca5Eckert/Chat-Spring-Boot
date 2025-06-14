package com.projetospring.chatonline.service.validator;

import java.util.regex.Pattern;
import com.projetospring.chatonline.annotations.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    
    public static final Pattern UPPERCASE = Pattern.compile(".*[A-Z].*");
    public static final Pattern LOWERCASE = Pattern.compile(".*[a-z].*");
    public static final Pattern NUMBER = Pattern.compile(".*\\d.*");
    public static final Pattern SPECIAL = Pattern.compile(".*[!@#$%^&*()\\-+=~`{}\\[\\]:;\"'<>,.?/].*");
    
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        
        context.disableDefaultConstraintViolation();
        
        boolean isValid = true;
        
        if (!UPPERCASE.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate("Password must contain at least one uppercase letter")
                   .addConstraintViolation();
            isValid = false;
        }
        
        if (!LOWERCASE.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate("Password must contain at least one lowercase letter")
                   .addConstraintViolation();
            isValid = false;
        }
        
        if (!NUMBER.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate("Password must contain at least one number")
                   .addConstraintViolation();
            isValid = false;
        }
        
        if (!SPECIAL.matcher(password).matches()) {
            context.buildConstraintViolationWithTemplate("Password must contain at least one special character")
                   .addConstraintViolation();
            isValid = false;
        }
        
        return isValid;
    }
    
}