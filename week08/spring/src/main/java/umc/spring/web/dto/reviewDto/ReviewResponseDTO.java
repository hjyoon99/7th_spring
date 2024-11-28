package umc.spring.web.dto.reviewDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddReviewResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }
}
