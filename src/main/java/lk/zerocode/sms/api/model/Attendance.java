package lk.zerocode.sms.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "attendances")
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

}
