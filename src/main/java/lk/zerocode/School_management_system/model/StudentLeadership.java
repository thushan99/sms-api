package lk.zerocode.School_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "student_leaderships")

public class StudentLeadership extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String leadership;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Grade grade;

}
