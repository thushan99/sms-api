package lk.zerocode.School_management_system.service;

import lk.zerocode.School_management_system.controller.request.TeacherRequest;
import lk.zerocode.School_management_system.exception.TeacherNotFoundException;
import lk.zerocode.School_management_system.model.Teacher;
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
