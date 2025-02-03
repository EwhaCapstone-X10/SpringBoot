package DriveMate.spring.domain.stretching.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stretching")
public class Stretching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stretchingId;

    private boolean isVideo;

    private String videoUrl;

    private String title;

    private String description;
}
