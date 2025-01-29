package DriveMate.spring.domain.chat.entity;

import DriveMate.spring.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @Lob
    private String content;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

}
