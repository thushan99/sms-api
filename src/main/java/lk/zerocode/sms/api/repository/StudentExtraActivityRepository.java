package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.StudentExtraActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExtraActivityRepository extends JpaRepository<StudentExtraActivity, Long> {

    List<StudentExtraActivity> findAllByStudentIdAndGradeId(Long studentId, Long gradeId);
}
