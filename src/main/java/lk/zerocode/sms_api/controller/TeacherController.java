package lk.zerocode.sms_api.controller;

import lk.zerocode.sms_api.controller.request.TeacherRequest;
import lk.zerocode.sms_api.controller.response.TeacherNameListResponse;
import lk.zerocode.sms_api.controller.response.TeacherResponse;
import lk.zerocode.sms_api.exception.TeacherNotFoundException;
import lk.zerocode.sms_api.model.Teacher;
import lk.zerocode.sms_api.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/teachers", headers = "X-Api-Version=v1")
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherControllerRequest) {
        Teacher teacher = teacherService.createTeacher(teacherControllerRequest);
        TeacherResponse teacherControllerResponse = modelMapper.map(teacher, TeacherResponse.class);
        return new ResponseEntity<>(teacherControllerResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/teachers", headers = "X-Api-Version=v1")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        List<Teacher> teacherList = teacherService.readAllTeachers();
        List<TeacherResponse> teacherControllerResponseList = teacherList.stream().map(teacher -> modelMapper.map(teacher, TeacherResponse.class)).toList();
        return new ResponseEntity<>(teacherControllerResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/{teacher-id}/teachers", headers = "X-Api-Version=v1")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable("teacher-id") Long teacherId) throws TeacherNotFoundException {
        Teacher teacher = teacherService.getSpecificTeacherById(teacherId);
        TeacherResponse teacherControllerResponse = modelMapper.map(teacher, TeacherResponse.class);
        return new ResponseEntity<>(teacherControllerResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/teachers", headers = "X-Api-Version=v1")
    public ResponseEntity<TeacherResponse> deleteTeacherById(@PathVariable("id") Long id) throws TeacherNotFoundException {
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{teacher-id}/teachers", headers = "X-Api-Version=v1")
    public ResponseEntity<TeacherResponse> updateById(@PathVariable("teacher-id") Long teacherId, @RequestBody TeacherRequest teacherControllerRequest) throws TeacherNotFoundException {
        Teacher teacher = teacherService.updateSpecificTeacherById(teacherId, teacherControllerRequest);
        TeacherResponse teacherControllerResponse = modelMapper.map(teacher, TeacherResponse.class);
        return new ResponseEntity<>(teacherControllerResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/teachers-with-grades", headers = "X-Api-Version=v1")
    public ResponseEntity<List<TeacherNameListResponse>> getAllTeachersWithGrades() {
        List<Teacher> teacherList = teacherService.getAllTeachersWithGrades();
        List<TeacherNameListResponse> gradeTeacherResponses = teacherList.stream().map(teacher -> modelMapper.map(teacher, TeacherNameListResponse.class)).toList();
        return new ResponseEntity<>(gradeTeacherResponses, HttpStatus.OK);
    }


}

