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
import umc.spring.converter.MemberConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.service.memberService.MemberCommandService;
import umc.spring.service.memberService.MemberQueryService;
import umc.spring.service.memberService.MemberQueryServiceImpl;
import umc.spring.service.reviewService.ReviewQueryService;
import umc.spring.service.reviewService.ReviewQueryServiceImpl;
import umc.spring.validation.annotaion.CheckPage;
import umc.spring.validation.annotaion.ExistStore;
import umc.spring.web.dto.memberDto.MemberRequestDTO;
import umc.spring.web.dto.memberDto.MemberResponseDTO;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@Valid @RequestBody MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/{storeId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @PathVariable(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<ReviewResponseDTO.ReviewPreViewDTO> reviewPage = memberQueryService.getReviewsByMemberAndStore(storeId, memberId, page);

        // Page<ReviewPreViewDTO>를 ReviewPreViewListDTO로 변환
        ReviewResponseDTO.ReviewPreViewListDTO responseDTO = ReviewConverter.reviewPreViewListDTO(reviewPage);

        return ApiResponse.onSuccess(responseDTO);
    }
}