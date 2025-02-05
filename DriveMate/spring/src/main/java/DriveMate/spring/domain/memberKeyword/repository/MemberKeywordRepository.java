package DriveMate.spring.domain.memberKeyword.repository;

import DriveMate.spring.domain.member.entity.Member;
import DriveMate.spring.domain.memberKeyword.entity.MemberKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberKeywordRepository extends JpaRepository<MemberKeyword, Long> {
    void deleteAllByMember(Member member);

    List<MemberKeyword> findAllByMember(Member member);
}
