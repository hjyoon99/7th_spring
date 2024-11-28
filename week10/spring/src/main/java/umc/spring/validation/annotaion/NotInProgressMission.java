package umc.spring.validation.annotaion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionNotInProgressValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MissionNotInProgressValidator.class)
@Target(ElementType.TYPE) // 클래스 레벨에서 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInProgressMission {
    String message() default "해당 미션은 이미 도전 중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
