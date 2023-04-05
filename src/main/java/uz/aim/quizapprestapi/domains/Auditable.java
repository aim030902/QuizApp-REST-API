package uz.aim.quizapprestapi.domains;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import uz.aim.quizapprestapi.domains.entity.auth.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 18:17
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp updatedAt;
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private User createdBy;
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;

    private boolean deleted;
}
