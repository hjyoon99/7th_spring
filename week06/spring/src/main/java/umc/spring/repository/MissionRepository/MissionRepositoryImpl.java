package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.QRegion;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Mission> findCompletedAndOngoingMissions(Long memberId, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QMemberMission.memberMission.member.id.eq(memberId))
                .and(QMemberMission.memberMission.status.in(MissionStatus.COMPLETED, MissionStatus.CHALLENGING));

        List<Mission> results = jpaQueryFactory.selectFrom(QMission.mission)
                .join(QMission.mission.memberMissionList, QMemberMission.memberMission)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = jpaQueryFactory.selectFrom(QMission.mission)
                .join(QMission.mission.memberMissionList, QMemberMission.memberMission)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public Page<Mission> findMissionsByRegion(String regionName, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QMission.mission.store.region.name.eq(regionName))
                .and(QMemberMission.memberMission.status.eq(MissionStatus.AVAILABLE)); // 도전 가능한 미션만 조회

        List<Mission> results = jpaQueryFactory.selectFrom(QMission.mission)
                .join(QMission.mission.store, QStore.store)
                .join(QStore.store.region, QRegion.region)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(QMission.mission)
                .join(QMission.mission.store, QStore.store)
                .join(QStore.store.region, QRegion.region)
                .join(QMission.mission.memberMissionList, QMemberMission.memberMission) // MemberMission과 조인
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}

