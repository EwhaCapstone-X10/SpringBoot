package DriveMate.spring.domain.stretching.service;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.common.response.PageInfo;
import DriveMate.spring.common.status.SuccessStatus;
import DriveMate.spring.domain.stretching.dto.StretchingResponseDto;
import DriveMate.spring.domain.stretching.entity.Stretching;
import DriveMate.spring.domain.stretching.repository.StretchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StretchingServiceImpl implements StretchingService{

    private final StretchingRepository stretchingRepository;

    @Override
    public ResponseEntity<ApiResponse> getStretchingList(Pageable pageable) {
        Page<Stretching> stretchings = stretchingRepository.findAll(pageable);

        List<StretchingResponseDto.StretchingInfodto> stretchingInfodtoList = stretchings.getContent().stream()
                .map(stretching -> StretchingResponseDto.StretchingInfodto.builder()
                        .stretchingId(stretching.getStretchingId())
                        .isVideo(stretching.isVideo())
                        .src(stretching.getSrc())
                        .source(stretching.getSource())
                        .title(stretching.getTitle())
                        .description(stretching.getDescription())
                        .build())
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(
                stretchings.getNumber(),
                stretchings.getSize(),
                stretchings.hasNext(),
                stretchings.getTotalElements(),
                stretchings.getTotalPages()
        );

        return ApiResponse.onSuccess(SuccessStatus._OK, pageInfo, stretchingInfodtoList);
    }
}
