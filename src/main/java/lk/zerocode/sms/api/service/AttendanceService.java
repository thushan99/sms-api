package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.AttendanceRequest;
import lk.zerocode.sms.api.controller.request.AttendanceUpdateRequest;
import lk.zerocode.sms.api.controller.response.TotalAttendanceResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceService {

    Attendance create(Long studentId, AttendanceRequest attendanceRequest) throws StudentNotFoundException, GradeNotFoundException;

    List<Attendance> findAll(Pageable pageable);

    List<Attendance> findByGradeId(Long gradeId);

    Attendance getById(Long attendanceId);

    List<TotalAttendanceResponse> getTotalAttendanceList(Long studentId);

    TotalAttendanceResponse getTotalAttendance(Long studentId);

    Attendance update(Long attendanceId, AttendanceUpdateRequest attendanceUpdateRequest);

    void delete(Long attendanceId);
}
