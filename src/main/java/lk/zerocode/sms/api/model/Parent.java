package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "parents")
public class Parent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GuardianType guardianType;

    private String name;
    private String address;
    private String nic;
    private String occupation;
    private String mobile;
    private String religion;
    private Double monthlyAvgIncome;

    @ManyToMany(mappedBy = "parents")
    private List<Student> students;

}
