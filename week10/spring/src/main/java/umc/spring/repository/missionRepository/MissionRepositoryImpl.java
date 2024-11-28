package umc.spring.repository.missionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;
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
                .and(QMemberMission.memberMission.status.in(MissionStatus.COMPLETED, MissionStatus.IN_PROGRESS));

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
    public Page<Mission> findInProgressMissions(Long memberId, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QMemberMission.memberMission.member.id.eq(memberId))
                .and(QMemberMission.memberMission.status.in(MissionStatus.IN_PROGRESS));

        // 중복 코드이므로 메서드로 추출가능?
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

    @Override
    public Page<Mission> findAllByMemberMissionList(Long memberId, Pageable pageable) {
        QMission mission = QMission.mission;
        QMemberMission memberMission = QMemberMission.memberMission;

        // 조건 빌더 생성
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(memberMission.member.id.eq(memberId)); // 멤버 ID에 따른 필터링

        // 데이터 조회
        List<Mission> results = jpaQueryFactory.selectFrom(mission)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id)) // MemberMission과 조인
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 데이터 개수 조회
        long total = jpaQueryFactory.selectFrom(mission)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(predicate)
                .fetchCount();

        // Page 객체 반환
        return new PageImpl<>(results, pageable, total);
    }




    public Page<Mission> findMissionsByStore(Long storeId, Pageable pageable) {
        QMission mission = QMission.mission;
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(mission.store.id.eq(storeId));

        List<Mission> results = jpaQueryFactory.selectFrom(mission)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.selectFrom(mission)
                .where(predicate)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}

