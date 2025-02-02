package DriveMate.spring.domain.member.dto;

import DriveMate.spring.domain.member.entity.MemberSex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberRequestDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class signupDto {
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotNull(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class userInfoDto {
        private Long memberId;
        private String name;
        private int age;
        private MemberSex sex;
        private String occupation;
    }
}
