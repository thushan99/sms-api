package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
