package umc.spring.service.missionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConvertor;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.missionRepository.MissionRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.web.dto.missionDto.MissionRequestDTO;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission addMission(MissionRequestDTO.AddMissionDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게 ID입니다."));

        Mission mission = MissionConvertor.toMission(request, store);

        return missionRepository.save(mission);


    }
}
