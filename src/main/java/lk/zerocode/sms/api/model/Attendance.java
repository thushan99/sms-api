package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "attendances")
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Student student;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private String remarks;
}
