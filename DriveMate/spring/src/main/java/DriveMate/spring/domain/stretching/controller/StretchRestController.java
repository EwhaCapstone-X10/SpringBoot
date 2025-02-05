package DriveMate.spring.domain.stretching.controller;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.common.status.SuccessStatus;
import DriveMate.spring.domain.stretching.dto.StretchingResponseDto;
import DriveMate.spring.domain.stretching.service.StretchingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stretching")
@RequiredArgsConstructor
public class StretchRestController {

    private final StretchingService stretchingService;

    // 스트레칭 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse> getStretching(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return stretchingService.getStretchingList(pageable);
    }
}
