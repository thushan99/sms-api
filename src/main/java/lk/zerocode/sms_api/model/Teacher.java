package lk.zerocode.sms_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "teachers")
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teacherName;
    private String nic;
    private String address;
    private Integer mobile;
    private LocalDate date;
    private String email;
    private String subject;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Lob
    private String education;

    @Lob
    private String experience;

}
