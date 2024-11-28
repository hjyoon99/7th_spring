package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.web.dto.memberDto.MemberRequestDTO;
import umc.spring.web.dto.memberDto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = convertGender(request.getGender());

        return Member.builder()
                .address(request.getAddress())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .status(MemberStatus.ACTIVE)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    private static Gender convertGender(Integer genderCode) {
        if (genderCode == null) {
            return Gender.NONE; // 기본값 설정
        }

        switch (genderCode) {
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            case 3:
                return Gender.NONE;
            default:
                throw new IllegalArgumentException("유효하지 않은 성별 코드입니다: " + genderCode);
        }
    }
}
