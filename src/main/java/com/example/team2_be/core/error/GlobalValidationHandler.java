package com.example.team2_be.core.error;

import com.example.team2_be.core.error.exception.BadRequestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Aspect
@Component
public class GlobalValidationHandler {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validationAdvice(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    throw new BadRequestException(
                            errors.getFieldErrors().get(0).getField()+":"+errors.getFieldErrors().get(0).getDefaultMessage()
                    );
                }
            }
        }
    }
}