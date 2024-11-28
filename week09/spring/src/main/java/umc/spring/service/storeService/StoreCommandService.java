package umc.spring.service.storeService;

import umc.spring.domain.Store;
import umc.spring.web.dto.storeDto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddDto request);
}
