package lk.zerocode.School_management_system.controller;

import lk.zerocode.School_management_system.controller.request.StudentInformationRequest;
import lk.zerocode.School_management_system.controller.response.StudentGradeDetailsResponse;
import lk.zerocode.School_management_system.controller.response.StudentInformationResponse;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.service.StudentService;
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
    public ResponseEntity<?> create(@RequestBody StudentInformationRequest studentInformationRequest) {

        try {
            Student createdStudent = studentService.create(studentInformationRequest);
            StudentInformationResponse response = modelMapper.map(createdStudent, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (GradeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping(value = "/students", headers = "X-Api-Version=v1")
    public ResponseEntity<List<StudentInformationResponse>> getAll() {
        try {
            List<Student> studentList = studentService.getAll();
            List<StudentInformationResponse> studentInformationResponses = studentList
                    .stream().map(student -> modelMapper
                            .map(student, StudentInformationResponse.class)).toList();
            return new ResponseEntity<>(studentInformationResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentInformationResponse> getById(@PathVariable("student-id") Long studentId) {
        try {
            Student student = studentService.getById(studentId);
            StudentInformationResponse response = modelMapper.map(student, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentInformationResponse> updateById(
            @RequestBody StudentInformationRequest studentInformationRequest,
            @PathVariable("student-id") Long studentId) {
        try {
            Student updatedStudent = studentService.updateById(studentInformationRequest, studentId);
            StudentInformationResponse response = modelMapper.map(updatedStudent, StudentInformationResponse.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (GradeNotFoundException | StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
   @DeleteMapping(value = "/students/{student-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<?> deleteById(@PathVariable("student-id") Long studentId) {
        try {
            studentService.deleteById(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/students/grades/students/{student-id}",headers = "X-Api-Version")
    public ResponseEntity<StudentGradeDetailsResponse>getStudentWithGrades (@PathVariable ("student-id") Long studentId) throws StudentNotFoundException{

        StudentGradeDetailsResponse response = studentService.getStudentWithGrades(studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
