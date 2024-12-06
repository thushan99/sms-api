package lk.zerocode.School_management_system.service;

import lk.zerocode.School_management_system.controller.request.LeadershipRequest;
import lk.zerocode.School_management_system.exception.ExtraActivityNotFoundException;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.LeadershipNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.StudentExtraActivity;
import lk.zerocode.School_management_system.model.StudentLeadership;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentLeadershipService {

    StudentLeadership createStudentLeadership(Long studentId, LeadershipRequest leadershipRequest) throws StudentNotFoundException, GradeNotFoundException;

    List<StudentLeadership> getAllByGradesWise(Long studentId, Long gradeId) throws StudentNotFoundException, GradeNotFoundException;

    StudentLeadership deleteById(Long leadershipId) throws LeadershipNotFoundException;
}