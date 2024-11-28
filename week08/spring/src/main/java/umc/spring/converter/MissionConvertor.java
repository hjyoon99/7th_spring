package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.missionDto.MissionRequestDTO;
import umc.spring.web.dto.missionDto.MissionResponseDTO;

public class MissionConvertor {
    public static Mission toMission(MissionRequestDTO.AddMissionDto request, Store store) {
        return Mission.builder()
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.AddMissionResultDto toaddMissionResultDto(Mission mission) {
        return MissionResponseDTO.AddMissionResultDto.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
