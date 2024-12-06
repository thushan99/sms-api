package lk.zerocode.School_management_system.service.impl;

import lk.zerocode.School_management_system.controller.request.PreviousSchoolRequest;
import lk.zerocode.School_management_system.exception.SchoolNotFoundException;
import lk.zerocode.School_management_system.exception.StudentInactiveException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.Status;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.model.PreviousSchool;
import lk.zerocode.School_management_system.repository.PreviousSchoolRepository;
import lk.zerocode.School_management_system.repository.StudentRepository;
import lk.zerocode.School_management_system.service.PreviousSchoolService;
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

