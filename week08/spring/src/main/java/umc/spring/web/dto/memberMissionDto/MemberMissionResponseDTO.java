package umc.spring.web.dto.memberMissionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberMissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeResponseDTO {
        Long memberMissionId;
        Long missionId;
        Long memberId;
        String memberMissionStatus;
        LocalDateTime createdAt;
    }
}
