package DriveMate.spring.common.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "페이지를 찾을 수 없습니다."),

    // 입력값 검증 관련 에러
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "VALID401", "입력값이 올바르지 않습니다."),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 사용중인 이메일입니다."),

    CHAT_NOT_FOUND(HttpStatus.BAD_REQUEST, "CHAT4001", "해당 ID의 대화내역이 없습니다."),

    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY4001", "해당 카테고리가 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))  //공백이 아닌 문자열만 통과
                .orElse(this.getMessage());
    }


}
