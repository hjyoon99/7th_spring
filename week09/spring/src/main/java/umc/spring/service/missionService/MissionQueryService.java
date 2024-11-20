package umc.spring.service.missionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

public interface MissionQueryService {
    Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable page);
    Page<Mission> findMissionsByRegion(String regionName, Pageable page);
    Page<Mission> findMemberMissionList(Long memberId, Pageable pageable);
}
