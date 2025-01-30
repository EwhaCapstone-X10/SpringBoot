package DriveMate.spring.domain.member.service;

import DriveMate.spring.domain.member.dto.MemberRequestDto;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import jakarta.validation.Valid;

public interface MemberService {
    MemberResponseDto.signupResultdto signupMember(MemberRequestDto.signupDto request);
}
