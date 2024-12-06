package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.StudentDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {
}
