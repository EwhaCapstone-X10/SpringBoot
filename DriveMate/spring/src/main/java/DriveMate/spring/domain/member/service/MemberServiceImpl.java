package DriveMate.spring.domain.member.service;

import DriveMate.spring.common.exception.GeneralException;
import DriveMate.spring.common.status.ErrorStatus;
import DriveMate.spring.domain.keyword.entity.Keyword;
import DriveMate.spring.domain.keyword.repository.KeywordRepository;
import DriveMate.spring.domain.member.dto.MemberRequestDto;
import DriveMate.spring.domain.member.dto.MemberResponseDto;
import DriveMate.spring.domain.member.entity.Member;
import DriveMate.spring.domain.member.repository.MemberRepository;
import DriveMate.spring.domain.memberKeyword.entity.MemberKeyword;
import DriveMate.spring.domain.memberKeyword.repository.MemberKeywordRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final KeywordRepository keywordRepository;
    private final MemberKeywordRepository memberKeywordRepository;


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

    @Transactional
    @Override
    public MemberResponseDto.userInfodto userInfo(MemberRequestDto.userInfoDto request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        member.setName(request.getName());
        member.setAge(request.getAge());
        member.setSex(request.getSex());
        member.setMode(request.getMode());
        member.setOccupation(request.getOccupation());

        if (request.getInterests() != null) {
            memberKeywordRepository.deleteAllByMember(member);

            List<MemberKeyword> memberKeywords = request.getInterests().stream()
                    .map(keywordName -> {
                        // 키워드 이름으로 키워드 조회
                        Keyword keyword = keywordRepository.findByName(keywordName)
                                .orElseThrow(() -> new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND));

                        // MemberKeyword 생성
                        return MemberKeyword.builder()
                                .member(member)
                                .keyword(keyword)
                                .build();
                    })
                    .collect(Collectors.toList());

            // MemberKeyword 저장
            memberKeywordRepository.saveAll(memberKeywords);
        }

        List<String> keywordnames = memberKeywordRepository.findAllByMember(member).stream()
                        .map(memberKeyword -> memberKeyword.getKeyword().getName())
                                .collect(Collectors.toList());

        memberRepository.save(member);
        return MemberResponseDto.userInfodto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .sex(member.getSex())
                .mode(member.getMode())
                .occupation(member.getOccupation())
                .interests(keywordnames)
                .build();

    }

    @Override
    public MemberResponseDto.userInfodto getUserInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        List<String> keywordnames = memberKeywordRepository.findAllByMember(member).stream()
                .map(memberKeyword -> memberKeyword.getKeyword().getName())
                .collect(Collectors.toList());

        return MemberResponseDto.userInfodto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .sex(member.getSex())
                .mode(member.getMode())
                .occupation(member.getOccupation())
                .interests(keywordnames)
                .build();
    }

}
