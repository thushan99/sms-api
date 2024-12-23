package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
