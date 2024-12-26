package lk.zerocode.sms.api.controller;

import lk.zerocode.sms.api.controller.request.PreviousSchoolRequest;
import lk.zerocode.sms.api.controller.response.StudentPreviousSchoolResponse;
import lk.zerocode.sms.api.exception.SchoolNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.PreviousSchool;
import lk.zerocode.sms.api.service.PreviousSchoolService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class PreviousSchoolController {

    private PreviousSchoolService previousSchoolService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/students/{student-id}/previous-schools", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentPreviousSchoolResponse> create(@PathVariable("student-id") Long studentId, @RequestBody PreviousSchoolRequest previousSchoolRequest) throws StudentNotFoundException {

        PreviousSchool studentPreviousSchool = previousSchoolService.create(studentId, previousSchoolRequest);
        StudentPreviousSchoolResponse studentPreviousSchoolResponse = modelMapper.map(studentPreviousSchool, StudentPreviousSchoolResponse.class);
        return new ResponseEntity<>(studentPreviousSchoolResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/students/{student-id}/previous-schools", headers = "X-Api-Version=v1")
    public ResponseEntity<List<StudentPreviousSchoolResponse>> getAll(@PathVariable("student-id") Long studentId) throws StudentNotFoundException {

        List<PreviousSchool> previousSchoolList = previousSchoolService.getAll(studentId);
        List<StudentPreviousSchoolResponse> schoolResponses = previousSchoolList.stream()
                .map(studentPreviousSchool -> modelMapper.map(studentPreviousSchool, StudentPreviousSchoolResponse.class)).toList();
        return new ResponseEntity<>(schoolResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{student-id}/previous-schools/{school-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentPreviousSchoolResponse> getById(@PathVariable("student-id") Long studentId, @PathVariable("school-id") Long schoolId) throws StudentNotFoundException, SchoolNotFoundException {

        PreviousSchool previousSchool = previousSchoolService.getById(studentId, schoolId);
        StudentPreviousSchoolResponse previousSchoolResponse = modelMapper.map(previousSchool, StudentPreviousSchoolResponse.class);
        return new ResponseEntity<>(previousSchoolResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/students/{student-id}/previous-schools/{school-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentPreviousSchoolResponse> updateById(@PathVariable("student-id") Long studentId, @PathVariable("school-id") Long schoolId, @RequestBody PreviousSchoolRequest request) throws StudentNotFoundException, SchoolNotFoundException {

        PreviousSchool previousSchool = previousSchoolService.updateById(studentId, schoolId, request);
        StudentPreviousSchoolResponse previousSchoolResponse = modelMapper.map(previousSchool, StudentPreviousSchoolResponse.class);
        return new ResponseEntity<>(previousSchoolResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{student-id}/previous-schools/{school-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentPreviousSchoolResponse> deleteById(@PathVariable("student-id") Long studentId, @PathVariable("school-id") Long schoolId) throws StudentNotFoundException, SchoolNotFoundException {
        previousSchoolService.deleteById(studentId, schoolId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}