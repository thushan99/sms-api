package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.StudentHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentHealthRepository extends JpaRepository<StudentHealth, Long> {

    List<StudentHealth> findByStudentId(Long id);
}
