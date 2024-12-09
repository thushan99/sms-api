package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.HealthStatusRequest;
import lk.zerocode.sms_api.exception.HealthStatusNotFoundException;
import lk.zerocode.sms_api.exception.StudentInactiveException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.StudentHealth;

import java.util.List;

public interface StudentHealthService {

    StudentHealth create(Long id, HealthStatusRequest request) throws StudentNotFoundException;

    List<StudentHealth> getAll(Long studentId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException;

    StudentHealth getById(Long studentId,Long healthId) throws StudentNotFoundException,  StudentInactiveException, HealthStatusNotFoundException;

    StudentHealth updateById(Long studentId,Long healthId,HealthStatusRequest request) throws StudentNotFoundException,  StudentInactiveException, HealthStatusNotFoundException;

    void deleteById(Long studentId,Long healthId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException;
}
