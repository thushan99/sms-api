package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.StudentLeadership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLeadershipRepository extends JpaRepository<StudentLeadership, Long> {

    List<StudentLeadership> findAllByStudentIdAndGradeId(Long studentId, Long gradeId);
}
