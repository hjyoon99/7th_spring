package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.memberRepository.MemberRepository;
import umc.spring.repository.reviewRepository.ReviewRepository;
import umc.spring.repository.storeRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;


    @Override
    public Page<Review> getReviewsByMemberAndStore(Long storeId, Long memberId, Integer page) {

        Store store = storeRepository.findById(storeId).get();
        Member member = memberRepository.findById(memberId).get();

        Page<Review> memberPage = reviewRepository.findAllByStoreAndMember(store, member, PageRequest.of(page, 10));

        return memberPage;
    }
}
