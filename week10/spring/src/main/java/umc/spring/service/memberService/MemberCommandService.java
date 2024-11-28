package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.memberDto.MemberRequestDTO;
import umc.spring.web.dto.reviewDto.ReviewRequestDTO;
import umc.spring.web.dto.storeDto.StoreRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);

}
