package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.memberMissionDto.MemberMissionResponseDTO;

public class MemberMissionConverter {
    public static MemberMissionResponseDTO.ChallengeResponseDTO toChallengeResponseDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .memberMissionStatus(memberMission.getStatus().toString())
                .createdAt(memberMission.getCreatedAt())
                .build();

    }

    public static MemberMission toMemberMission(Member member, Mission mission, MissionStatus status) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(status)
                .build();
    }


}
