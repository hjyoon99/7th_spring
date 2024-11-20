package umc.spring.service.memberService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;

public interface MemberQueryService {
    Page<Review> getReviewsByMemberAndStore(Long storeId,Long memberId, Integer page);
}
