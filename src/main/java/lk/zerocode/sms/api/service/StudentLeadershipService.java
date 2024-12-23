package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.LeadershipRequest;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.LeadershipNotFoundException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.StudentLeadership;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentLeadershipService {

    StudentLeadership createStudentLeadership(Long studentId, LeadershipRequest leadershipRequest) throws StudentNotFoundException, GradeNotFoundException;

    List<StudentLeadership> getAllByGradesWise(Long studentId, Long gradeId) throws StudentNotFoundException, GradeNotFoundException;

    void deleteById(Long leadershipId) throws LeadershipNotFoundException;
}