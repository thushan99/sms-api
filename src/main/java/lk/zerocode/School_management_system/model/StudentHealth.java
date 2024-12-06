package lk.zerocode.School_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
