package lk.zerocode.School_management_system.service.impl;


import lk.zerocode.School_management_system.controller.request.ParentAssignRequest;
import lk.zerocode.School_management_system.controller.request.ParentRequest;
import lk.zerocode.School_management_system.exception.ParentNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.Parent;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.repository.ParentRepository;
import lk.zerocode.School_management_system.repository.StudentRepository;
import lk.zerocode.School_management_system.service.ParentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    @Override
    public Parent create(ParentRequest parentRequest) {

        Parent parent = modelMapper.map(parentRequest, Parent.class);
        parentRepository.save(parent);
        return modelMapper.map(parent, Parent.class);
    }

    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    @Override
    public Parent findById(Long parentId) throws ParentNotFoundException {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new ParentNotFoundException("Parent not found with id: " + parentId));
        return modelMapper.map(parent, Parent.class);
    }

    @Override
    public void assignParentToStudent(Long studentId, ParentAssignRequest parentAssignRequest) throws ParentNotFoundException, StudentNotFoundException {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
        for (Long parentId : parentAssignRequest.getParentIds()) {
            Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new RuntimeException("Parent not found"));
            student.getParents().add(parent);
            studentRepository.save(student);
        }

    }
}
