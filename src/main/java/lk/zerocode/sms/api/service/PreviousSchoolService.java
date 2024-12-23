package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.PreviousSchoolRequest;
import lk.zerocode.sms.api.exception.SchoolNotFoundException;
import lk.zerocode.sms.api.exception.StudentInactiveException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.PreviousSchool;

import java.util.List;


public interface PreviousSchoolService {

    PreviousSchool create(Long id, PreviousSchoolRequest request) throws StudentNotFoundException;

    List<PreviousSchool> getAll(Long studentId) throws StudentNotFoundException, StudentInactiveException;

    PreviousSchool getById(Long studentId,Long schoolId) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException;

    PreviousSchool updateById(Long studentId,Long schoolId,PreviousSchoolRequest request) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException;

    void deleteById(Long studentId,Long schoolId) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException;
}
