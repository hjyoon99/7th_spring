package umc.spring.repository.missionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    Page<Mission> findAllByStore(Store store, Pageable pageable);

}
