package lk.zerocode.School_management_system.service.impl;

import lk.zerocode.School_management_system.controller.request.LeadershipRequest;
import lk.zerocode.School_management_system.exception.ExtraActivityNotFoundException;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.LeadershipNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.Grade;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.model.StudentExtraActivity;
import lk.zerocode.School_management_system.model.StudentLeadership;
import lk.zerocode.School_management_system.repository.GradeRepository;
import lk.zerocode.School_management_system.repository.StudentLeadershipRepository;
import lk.zerocode.School_management_system.repository.StudentRepository;
import lk.zerocode.School_management_system.service.StudentLeadershipService;
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
    public StudentLeadership deleteById(Long leadershipId) throws LeadershipNotFoundException {

        StudentLeadership leadership = studentLeadershipRepository.findById(leadershipId).orElseThrow(() -> new LeadershipNotFoundException("Student with id " + leadershipId + " not found"));
        studentLeadershipRepository.delete(leadership);
        return null;
    }
}
