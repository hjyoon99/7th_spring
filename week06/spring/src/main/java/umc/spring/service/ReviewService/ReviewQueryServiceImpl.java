package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Page<Review> findReviewsByStore(Long storeId, Pageable pageable) {
        Page<Review> filteredReviews = reviewRepository.findReviewsByStore(storeId, pageable);

        // 페이징된 리뷰 출력
        filteredReviews.forEach(review -> System.out.println("Review: " + review));

        return filteredReviews;
    }
}

