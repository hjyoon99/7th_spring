package umc.spring.service.memberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.foodRepository.FoodCategoryRepository;
import umc.spring.repository.memberRepository.MemberRepository;
import umc.spring.repository.reviewRepository.ReviewRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.memberDto.MemberRequestDTO;
import umc.spring.web.dto.storeDto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    /*@Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }*/

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        // 로그 추가
        System.out.println("회원가입 요청: " + request);

        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        System.out.println("생성된 Member 엔티티: " + newMember);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category)
                            .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        System.out.println("선호 음식 카테고리: " + foodCategoryList);

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));

        System.out.println("생성된 MemberPrefer 리스트: " + memberPreferList);

        Member savedMember = memberRepository.save(newMember);
        System.out.println("저장된 Member: " + savedMember);

        return savedMember;
    }

}

