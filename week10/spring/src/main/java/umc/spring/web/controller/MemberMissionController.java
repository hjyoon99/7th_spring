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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.missionRepository.MissionRepository;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.service.missionService.MissionQueryService;
import umc.spring.service.missionService.MissionQueryServiceImpl;
import umc.spring.validation.annotaion.CheckPage;
import umc.spring.validation.annotaion.ExistStore;
import umc.spring.web.dto.memberMissionDto.MemberMissionRequestDTO;
import umc.spring.web.dto.memberMissionDto.MemberMissionResponseDTO;
import umc.spring.web.dto.missionDto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {
    
    private final MemberMissionCommandService memberMissionCommandService;
    private final MissionQueryService missionQueryService;
    private final MissionRepository missionRepository;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeResponseDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.AddChallengeMissionDto request) {
        MemberMission memberMission = memberMissionCommandService.addChallengeMission(request);
        
        MemberMissionResponseDTO.ChallengeResponseDTO response = MemberMissionConverter.toChallengeResponseDTO(memberMission);

        return ApiResponse.onSuccess(response);

    }
    // status가 IN_PROGRESS인 것들만 가져오기.
    @GetMapping("/{memberId}/missions/in-progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMemberMissionList(@ExistStore @PathVariable(name = "memberId") Long memberId,
                                                                                @CheckPage @RequestParam(name = "page") Integer page,
                                                                                      @RequestParam(name = "size") Integer size) {
        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);

        //** 되긴 되는데, repo가 아니라 service에서 받아올 수 있도록**
        Page<Mission> missionList = missionQueryService.findOngoingMissions(memberId, pageable);

        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));
    }

    //진행중인 미션을 진행 완료로 상태를 변경하므로, PATCH로 들어간다
    // ** {missionId} 안받고, 그냥 IN_PROGRESS인 것들만 COMPLETED로 바꿔주기
    @PatchMapping("{memberId}/missions/{missionId}/complete")
    @Operation(summary = "진행 중인 미션을 완료로 변경하는 API",
            description = "특정 사용자의 진행 중인 미션을 완료로 상태를 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOT_FOUND", description = "해당 미션을 찾을 수 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BAD_REQUEST", description = "요청이 잘못되었습니다.")
    })
    public ApiResponse<MemberMissionResponseDTO.ChallengeResponseDTO> completeMission(@RequestBody @Valid MemberMissionRequestDTO.CompleteMissionDto request) {

        MemberMission updateMemberMission = memberMissionCommandService.patchCompleteMission(request);

        MemberMissionResponseDTO.ChallengeResponseDTO response = MemberMissionConverter.toChallengeResponseDTO(updateMemberMission);
        return ApiResponse.onSuccess(response);
    }
}
