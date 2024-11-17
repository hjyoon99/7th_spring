package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.reviewService.ReviewCommandService;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDto> addReview(@RequestBody @Valid ReviewRequestDTO.AddReviewDto request){
        Review review = reviewCommandService.addReview(request);

        ReviewResponseDTO.AddReviewResultDto response = ReviewConverter.toAddReviewResultDTO(review);

        return ApiResponse.onSuccess(response);
    }
}
