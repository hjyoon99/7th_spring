package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.storeService.StoreCommandService;
import umc.spring.web.dto.storeDto.StoreRequestDTO;
import umc.spring.web.dto.storeDto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.AddResultDTO> add(@RequestBody @Valid StoreRequestDTO.AddDto request){
        //StoreCommandService 통해 Service 추가
        Store store = storeCommandService.addStore(request);

        StoreResponseDTO.AddResultDTO response = StoreConverter.toAddResultDTO(store);

        return ApiResponse.onSuccess(response);
    }
}
