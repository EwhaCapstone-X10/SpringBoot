package DriveMate.spring.domain.member.controller;

import DriveMate.spring.common.response.ApiResponse;
import DriveMate.spring.common.status.SuccessStatus;
import DriveMate.spring.domain.member.dto.MemberRequestDto;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import DriveMate.spring.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestContoller {

    private final MemberService memberService;

    // 기본 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(
            @RequestBody @Valid MemberRequestDto.signupDto request
    ) {
        MemberResponseDto.signupResultdto response = memberService.signupMember(request);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

    // 개인정보 저장
    @PostMapping("")
    public ResponseEntity<ApiResponse> userInfo(
            @RequestBody @Valid MemberRequestDto.userInfoDto request
    ) {
        MemberResponseDto.userInfodto response = memberService.userInfo(request);
        return ApiResponse.onSuccess(SuccessStatus._OK, response);
    }

}
