package umc.spring.service.missionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

public interface MissionQueryService {
    Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable);
    Page<Mission> findMissionsByRegion(String regionName, Pageable pageable);
}
