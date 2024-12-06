package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.Grade;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.model.StudentExtraActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExtraActivityRepository extends JpaRepository<StudentExtraActivity, Long> {

    List<StudentExtraActivity> findAllByStudentIdAndGradeId(Long studentId, Long gradeId);
}
