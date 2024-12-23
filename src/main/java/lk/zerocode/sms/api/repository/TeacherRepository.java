package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
