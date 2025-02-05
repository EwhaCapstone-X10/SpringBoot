package DriveMate.spring.domain.member.dto;

import DriveMate.spring.domain.member.entity.MemberSex;
import DriveMate.spring.domain.member.entity.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class signupResultdto {
        private Long memberId;
        private LocalDateTime signupTime;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class userInfodto {
        private Long memberId;
        private String name;
        private Integer age;
        private MemberSex sex;
        private Mode mode;
        private String occupation;
        private List<String> keywords;
    }
}
