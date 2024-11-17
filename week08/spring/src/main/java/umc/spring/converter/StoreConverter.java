package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.storeDto.StoreRequestDTO;
import umc.spring.web.dto.storeDto.StoreResponseDTO;

public class StoreConverter {
    public static StoreResponseDTO.AddResultDTO toAddResultDTO(Store store) {
        return StoreResponseDTO.AddResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    //StoreRequestDTO.AddDto를 Store 엔티티로 변환

    public static Store toStore(StoreRequestDTO.AddDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region) // Region 객체 설정
                .build();
    }
}