package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByGradeId(Long id);

    Optional<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);

    Page<Attendance> findAllBy(Pageable pageable);
}
