package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.GradeRequest;
import lk.zerocode.sms.api.controller.response.GradeSummaryResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.TeacherNotFoundException;
import lk.zerocode.sms.api.model.Grade;
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
