package DriveMate.spring.domain.chat.dto;

import DriveMate.spring.domain.chat.entity.Role;
import DriveMate.spring.domain.member.entity.MemberSex;
import DriveMate.spring.domain.member.entity.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ChatRequestDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageDto {
        private Role role;
        private String chat;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatLogDto {
        private Long memberId;
        private LocalDateTime date;
        private String summary;
        private String keywords;
        private List<ChatMessageDto> chatting;
    }
}
