package umc.spring.repository.missionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;

public interface MissionRepositoryCustom {
    Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable);
    Page<Mission> findMissionsByRegion(String regionName, Pageable pageable);
}
