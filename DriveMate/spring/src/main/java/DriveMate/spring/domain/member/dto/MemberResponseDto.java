package DriveMate.spring.domain.member.dto;

import DriveMate.spring.domain.member.entity.MemberSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        private String occupation;

    }
}
