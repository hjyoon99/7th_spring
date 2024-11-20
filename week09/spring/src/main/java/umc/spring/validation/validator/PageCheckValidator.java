package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotaion.CheckPage;

@Component
@RequiredArgsConstructor
public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //page 값이 null 이거나 0이하면 에러
        if(value == null || value < 0){ // 일단 -1로 받아오는 거 보류...
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_VALIDATION_ERROR.getMessage())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
