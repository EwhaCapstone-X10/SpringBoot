package DriveMate.spring.common.response;

import DriveMate.spring.common.status.ErrorStatus;
import DriveMate.spring.common.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "pageInfo", "result"})
public class ApiResponse {

    private final boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final PageInfo pageInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object result;

    // 성공한 경우 응답
    public static ResponseEntity<ApiResponse> onSuccess(SuccessStatus status, PageInfo pageInfo, Object result) {
        return new ResponseEntity<>(
                new ApiResponse(true, status.getCode(), status.getMessage(), pageInfo, result),
                status.getStatus()
        );
    }

    public static ResponseEntity<ApiResponse> onSuccess(SuccessStatus status) {
        return onSuccess(status, null, null);
    }

    public static ResponseEntity<ApiResponse> onSuccess(SuccessStatus status, Object result) {
        return onSuccess(status, null, result);
    }

    // 실패한 경우 응답 생성
    public static ResponseEntity<ApiResponse> onFailure(ErrorStatus error) {
        return new ResponseEntity<>(
                new ApiResponse(false, error.getCode(), error.getMessage(), null, null), error.getStatus());
    }

    public static ResponseEntity<ApiResponse> onFailure(ErrorStatus error, String message) {
        return new ResponseEntity<>(new ApiResponse(false, error.getCode(), error.getMessage(message), null, null), error.getStatus());
    }
}
