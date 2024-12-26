package lk.zerocode.sms.api.service.impl;

import jakarta.transaction.Transactional;
import lk.zerocode.sms.api.controller.request.AttendanceRequest;
import lk.zerocode.sms.api.controller.request.AttendanceUpdateRequest;
import lk.zerocode.sms.api.controller.response.TotalAttendanceResponse;
import lk.zerocode.sms.api.exception.AttendanceNotFoundException;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Attendance;
import lk.zerocode.sms.api.model.CheckInOutState;
import lk.zerocode.sms.api.model.Grade;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.repository.AttendanceRepository;
import lk.zerocode.sms.api.repository.GradeRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Attendance create(Long studentId, AttendanceRequest attendanceRequest) throws StudentNotFoundException, GradeNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found" + studentId));
        Grade grade = gradeRepository.findById(attendanceRequest.getGradeId()).orElseThrow(() -> new GradeNotFoundException("Grade Not Found" + attendanceRequest.getGradeId()));

        LocalDate currentDate = LocalDate.now();

        Optional<Attendance> existingAttendance = attendanceRepository.findByStudentIdAndDate(studentId, currentDate);
        if (existingAttendance.isPresent() && attendanceRequest.getCheckInOut() == CheckInOutState.IN) {
            throw new IllegalArgumentException("Attendance for 'IN' state already exists for today.");
        }

        if (existingAttendance.isEmpty() && attendanceRequest.getCheckInOut() == CheckInOutState.OUT) {
            throw new IllegalArgumentException("Cannot mark 'OUT' state without a prior 'IN' record.");
        }

        Attendance attendance = Attendance.builder().student(student).grade(grade).status(attendanceRequest.getStatus()).checkInOut(attendanceRequest.getCheckInOut()).date(currentDate).time(LocalTime.now()).build();
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> findAll(Pageable pageable) {

        Page<Attendance> attendances = attendanceRepository.findAllBy(pageable);
        return attendances.getContent();
    }

    @Override
    public List<Attendance> findByGradeId(Long gradeId) {

        return attendanceRepository.findByGradeId(gradeId);
    }

    @Override
    public Attendance getById(Long attendanceId) {

        return attendanceRepository.findById(attendanceId).orElseThrow(() -> new AttendanceNotFoundException("Attendance Not Found" + attendanceId));
    }

    @Override
    public List<TotalAttendanceResponse> getTotalAttendanceList(Long studentId) {
        return List.of();
    }

    @Override
    public TotalAttendanceResponse getTotalAttendance(Long studentId) {
        return null;
    }

    @Override
    public Attendance update(Long attendanceId, AttendanceUpdateRequest attendanceUpdateRequest) {

        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new AttendanceNotFoundException("Student Not Found" + attendanceId));
        attendance.setStatus(attendanceUpdateRequest.getStatus());
        attendance.setRemarks(attendanceUpdateRequest.getRemarks());
        return attendanceRepository.save(attendance);
    }

    @Override
    public void delete(Long attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new AttendanceNotFoundException("Student Not Found" + attendanceId));
        attendanceRepository.delete(attendance);
    }
}
