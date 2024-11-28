package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.storeService.StoreCommandService;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.validation.annotaion.CheckPage;
import umc.spring.validation.annotaion.ExistStore;
import umc.spring.web.dto.missionDto.MissionResponseDTO;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;
import umc.spring.web.dto.storeDto.StoreRequestDTO;
import umc.spring.web.dto.storeDto.StoreResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Validated
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.AddResultDTO> add(@RequestBody @Valid StoreRequestDTO.AddDto request){
        //StoreCommandService 통해 Service 추가
        Store store = storeCommandService.addStore(request);

        StoreResponseDTO.AddResultDTO response = StoreConverter.toAddResultDTO(store);

        return ApiResponse.onSuccess(response);
    }

    // 가게ID 별로 리뷰 목록 조회
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    // 특정 가게 미션 목록 조회
    @GetMapping("{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게들의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                               @CheckPage @RequestParam(name = "page") Integer page){
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page);

        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));
    }


}


