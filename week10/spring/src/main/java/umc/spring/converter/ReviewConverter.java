package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;
import umc.spring.web.dto.storeDto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

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
    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .ownerNickname(review.getMember().getName()) // 일단 로그인이 구현 안되어있으므로, memberId = null..인데 하드코딩으로 db 넣어줌
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }
    /*public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.getContent().stream()
                .map(StoreConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }*/
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<ReviewResponseDTO.ReviewPreViewDTO> reviewPage) {
        // Page의 내용을 List로 변환
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.getContent();

        // ReviewPreViewListDTO 생성
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewPage.isLast())
                .isFirst(reviewPage.isFirst())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
