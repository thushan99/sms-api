package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.LeadershipRequest;
import lk.zerocode.sms_api.exception.GradeNotFoundException;
import lk.zerocode.sms_api.exception.LeadershipNotFoundException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.StudentLeadership;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentLeadershipService {

    StudentLeadership createStudentLeadership(Long studentId, LeadershipRequest leadershipRequest) throws StudentNotFoundException, GradeNotFoundException;

    List<StudentLeadership> getAllByGradesWise(Long studentId, Long gradeId) throws StudentNotFoundException, GradeNotFoundException;

    StudentLeadership deleteById(Long leadershipId) throws LeadershipNotFoundException;
}