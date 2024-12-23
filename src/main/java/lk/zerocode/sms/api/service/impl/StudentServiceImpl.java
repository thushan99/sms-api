package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.StudentInformationRequest;
import lk.zerocode.sms.api.controller.response.StudentGradeDetailsResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentInactiveException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.*;
import lk.zerocode.sms.api.repository.GradeRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;

    @Override
    public Student create(StudentInformationRequest request) throws GradeNotFoundException {
        Student lastStudent = studentRepository.findTopByOrderByIdDesc();

        if (lastStudent != null && lastStudent.getDraft() == Draft.INCOMPLETE) {
            return modelMapper.map(lastStudent, Student.class);
        }

        Student student = modelMapper.map(request, Student.class);
        Grade grade = gradeRepository.findById(request.getGrade()).orElseThrow(() -> new GradeNotFoundException("Grade not found for ID: " + request.getGrade()));

        student.setGrade(grade);
        student.setDraft(Draft.INCOMPLETE);
        student.setStatus(Status.ACTIVE);

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, Student.class);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findByStatus(Status.ACTIVE);
    }

    @Override
    public Student getById(Long studentId) throws StudentNotFoundException, StudentInactiveException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found for ID: " + studentId));
        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student with ID: " + studentId + " is not active.");
        }
        return student;
    }

    @Override
    public Student updateById(StudentInformationRequest request, Long studentId) throws StudentNotFoundException, GradeNotFoundException {

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found for ID: " + studentId));
        modelMapper.map(request, existingStudent);
        Grade grade = gradeRepository.findById(request.getGrade())
                .orElseThrow(() -> new GradeNotFoundException("Grade not found for ID: " + request.getGrade()));
        existingStudent.setGrade(grade);
        existingStudent.setStatus(Status.ACTIVE);
        Student updateStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updateStudent, Student.class);
    }

    @Override
    public void deleteById(Long studentId) throws StudentNotFoundException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found for ID: " + studentId));

        if (Status.INACTIVE.equals(student.getStatus())) {
            throw new IllegalStateException("Student with ID: " + studentId + " is already inactive.");
        }
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }

    @Override
    public StudentGradeDetailsResponse getStudentWithGrades(Long studentId) throws StudentNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found for ID: " + studentId));
        Grade grade = student.getGrade();
        if (grade == null) {
            throw new RuntimeException("No grade assigned to this student.");
        }
        Teacher teacher = grade.getTeacher();

        if (teacher == null) {
            throw new RuntimeException("No teacher assigned to this grades.");
        }

        StudentGradeDetailsResponse studentGradeDetailsResponse = new StudentGradeDetailsResponse();
        studentGradeDetailsResponse.setGradeNumber(grade.getGradeNumber());
        studentGradeDetailsResponse.setGradeName(grade.getGradeName());
        studentGradeDetailsResponse.setTeacherName(teacher.getTeacherName());

        return studentGradeDetailsResponse;
    }

}