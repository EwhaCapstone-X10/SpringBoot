package DriveMate.spring.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChatMessageId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ChatLog_id")
    private ChatLog chatLog;
}
