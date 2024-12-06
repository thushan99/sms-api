package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.StudentExtraActivity;
import lk.zerocode.School_management_system.model.StudentLeadership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLeadershipRepository extends JpaRepository<StudentLeadership, Long> {

    List<StudentLeadership> findAllByStudentIdAndGradeId(Long studentId, Long gradeId);
}
