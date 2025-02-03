package DriveMate.spring.domain.member.service;

import DriveMate.spring.common.exception.GeneralException;
import DriveMate.spring.common.status.ErrorStatus;
import DriveMate.spring.domain.member.dto.MemberRequestDto;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import DriveMate.spring.domain.member.entity.Member;
import DriveMate.spring.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public MemberResponseDto.signupResultdto signupMember(MemberRequestDto.@Valid signupDto request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(ErrorStatus.EMAIL_ALREADY_EXIST);
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .isDeleted(false)
                .build();

        memberRepository.save(member);

        return MemberResponseDto.signupResultdto.builder()
                .memberId(member.getMemberId())
                .signupTime(member.getCreatedAt())
                .build();
    }

    @Override
    public MemberResponseDto.userInfodto userInfo(MemberRequestDto.userInfoDto request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        member.setName(request.getName());
        member.setAge(request.getAge());
        member.setSex(request.getSex());
        member.setOccupation(request.getOccupation());

        memberRepository.save(member);
        return MemberResponseDto.userInfodto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .sex(member.getSex())
                .occupation(member.getOccupation())
                .build();

    }

    @Override
    public MemberResponseDto.userInfodto getUserInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return MemberResponseDto.userInfodto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .sex(member.getSex())
                .occupation(member.getOccupation())
                .build();
    }
}
