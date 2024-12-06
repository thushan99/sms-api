package lk.zerocode.School_management_system.service;

import lk.zerocode.School_management_system.controller.request.ParentAssignRequest;
import lk.zerocode.School_management_system.controller.request.ParentRequest;
import lk.zerocode.School_management_system.exception.ParentNotFoundException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParentService {

    Parent create(ParentRequest parentRequest);

    List<Parent> findAll();

    Parent findById(Long parentId)throws ParentNotFoundException;

    void assignParentToStudent(Long studentId, ParentAssignRequest parentAssignRequest)throws ParentNotFoundException, StudentNotFoundException;
}
