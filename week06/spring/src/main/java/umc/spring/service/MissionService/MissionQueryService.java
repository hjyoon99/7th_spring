package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

public interface MissionQueryService extends JpaRepository<Mission, Long> {
    Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable);
    Page<Mission> findMissionsByRegion(String regionName, Pageable pageable);
}
