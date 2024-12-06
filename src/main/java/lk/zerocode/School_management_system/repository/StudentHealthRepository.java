package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.StudentHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentHealthRepository extends JpaRepository<StudentHealth, Long> {


    List<StudentHealth> findByStudentId(Long id);
}
