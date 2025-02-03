package DriveMate.spring.domain.stretching.service;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.domain.stretching.dto.StretchingResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StretchingService {
    ResponseEntity<ApiResponse> getStretchingList(Pageable pageable);
}
