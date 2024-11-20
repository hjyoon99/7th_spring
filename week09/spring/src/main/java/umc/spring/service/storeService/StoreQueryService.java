package umc.spring.service.storeService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    // page 받는 방식이 pageable 방식이 좀 더 유연하나, 일단 직관적으로 Integer 방식으로...
    Page<Review> getReviewList(Long storeId, Integer page);
    Page<Mission> getMissionList(Long storeId, Integer page);
}
