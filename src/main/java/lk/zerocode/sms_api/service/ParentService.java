package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.ParentAssignRequest;
import lk.zerocode.sms_api.controller.request.ParentRequest;
import lk.zerocode.sms_api.exception.ParentNotFoundException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParentService {

    Parent create(ParentRequest parentRequest);

    List<Parent> findAll();

    Parent findById(Long parentId)throws ParentNotFoundException;

    void assignParentToStudent(Long studentId, ParentAssignRequest parentAssignRequest)throws ParentNotFoundException, StudentNotFoundException;
}
