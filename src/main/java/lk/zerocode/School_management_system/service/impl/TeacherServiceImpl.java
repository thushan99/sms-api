package lk.zerocode.School_management_system.service.impl;

import lk.zerocode.School_management_system.controller.request.TeacherRequest;
import lk.zerocode.School_management_system.exception.TeacherNotFoundException;
import lk.zerocode.School_management_system.model.Teacher;
import lk.zerocode.School_management_system.repository.TeacherRepository;
import lk.zerocode.School_management_system.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private ModelMapper modelMapper;

    @Override

    public Teacher createTeacher(TeacherRequest teacherControllerRequest) {
        Teacher teacher = modelMapper.map(teacherControllerRequest, Teacher.class);
        teacherRepository.save(teacher);
        return modelMapper.map(teacher, Teacher.class);
    }

    @Override
    public List<Teacher> readAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getSpecificTeacherById(Long teacherId) throws TeacherNotFoundException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId + " teacher not found"));
        return modelMapper.map(teacher, Teacher.class);
    }

    @Override
    public Teacher deleteTeacherById(Long teacherId) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId + " teacher not found"));
        teacherRepository.delete(teacher);
        return null;
    }

    @Override
    public Teacher updateSpecificTeacherById(Long teacherId, TeacherRequest teacherControllerRequest) throws TeacherNotFoundException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId + "teacher not found"));
        modelMapper.map(teacherControllerRequest, teacher);
        teacherRepository.save(teacher);
        return modelMapper.map(teacher, Teacher.class);
    }

    @Override
    public List<Teacher> getAllTeachersWithGrades() {
        return teacherRepository.findAll();
    }

}
