package lk.zerocode.sms.api.controller;

import lk.zerocode.sms.api.controller.request.ExtraActivitiesRequest;
import lk.zerocode.sms.api.controller.response.ExtraActivitiesResponse;
import lk.zerocode.sms.api.exception.ExtraActivityNotFoundException;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.StudentExtraActivity;
import lk.zerocode.sms.api.service.StudentExtraActivitiesService;
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
