package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.ExtraActivitiesRequest;
import lk.zerocode.sms_api.exception.ExtraActivityNotFoundException;
import lk.zerocode.sms_api.exception.GradeNotFoundException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.StudentExtraActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentExtraActivitiesService {

    StudentExtraActivity create(Long studentId, ExtraActivitiesRequest extraActivitiesRequest)throws StudentNotFoundException,GradeNotFoundException;

    List<StudentExtraActivity> getAllByGradesWise(Long studentId,Long gradeId)throws StudentNotFoundException,GradeNotFoundException ;

    StudentExtraActivity deleteById(Long activityId)throws ExtraActivityNotFoundException;
}
