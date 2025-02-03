package DriveMate.spring.domain.stretching.service;

import DriveMate.spring.domain.stretching.repository.StretchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StretchingServiceImpl implements StretchingService{

    private final StretchingRepository stretchingRepository;
}
