package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.missionDto.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(MissionRequestDTO.AddMissionDto request);
}
