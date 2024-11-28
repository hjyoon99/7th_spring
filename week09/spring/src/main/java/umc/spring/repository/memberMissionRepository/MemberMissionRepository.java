package umc.spring.repository.memberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
    // 특정 멤버의 진행 중인 미션 조회
    Optional<MemberMission> findByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}
