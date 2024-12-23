package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.PreviousSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreviousSchoolRepository extends JpaRepository<PreviousSchool,Long> {

    List<PreviousSchool> findByStudentId(Long id);
}
