package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.PreviousSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreviousSchoolRepository extends JpaRepository<PreviousSchool,Long> {

    List<PreviousSchool> findByStudentId(Long id);
}
