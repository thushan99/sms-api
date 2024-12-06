package lk.zerocode.School_management_system.controller;

import lk.zerocode.School_management_system.controller.request.ExtraActivitiesRequest;
import lk.zerocode.School_management_system.controller.response.ExtraActivitiesResponse;
import lk.zerocode.School_management_system.exception.ExtraActivityNotFoundException;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.StudentExtraActivity;
import lk.zerocode.School_management_system.service.StudentExtraActivitiesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StudentExtraActivitiesController {

    private StudentExtraActivitiesService studentExtraActivitiesService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/students/{student-id}/extra-activities",headers = "X-api-Version=v1")
    public ResponseEntity<ExtraActivitiesResponse> create(@PathVariable("student-id") Long studentId,
                                                          @RequestBody ExtraActivitiesRequest extraActivitiesRequest)throws StudentNotFoundException,GradeNotFoundException{

        StudentExtraActivity studentExtraActivity = studentExtraActivitiesService.create(studentId, extraActivitiesRequest);
        ExtraActivitiesResponse extraActivitiesResponse = modelMapper.map(studentExtraActivity, ExtraActivitiesResponse.class);
        return new ResponseEntity<>(extraActivitiesResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/students/{student-id}/grades/{grade-id}/extra-activities",headers = "X-Api-Version=v1")
    public ResponseEntity<List<ExtraActivitiesResponse>> readAll(@PathVariable("student-id") Long studentId,
                                                                @PathVariable("grade-id")Long gradeId)throws StudentNotFoundException,GradeNotFoundException{
        List<StudentExtraActivity> studentExtraActivity = studentExtraActivitiesService.getAllByGradesWise(studentId,gradeId);
        List<ExtraActivitiesResponse> extraActivitiesResponse = studentExtraActivity.stream()
                .map(studentExtraActivity1 -> modelMapper.map(studentExtraActivity1, ExtraActivitiesResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(extraActivitiesResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/extra-activities/{activity-id}",headers = "X-Api-Version=v1")
    public ResponseEntity<ExtraActivitiesResponse> deleteById(@PathVariable("activity-id") Long activityId)throws ExtraActivityNotFoundException {

        studentExtraActivitiesService.deleteById(activityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
