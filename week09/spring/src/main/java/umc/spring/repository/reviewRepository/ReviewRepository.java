package umc.spring.repository.reviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom{
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findAllByStoreAndMember(Store store, Member member, Pageable pageable);
}
