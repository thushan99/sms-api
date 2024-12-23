package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "student_healths")

public class StudentHealth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "`condition`")
    private String condition;

    private LocalDate diagnosisDate;

    @ManyToOne
    private Student student;

}
