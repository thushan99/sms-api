package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.ExtraActivitiesRequest;
import lk.zerocode.sms.api.exception.ExtraActivityNotFoundException;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Grade;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.model.StudentExtraActivity;
import lk.zerocode.sms.api.repository.GradeRepository;
import lk.zerocode.sms.api.repository.StudentExtraActivityRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.StudentExtraActivitiesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentExtraActivitiesServiceImpl implements StudentExtraActivitiesService {

    private final StudentExtraActivityRepository studentExtraActivityRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    @Override
    public StudentExtraActivity create(Long studentId, ExtraActivitiesRequest extraActivitiesRequest) throws StudentNotFoundException, GradeNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        Grade grade = gradeRepository.findById(extraActivitiesRequest.getGradeId()).orElseThrow(() -> new GradeNotFoundException("Student with id " + extraActivitiesRequest.getGradeId() + " not found"));

        StudentExtraActivity studentExtraActivity = new StudentExtraActivity();
        studentExtraActivity.setExtraActivity(extraActivitiesRequest.getExtraActivity());
        studentExtraActivity.setStudent(student);
        studentExtraActivity.setGrade(grade);
        studentExtraActivityRepository.save(studentExtraActivity);
        return modelMapper.map(studentExtraActivity, StudentExtraActivity.class);
    }

    @Override
    public List<StudentExtraActivity> getAllByGradesWise(Long studentId, Long gradeId) throws StudentNotFoundException, GradeNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new GradeNotFoundException("Grade with id " + gradeId + " not found"));

        return studentExtraActivityRepository.findAllByStudentIdAndGradeId(studentId, gradeId);
    }

    @Override
    public void deleteById(Long activityId) throws ExtraActivityNotFoundException {

        StudentExtraActivity studentExtraActivity = studentExtraActivityRepository.findById(activityId).orElseThrow(() -> new ExtraActivityNotFoundException("Student with id " + activityId + " not found"));
        studentExtraActivityRepository.delete(studentExtraActivity);
    }
}
