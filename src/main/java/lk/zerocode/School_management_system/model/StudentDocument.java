package lk.zerocode.School_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
