package umc.spring.repository.reviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.domain.QReview;
import umc.spring.domain.QStore;
import umc.spring.domain.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findReviewsByStore(Long storeId, Pageable pageable) {
        QReview review = QReview.review;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(review.store.id.eq(storeId)); // 특정 가게에 대한 조건


        List<Review> results = jpaQueryFactory.selectFrom(review)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(review)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public Page<Review> findReviewsByMemberAndStore(Long memberId, Long storeId, Pageable pageable) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        QMember member = QMember.member;

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(review.store.id.eq(storeId)); // 특정 가게에 대한 조건
        predicate.and(review.member.id.eq(memberId)); // 특정 멤버 조건

        // FETCH JOIN으로 Store와 Member 데이터를 함께 로드
        List<Review> results = jpaQueryFactory.selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.member, member).fetchJoin()
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(review)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}
