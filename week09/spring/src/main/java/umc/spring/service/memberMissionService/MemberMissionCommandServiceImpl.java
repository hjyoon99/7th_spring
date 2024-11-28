package umc.spring.service.memberMissionService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
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

        checkDuplicateMissionProgress(request.getMemberId(), request.getMissionId());

        MemberMission memberMission = MemberMissionConverter.toMemberMission(
                member,
                mission,
                MissionStatus.IN_PROGRESS
        );

        // memberMissionRepository에 저장
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public MemberMission patchCompleteMission(MemberMissionRequestDTO.CompleteMissionDto request) {
        // MemberMissionRepository에서 IN_PROGRESS인 memberMission을 가져옴
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionIdAndStatus(
                request.getMemberId(),
                request.getMissionId(),
                MissionStatus.IN_PROGRESS
        ).orElseThrow(() -> new IllegalArgumentException(ErrorStatus.MISSION_ALREADY_COMPLETED.getMessage()));

        // 상태 변경 (setter를 안쓰기 위해서 memberMission엔티티에서 캡슐화)
        memberMission.completeMission();

        // 변경된 엔티티 저장
        return memberMissionRepository.save(memberMission);
    }


    // 중복체크로직
    private void checkDuplicateMissionProgress(Long memberId, Long missionId) {
        if (memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                memberId, missionId, MissionStatus.IN_PROGRESS)) {
            throw new IllegalArgumentException(ErrorStatus.MISSION_ALREADY_IN_PROGRESS.getMessage());
        }
    }

}
