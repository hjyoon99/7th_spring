package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddReviewResultDto toAddReviewResultDTO(Review review) {
        return ReviewResponseDTO.AddReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.AddReviewDto request, Store store){
        return Review.builder()
                .score(request.getScore())
                .content(request.getBody())
                .store(store) //store 엔티티 설정
                .build();
    }
}
