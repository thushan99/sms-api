package lk.zerocode.sms_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "student_previous_schools")

public class PreviousSchool extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String schoolName;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Student student;

}

