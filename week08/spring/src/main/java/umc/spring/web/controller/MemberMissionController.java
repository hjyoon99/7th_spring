package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.memberMissionDto.MemberMissionRequestDTO;
import umc.spring.web.dto.memberMissionDto.MemberMissionResponseDTO;
import umc.spring.web.dto.missionDto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MemberMissionController {
    
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeResponseDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.AddChallengeMissionDto request) {
        MemberMission memberMission = memberMissionCommandService.addChallengeMission(request);
        
        MemberMissionResponseDTO.ChallengeResponseDTO response = MemberMissionConverter.toChallengeResponseDTO(memberMission);

        return ApiResponse.onSuccess(response);

    }
}
