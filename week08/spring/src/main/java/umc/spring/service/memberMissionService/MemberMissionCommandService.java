package umc.spring.service.memberMissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.memberMissionDto.MemberMissionRequestDTO;
import umc.spring.web.dto.missionDto.MissionRequestDTO;

public interface MemberMissionCommandService {
    MemberMission addChallengeMission(MemberMissionRequestDTO.AddChallengeMissionDto request);
}
