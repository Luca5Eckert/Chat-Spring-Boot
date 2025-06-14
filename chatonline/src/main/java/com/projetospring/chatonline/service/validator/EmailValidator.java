package com.projetospring.chatonline.service.validator;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import com.projetospring.chatonline.annotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        
        context.disableDefaultConstraintViolation();
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            context.buildConstraintViolationWithTemplate("Email must have a valid format")
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}