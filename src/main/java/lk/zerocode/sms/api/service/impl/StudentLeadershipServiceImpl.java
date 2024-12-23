package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.LeadershipRequest;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.LeadershipNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Grade;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.model.StudentLeadership;
import lk.zerocode.sms.api.repository.GradeRepository;
import lk.zerocode.sms.api.repository.StudentLeadershipRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.StudentLeadershipService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentLeadershipServiceImpl implements StudentLeadershipService {

    private final StudentLeadershipRepository studentLeadershipRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentLeadership createStudentLeadership(Long studentId, LeadershipRequest leadershipRequest) throws StudentNotFoundException, GradeNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        Grade grade = gradeRepository.findById(leadershipRequest.getGradeId()).orElseThrow(() -> new GradeNotFoundException("Student with id " + leadershipRequest.getGradeId() + " not found"));

        StudentLeadership studentLeadership = new StudentLeadership();
        studentLeadership.setLeadership(leadershipRequest.getLeadership());
        studentLeadership.setStudent(student);
        studentLeadership.setGrade(grade);
        studentLeadershipRepository.save(studentLeadership);
        return modelMapper.map(studentLeadership, StudentLeadership.class);
    }

    @Override
    public List<StudentLeadership> getAllByGradesWise(Long studentId, Long gradeId) throws StudentNotFoundException, GradeNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " not found"));

        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new GradeNotFoundException("Grade with id " + gradeId + " not found"));

        return studentLeadershipRepository.findAllByStudentIdAndGradeId(studentId, gradeId);
    }

    @Override
    public void deleteById(Long leadershipId) throws LeadershipNotFoundException {

        StudentLeadership leadership = studentLeadershipRepository.findById(leadershipId).orElseThrow(() -> new LeadershipNotFoundException("Student with id " + leadershipId + " not found"));
        studentLeadershipRepository.delete(leadership);
    }
}
