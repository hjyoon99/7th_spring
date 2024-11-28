package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.missionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable) {
        return missionRepository.findCompletedAndOngoingMissions(memberId, pageable);
    }

    @Override
    public Page<Mission> findMissionsByRegion(String regionName, Pageable pageable) {
        return missionRepository.findMissionsByRegion(regionName, pageable);
    }
}
