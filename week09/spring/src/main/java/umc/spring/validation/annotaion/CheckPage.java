package umc.spring.validation.annotaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.PageCheckValidator;
import umc.spring.validation.validator.StoreExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageCheckValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    // page 범위에 따라 커스텀 어노테이션 page 1을 0으로 만들어 return
    // page 범위가 너무 작은지(0이하) 판단해 작으면 에러
    // 에러 발생 시 RestControllerAdvice와 연계
    String message() default "유효하지 않은 page number 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

