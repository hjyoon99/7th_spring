package umc.spring.validation.annotaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.StoreExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {

    String message() default "해당 가게가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
