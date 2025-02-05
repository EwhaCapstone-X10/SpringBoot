package DriveMate.spring.domain.stretching.repository;

import DriveMate.spring.domain.stretching.entity.Stretching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StretchingRepository extends JpaRepository<Stretching, Long> {
}
