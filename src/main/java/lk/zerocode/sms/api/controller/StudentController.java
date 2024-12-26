package lk.zerocode.sms.api.controller;

import lk.zerocode.sms.api.controller.request.StudentInformationRequest;
import lk.zerocode.sms.api.controller.response.StudentGradeDetailsResponse;
import lk.zerocode.sms.api.controller.response.StudentInformationResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/students", headers = "X-Api-Version=v1")
    public ResponseEntity<?> create(@RequestBody StudentInformationRequest studentInformationRequest) throws GradeNotFoundException {

            Student createdStudent = studentService.create(studentInformationRequest);
            StudentInformationResponse response = modelMapper.map(createdStudent, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/students", headers = "X-Api-Version=v1")
    public ResponseEntity<List<StudentInformationResponse>> getAll() {

            List<Student> studentList = studentService.getAll();
            List<StudentInformationResponse> studentInformationResponses = studentList
                    .stream().map(student -> modelMapper
                            .map(student, StudentInformationResponse.class)).toList();
            return new ResponseEntity<>(studentInformationResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentInformationResponse> getById(@PathVariable("student-id") Long studentId) throws StudentNotFoundException {

            Student student = studentService.getById(studentId);
            StudentInformationResponse response = modelMapper.map(student, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentInformationResponse> updateById(
            @RequestBody StudentInformationRequest studentInformationRequest,
            @PathVariable("student-id") Long studentId) throws GradeNotFoundException, StudentNotFoundException {

            Student updatedStudent = studentService.updateById(studentInformationRequest, studentId);
            StudentInformationResponse response = modelMapper.map(updatedStudent, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
  
   @DeleteMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> deleteById(@PathVariable("student-id") Long studentId) throws StudentNotFoundException {

            studentService.deleteById(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/students/grades/students/{student-id}",headers = "X-Api-Version")
    public ResponseEntity<StudentGradeDetailsResponse>getStudentWithGrades (@PathVariable ("student-id") Long studentId) throws StudentNotFoundException{

        StudentGradeDetailsResponse response = studentService.getStudentWithGrades(studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
