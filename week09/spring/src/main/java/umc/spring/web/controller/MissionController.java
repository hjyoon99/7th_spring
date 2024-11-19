package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConvertor;
import umc.spring.domain.Mission;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.web.dto.missionDto.MissionRequestDTO;
import umc.spring.web.dto.missionDto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.AddMissionResultDto> addMission(@RequestBody @Valid MissionRequestDTO.AddMissionDto request){
        Mission mission = missionCommandService.addMission(request);
        MissionResponseDTO.AddMissionResultDto response = MissionConvertor.toaddMissionResultDto(mission);
        return ApiResponse.onSuccess(response);
    }
}
