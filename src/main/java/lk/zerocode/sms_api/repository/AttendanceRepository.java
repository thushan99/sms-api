package lk.zerocode.sms_api.repository;

import lk.zerocode.sms_api.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
