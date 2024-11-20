package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.validation.annotaion.ExistStore;

import java.lang.annotation.Annotation;

@RequiredArgsConstructor
public class StoreExistsValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        boolean isValid = storeId != null && storeRepository.existsById(storeId);

        if (!isValid) {
            //기본 오류 메시지 비활성화
            context.disableDefaultConstraintViolation();
            //커스텀 오류 메시지
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }
        return isValid;
    }
}
