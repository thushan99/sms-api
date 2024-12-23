package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "student_extra_activitys")

public class StudentExtraActivity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String extraActivity;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Grade grade;

}
