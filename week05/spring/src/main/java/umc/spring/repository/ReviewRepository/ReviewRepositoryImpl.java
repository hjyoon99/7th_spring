package umc.spring.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QReview;
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
}
