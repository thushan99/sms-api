package lk.zerocode.sms.api.repository;

import lk.zerocode.sms.api.model.Attendance;
import lk.zerocode.sms.api.projection.StudentAttendanceMonthlyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByGradeId(Long id);

    Optional<Attendance> findByStudentIdAndDate(Long studentId, LocalDate date);

    Page<Attendance> findAllBy(Pageable pageable);

    @Query("SELECT " +
            "FUNCTION('DATE_FORMAT', a.date, '%Y-%m') AS yearMonth, " +
            "SUM(CASE WHEN a.status = 'present' THEN 1 ELSE 0 END) AS presentCount, " +
            "SUM(CASE WHEN a.status = 'late' THEN 1 ELSE 0 END) AS lateCount, " +
            "SUM(CASE WHEN a.status = 'absent' THEN 1 ELSE 0 END) AS absentCount " +
            "FROM Attendance a " +
            "WHERE a.student.id = :studentId " +
            "GROUP BY FUNCTION('DATE_FORMAT', a.date, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', a.date, '%Y-%m')")
    List<StudentAttendanceMonthlyProjection> findMonthlyAttendanceCountsByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT " +
            "SUM(CASE WHEN a.status = 'present' THEN 1 ELSE 0 END) AS presentCount, " +
            "SUM(CASE WHEN a.status = 'late' THEN 1 ELSE 0 END) AS lateCount, " +
            "SUM(CASE WHEN a.status = 'absent' THEN 1 ELSE 0 END) AS absentCount " +
            "FROM Attendance a " +
            "WHERE a.student.id = :studentId AND " +
            "      a.date >= :startOfMonth AND a.date <= :endOfMonth")
    StudentAttendanceMonthlyProjection findCurrentMonthAttendanceCountsByStudentId(
            @Param("studentId") Long studentId,
            @Param("startOfMonth") LocalDate startOfMonth,
            @Param("endOfMonth") LocalDate endOfMonth);
}
