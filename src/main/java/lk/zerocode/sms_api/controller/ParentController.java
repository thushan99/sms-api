package lk.zerocode.sms_api.controller;



import lk.zerocode.sms_api.controller.request.ParentAssignRequest;
import lk.zerocode.sms_api.controller.request.ParentRequest;
import lk.zerocode.sms_api.controller.response.ParentResponse;
import lk.zerocode.sms_api.exception.ParentNotFoundException;
import lk.zerocode.sms_api.exception.StudentNotFoundException;
import lk.zerocode.sms_api.model.Parent;
import lk.zerocode.sms_api.service.ParentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParentController {

    private final ParentService parentService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/parents",headers = "X-Api-Version=v1")
    public ResponseEntity<ParentResponse> create(@RequestBody ParentRequest parentRequest) {

        Parent parent = parentService.create(parentRequest);
        ParentResponse parentResponse = modelMapper.map(parent, ParentResponse.class);
        return new ResponseEntity<>(parentResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/parents",headers = "X-Api-version=v1")
    public ResponseEntity<List<ParentResponse>> getAll(){

        List<Parent> parents = parentService.findAll();
        List<ParentResponse> parentResponses = modelMapper.map(parents, List.class);
        return new ResponseEntity<>(parentResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/parents/{parent-id}",headers = "X-Api-Version=v1")
    public ResponseEntity<ParentResponse> getById(@PathVariable("parent-id") Long parentId)throws ParentNotFoundException {

        Parent parent = parentService.findById(parentId);
        ParentResponse parentResponse = modelMapper.map(parent, ParentResponse.class);
        return new ResponseEntity<>(parentResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/students/{student-id}/parents",headers = "X-Api-Version=v1")
    public ResponseEntity<String> assignParentToStudent(@PathVariable("student-id")Long studentId,
                                                        @RequestBody ParentAssignRequest parentAssignRequest)throws StudentNotFoundException, ParentNotFoundException {
        parentService.assignParentToStudent(studentId, parentAssignRequest);
        return new ResponseEntity<>("Parent or parents assigned to student successfully", HttpStatus.OK);
    }
}
