package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.regionRepository.RegionRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.storeDto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.AddDto request) {

        // regionId로 Region 객체 조회
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역 ID입니다."));

        // Store 엔티티 생성
        Store newStore = StoreConverter.toStore(request, region);

        //Store 엔티티 저장
        return storeRepository.save(newStore);
    }
}
