package umc.spring.service.memberMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.memberMissionRepository.MemberMissionRepository;
import umc.spring.repository.memberRepository.MemberRepository;
import umc.spring.repository.missionRepository.MissionRepository;
import umc.spring.web.dto.memberMissionDto.MemberMissionRequestDTO;
import umc.spring.web.dto.missionDto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberMission addChallengeMission(MemberMissionRequestDTO.AddChallengeMissionDto request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.MISSION_NOT_FOUND.getMessage()));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.MEMBER_NOT_FOUND.getMessage()));

        checkDuplicateMission(request.getMemberId(), request.getMissionId());

        MemberMission memberMission = MemberMissionConverter.toMemberMission(
                member,
                mission,
                MissionStatus.IN_PROGRESS
        );

        return memberMissionRepository.save(memberMission);
    }

    // 중복체크로직
    private void checkDuplicateMission(Long memberId, Long missionId) {
        if (memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                memberId, missionId, MissionStatus.IN_PROGRESS)) {
            throw new IllegalArgumentException(ErrorStatus.MISSION_ALREADY_IN_PROGRESS.getMessage());
        }
    }

}
