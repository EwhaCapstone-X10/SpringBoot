package DriveMate.spring.domain.memberKeyword.entity;

import DriveMate.spring.domain.keyword.entity.Keyword;
import DriveMate.spring.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MemberKeyword", indexes = {
        @Index(name = "idx_member_keyword_member_id", columnList = "member_id"),
        @Index(name = "idx_member_keyword_keyword_id", columnList = "keyword_id")
})
public class MemberKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_keyword_id")
    private Long memberKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @Builder
    public MemberKeyword(Member member, Keyword keyword) {
        this.member = member;
        this.keyword = keyword;
    }
}
