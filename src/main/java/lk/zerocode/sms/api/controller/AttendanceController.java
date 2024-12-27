package lk.zerocode.sms.api.controller;

import lk.zerocode.sms.api.controller.request.AttendanceRequest;
import lk.zerocode.sms.api.controller.request.AttendanceUpdateRequest;
import lk.zerocode.sms.api.controller.response.AttendanceResponse;
import lk.zerocode.sms.api.controller.response.TotalAttendanceResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Attendance;
import lk.zerocode.sms.api.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceController {

    private AttendanceService attendanceService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/attendances/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> create(@PathVariable("student-id") Long studentId, @RequestBody AttendanceRequest attendanceRequest) throws GradeNotFoundException, StudentNotFoundException {

        Attendance createdAttendance = attendanceService.create(studentId, attendanceRequest);
        AttendanceResponse attendanceResponse = modelMapper.map(createdAttendance, AttendanceResponse.class);
        return new ResponseEntity<>(attendanceResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/attendances", headers = "X-Api-Version=v1")
    public ResponseEntity<List<AttendanceResponse>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        Sort sort = Sort.by(Sort.Order.asc("date"));
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        List<Attendance> attendances = attendanceService.findAll(pageRequest);
        List<AttendanceResponse> attendanceResponses = attendances.stream().map(attendance -> modelMapper.map(attendance, AttendanceResponse.class)).toList();
        return new ResponseEntity<>(attendanceResponses, HttpStatus.OK);
    }

    @GetMapping(value = "grades/{grade-id}/attendances", headers = "X-Api-Version=v1")
    public ResponseEntity<?> findByGrade(@PathVariable("grade-id") Long gradeId) {

        List<Attendance> attendances = attendanceService.findByGradeId(gradeId);
        List<AttendanceResponse> attendanceResponses = attendances.stream().map(attendance -> modelMapper.map(attendance, AttendanceResponse.class)).toList();
        return new ResponseEntity<>(attendanceResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/attendances/{attendance-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> findById(@PathVariable("attendance-id") Long attendanceId) {

        Attendance attendance = attendanceService.getById(attendanceId);
        AttendanceResponse attendanceResponse = modelMapper.map(attendance, AttendanceResponse.class);
        return new ResponseEntity<>(attendanceResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{studentId}/attendances", headers = "X-Api-Version=v1")
    public ResponseEntity<List<TotalAttendanceResponse>> getMonthlyAttendanceByStudentId(@PathVariable("studentId") Long studentId) {

        List<TotalAttendanceResponse> response = attendanceService.getTotalAttendanceList(studentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{studentId}/current-attendances", headers = "X-Api-Version=v1")
    public ResponseEntity<TotalAttendanceResponse> getCurrentMonthlyAttendanceByStudentId (@PathVariable("studentId") Long studentId) {
        TotalAttendanceResponse response =attendanceService.getTotalAttendance(studentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/attendances/{attendance-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> updateById(@PathVariable("attendance-id") Long attendanceId, @RequestBody AttendanceUpdateRequest attendanceUpdateRequest) {

        Attendance attendance = attendanceService.update(attendanceId, attendanceUpdateRequest);
        AttendanceResponse attendanceResponse = modelMapper.map(attendance, AttendanceResponse.class);
        return new ResponseEntity<>(attendanceResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/attendances/{attendance-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> delete(@PathVariable("attendance-id") Long attendanceId) {

        attendanceService.delete(attendanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
