package lk.zerocode.sms_api.repository;

import lk.zerocode.sms_api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
