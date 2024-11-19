package umc.spring.web.dto.reviewDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.Member;
import umc.spring.validation.annotaion.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class AddReviewDto{

        @NotNull(message = "평점은 필수 입력 사항입니다.")
        private Float score;

        @NotBlank(message = "리뷰 내용은 필수 입력 사항입니다.")
        private String body;

        @ExistStore
        private Long storeId;
    }
}
