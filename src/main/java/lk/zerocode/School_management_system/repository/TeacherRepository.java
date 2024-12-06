package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
