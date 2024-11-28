package umc.spring.web.dto.storeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class AddDto{
        @NotBlank(message = "가게 이름은 필수 입력 사항입니다.")
        String name;

        @NotBlank(message = "주소는 필수 입력 사항입니다.")
        String address;

        @NotNull(message = "지역 ID는 필수 입력 사항입니다.")
        Long regionId;
    }

    @Getter
    public static class GetReviewDto{
        @NotNull(message = "storeId 는 필수 입력 사항입니다.")
        Long storeId;
    }
}
