package umc.spring.service.reviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;

import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> findReview(Long id);
    Page<Review> findReviewsByStore(Long storeId, Pageable pageable);
}

