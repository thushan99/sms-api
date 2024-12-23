package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.PreviousSchoolRequest;
import lk.zerocode.sms.api.exception.SchoolNotFoundException;
import lk.zerocode.sms.api.exception.StudentInactiveException;
import lk.zerocode.sms.api.exception.StudentNotFoundException;
import lk.zerocode.sms.api.model.Status;
import lk.zerocode.sms.api.model.Student;
import lk.zerocode.sms.api.model.PreviousSchool;
import lk.zerocode.sms.api.repository.PreviousSchoolRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.service.PreviousSchoolService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PreviousSchoolServiceImpl implements PreviousSchoolService {

    private PreviousSchoolRepository previousSchoolRepository;
    private ModelMapper modelMapper;
    private StudentRepository studentRepository;

    @Override
    public PreviousSchool create(Long studentId, PreviousSchoolRequest request) throws StudentNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));
        PreviousSchool studentPreviousSchool = modelMapper.map(request, PreviousSchool.class);
        studentPreviousSchool.setStudent(student);
        return previousSchoolRepository.save(studentPreviousSchool);
    }

    @Override
    public List<PreviousSchool> getAll(Long studentId) throws StudentNotFoundException, StudentInactiveException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        List<PreviousSchool> previousSchools = previousSchoolRepository.findByStudentId(studentId);

        if (previousSchools.isEmpty()) {
            throw new StudentNotFoundException("No previous school records found for Student ID: " + studentId);
        }
        return previousSchools;
    }

    @Override
    public PreviousSchool getById(Long studentId, Long schoolId) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        return previousSchoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException("School is Not Found " + schoolId));
    }

    @Override
    public PreviousSchool updateById(Long studentId, Long schoolId, PreviousSchoolRequest request) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));

        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        PreviousSchool existingSchool = previousSchoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException("School is Not Found " + schoolId));
        modelMapper.map(request, existingSchool);
        return previousSchoolRepository.save(existingSchool);
    }

    @Override
    public void deleteById(Long studentId, Long schoolId) throws StudentNotFoundException, SchoolNotFoundException, StudentInactiveException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found " + studentId));
        if (!Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentInactiveException("Student is Inactive " + studentId);
        }
        PreviousSchool existingSchool = previousSchoolRepository.findById(schoolId).orElseThrow(() -> new SchoolNotFoundException("School is Not Found " + schoolId));
        previousSchoolRepository.delete(existingSchool);
    }
}

