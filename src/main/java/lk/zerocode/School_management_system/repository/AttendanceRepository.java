package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
