package lk.zerocode.School_management_system.service;

import lk.zerocode.School_management_system.controller.request.GradeRequest;
import lk.zerocode.School_management_system.controller.response.GradeSummaryResponse;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.exception.TeacherNotFoundException;
import lk.zerocode.School_management_system.model.Grade;
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
