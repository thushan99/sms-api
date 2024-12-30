package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.HealthStatusRequest;
import lk.zerocode.sms.api.exception.HealthStatusNotFoundException;
import lk.zerocode.sms.api.exception.StudentInactiveException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Status;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.model.StudentHealth;
import lk.zerocode.sms.api.repository.StudentHealthRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.StudentHealthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentHealthServiceImpl implements StudentHealthService {

    private StudentHealthRepository studentHealthRepository;
    private StudentRepository studentRepository;

    @Override
    public StudentHealth create(Long studentId, HealthStatusRequest request) throws StudentNotFoundException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));
        StudentHealth studentHealth = new StudentHealth();
        studentHealth.setStudent(student);
        studentHealth.setHealthCondition(request.getHealthCondition());
        studentHealth.setDiagnosisDate(request.getDiagnosisDate());
        return studentHealthRepository.save(studentHealth);
    }

    @Override
    public List<StudentHealth> getAll(Long studentId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        List<StudentHealth> studentHealths = studentHealthRepository.findByStudentId(studentId);

        if (studentHealths.isEmpty()) {
            throw new HealthStatusNotFoundException("No Health status records found for Student ID: " + studentId);
        }
        return studentHealths;
    }

    @Override
    public StudentHealth getById(Long studentId, Long healthId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        return studentHealthRepository.findById(healthId)
                .orElseThrow(() -> new HealthStatusNotFoundException("Health Status is Not Found " + healthId));
    }

    @Override
    public StudentHealth updateById(Long studentId, Long healthId, HealthStatusRequest request) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        StudentHealth existingHealth = studentHealthRepository.findById(healthId)
                .orElseThrow(() -> new HealthStatusNotFoundException("Health Status is Not Found " + healthId));
        existingHealth.setHealthCondition(request.getHealthCondition());
        existingHealth.setDiagnosisDate(request.getDiagnosisDate());
        return studentHealthRepository.save(existingHealth);
    }

    @Override
    public void deleteById(Long studentId, Long healthId) throws StudentNotFoundException, StudentInactiveException, HealthStatusNotFoundException {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));
        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        StudentHealth studentHealth = studentHealthRepository.findById(healthId).orElseThrow(() -> new HealthStatusNotFoundException("Health Status is Not Found " + healthId));
        studentHealthRepository.delete(studentHealth);
    }


}
