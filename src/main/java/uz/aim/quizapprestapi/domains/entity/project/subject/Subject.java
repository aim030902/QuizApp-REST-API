package uz.aim.quizapprestapi.domains.entity.project.subject;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:00
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "subject")
@EntityListeners(value = AuditingEntityListener.class)
public class Subject extends Auditable {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    @Builder(builderMethodName = "childBuilder")
    public Subject(Long id, Timestamp createdAt, Timestamp updatedAt, User createdBy, User updatedBy, boolean deleted, String name, String description, List<Question> questions) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.name = name;
        this.description = description;
        this.questions = questions;
    }
}
