package lk.zerocode.sms_api.repository;

import lk.zerocode.sms_api.model.StudentExtraActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExtraActivityRepository extends JpaRepository<StudentExtraActivity, Long> {

    List<StudentExtraActivity> findAllByStudentIdAndGradeId(Long studentId, Long gradeId);
}
