package lk.zerocode.sms_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student_documents")

public class StudentDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String documentPath;

    @Enumerated(EnumType.STRING)
    private DocType docType;

    private String docFormat;

    @ManyToOne
    private Student student;

}
