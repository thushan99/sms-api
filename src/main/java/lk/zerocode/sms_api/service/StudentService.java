package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.StudentInformationRequest;
import lk.zerocode.sms_api.controller.response.StudentGradeDetailsResponse;
import lk.zerocode.sms_api.exception.GradeNotFoundException;
import lk.zerocode.sms_api.exception.StudentInactiveException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.Student;

import java.util.List;

public interface StudentService {

    Student create(StudentInformationRequest request) throws GradeNotFoundException;

    List<Student> getAll();

    Student getById(Long studentId) throws StudentNotFoundException, StudentInactiveException;

    Student updateById(StudentInformationRequest request,Long studentId) throws StudentNotFoundException,GradeNotFoundException;

    void deleteById(Long studentId) throws StudentNotFoundException;

    StudentGradeDetailsResponse getStudentWithGrades(Long studentId) throws StudentNotFoundException;

}
