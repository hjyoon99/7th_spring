package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable) {
        // 완료된 또는 진행 중인 미션 조회
        return missionRepository.findCompletedAndOngoingMissions(memberId, pageable);
    }

    @Override
    public Page<Mission> findMissionsByRegion(String regionName, Pageable pageable) {
        // 특정 지역의 도전 가능한 미션 조회
        return missionRepository.findMissionsByRegion(regionName, pageable);
    }
}