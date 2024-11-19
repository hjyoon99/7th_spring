package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.web.dto.reviewDto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
