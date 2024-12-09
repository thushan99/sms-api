package lk.zerocode.sms_api.repository;

import lk.zerocode.sms_api.model.StudentDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {
}
