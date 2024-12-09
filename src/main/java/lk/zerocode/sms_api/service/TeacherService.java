package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.TeacherRequest;
import lk.zerocode.sms_api.exception.TeacherNotFoundException;
import lk.zerocode.sms_api.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    Teacher createTeacher(TeacherRequest teacherControllerRequest);

    List<Teacher> readAllTeachers();

    Teacher getSpecificTeacherById(Long teacherId) throws TeacherNotFoundException;

    Teacher deleteTeacherById(Long teacherId) throws TeacherNotFoundException;

    Teacher updateSpecificTeacherById(Long teacherId, TeacherRequest teacherControllerRequest) throws TeacherNotFoundException;

    List<Teacher> getAllTeachersWithGrades();
}
