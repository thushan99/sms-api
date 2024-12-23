package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.HealthStatusRequest;
import lk.zerocode.sms.api.exception.HealthStatusNotFoundException;
import lk.zerocode.sms.api.exception.StudentInactiveException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.StudentHealth;

import java.util.List;

public interface StudentHealthService {

    StudentHealth create(Long id, HealthStatusRequest request) throws StudentNotFoundException;

    List<StudentHealth> getAll(Long studentId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException;

    StudentHealth getById(Long studentId,Long healthId) throws StudentNotFoundException,  StudentInactiveException, HealthStatusNotFoundException;

    StudentHealth updateById(Long studentId,Long healthId,HealthStatusRequest request) throws StudentNotFoundException,  StudentInactiveException, HealthStatusNotFoundException;

    void deleteById(Long studentId,Long healthId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException;
}
