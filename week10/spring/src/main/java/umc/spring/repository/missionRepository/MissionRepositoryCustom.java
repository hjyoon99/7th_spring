package umc.spring.repository.missionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;

public interface MissionRepositoryCustom {
    // 완료, 진행중인 미션 조회
    Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable);
    // 진행중인 미션 조회
    Page<Mission> findInProgressMissions(Long memberId, Pageable pageable);
    Page<Mission> findMissionsByRegion(String regionName, Pageable pageable);
    Page<Mission> findAllByMemberMissionList(Long memberId, Pageable pageable);
}
