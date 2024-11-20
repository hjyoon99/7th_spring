package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // Food Category 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4004", "해당 음식 카테고리를 찾을 수 없습니다."),

    //Store 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4004", "STORE_NOT_FOUND"),

    //mission 관련 에러
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.ALREADY_REPORTED, "MISSION4004","MISSION4004 이미 도전 중인 미션입니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "존재하지 않는 미션 ID입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.ALREADY_REPORTED, "MISSION4002", "MISSION4002 이미 완료한 미션입니다."),

    //page 관련 에러
    PAGE_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "PAGE4004", "PAGE_VALIDATION_ERROR"),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
