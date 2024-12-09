package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.GradeRequest;
import lk.zerocode.sms_api.controller.response.GradeSummaryResponse;
import lk.zerocode.sms_api.exception.GradeNotFoundException;
import lk.zerocode.sms_api.exception.TeacherNotFoundException;
import lk.zerocode.sms_api.model.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {

    Grade creteGrade(Long teacherId,GradeRequest gradeRequest)throws TeacherNotFoundException;

    List<Grade> readAllGrades();

    Grade getGradeById(Long gradeId) throws GradeNotFoundException;

    Grade updateById(Long gradeId, GradeRequest gradeRequest)throws GradeNotFoundException, TeacherNotFoundException;

    List<GradeSummaryResponse> getGradeTeacherDetails();
  
}
