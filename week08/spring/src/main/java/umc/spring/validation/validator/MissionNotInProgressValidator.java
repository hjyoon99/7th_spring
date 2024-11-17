package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.memberMissionRepository.MemberMissionRepository;
import umc.spring.validation.annotaion.NotInProgressMission;
import umc.spring.web.dto.memberMissionDto.MemberMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionNotInProgressValidator implements ConstraintValidator<NotInProgressMission, MemberMissionRequestDTO.AddChallengeMissionDto> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MemberMissionRequestDTO.AddChallengeMissionDto request, ConstraintValidatorContext context) {

        //멤버 id나 미션 id 없으면 검증 실패
        if(request.getMemberId() == null || request.getMissionId() == null) {
            return false;
        }
        // 이미 도전 중인지 확인
        boolean isInProgress = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                request.getMemberId(), request.getMissionId(), MissionStatus.IN_PROGRESS);

        if(isInProgress) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 미션은 이미 도전 중입니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }


}
