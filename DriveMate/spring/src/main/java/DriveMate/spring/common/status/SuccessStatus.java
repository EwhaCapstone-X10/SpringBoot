package DriveMate.spring.common.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    _OK(HttpStatus.OK, "COMMON200", "SUCCESS!");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
