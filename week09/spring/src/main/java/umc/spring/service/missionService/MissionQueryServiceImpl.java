package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.repository.missionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable page) {
        return missionRepository.findCompletedAndOngoingMissions(memberId, page);
    }

    @Override
    public Page<Mission> findMissionsByRegion(String regionName, Pageable page) {
        return missionRepository.findMissionsByRegion(regionName, page);
    }


    @Override
    public Page<Mission> findMemberMissionList(Long memberId, Pageable pageable) {
        Page<Mission> filteredMissions = missionRepository.findAllByMemberMissionList(memberId, pageable);

        // 페이징된 미션 출력
        filteredMissions.forEach(review -> System.out.println("Review: " + review));

        return filteredMissions;
    }
}
