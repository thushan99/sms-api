package lk.zerocode.sms_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grades")
public class Grade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gradeNumber;
    private String gradeName;
    private Double classFee;

    @ManyToOne
    private Teacher teacher;

}
