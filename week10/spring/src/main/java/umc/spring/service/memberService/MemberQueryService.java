package umc.spring.service.memberService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;

public interface MemberQueryService {
    Page<ReviewResponseDTO.ReviewPreViewDTO> getReviewsByMemberAndStore(Long storeId, Long memberId, Integer page);
}
