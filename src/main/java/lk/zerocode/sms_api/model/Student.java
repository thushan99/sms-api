package lk.zerocode.sms_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileImageUrl;
    private String name;
    private String registrationNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;
    private LocalDate admissionDate;
    private String religion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Draft draft;

    @ManyToOne
    private Grade grade;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_parent",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private List<Parent> parents = new ArrayList<>();

}
