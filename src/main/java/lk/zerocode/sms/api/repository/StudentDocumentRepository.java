package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.StudentDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {
}
