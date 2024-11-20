package umc.spring.web.dto.memberMissionDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import umc.spring.validation.annotaion.NotInProgressMission;

@NotInProgressMission
public class MemberMissionRequestDTO {
    @Getter

    public static class AddChallengeMissionDto{
        @NotNull(message = "미션 ID는 필수 입력 사항입니다.")
        private Long missionId;

        @NotNull(message = "멤버 ID는 필수 입력 사항입니다.")
        private Long memberId;
    }

    @Data
    public static class CompleteMissionDto {
        private Long memberId;
        private Long missionId;
    }

}
