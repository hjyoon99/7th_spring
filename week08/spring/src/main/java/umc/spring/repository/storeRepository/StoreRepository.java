package umc.spring.repository.storeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
