package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {
    Page<Review> findReviewsByStore(Long storeId, Pageable pageable);
}

