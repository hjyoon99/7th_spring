package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDTO.AddReviewDto request);
}
