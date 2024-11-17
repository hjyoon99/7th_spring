package umc.spring.web.dto.missionDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import umc.spring.validation.annotaion.ExistStore;
import umc.spring.validation.annotaion.NotInProgressMission;

import java.time.LocalDate;


public class MissionRequestDTO {

    @Getter
    public static class AddMissionDto{
        @ExistStore
        private Long storeId;

        @NotBlank(message = "미션 설명은 필수 입력 사항입니다.")
        private String missionSpec;

        @NotNull(message = "미션 보상은 필수 입력 사항입니다.")
        private Integer reward;

        @NotNull(message = "미션 기한은 필수 입력 사항입니다.")
        private LocalDate deadline;
    }


}
