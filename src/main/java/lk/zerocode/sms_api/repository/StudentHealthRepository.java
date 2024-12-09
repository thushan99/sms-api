package lk.zerocode.sms_api.repository;

import lk.zerocode.sms_api.model.StudentHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentHealthRepository extends JpaRepository<StudentHealth, Long> {


    List<StudentHealth> findByStudentId(Long id);
}
