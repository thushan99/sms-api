package lk.zerocode.sms_api.controller;

import lk.zerocode.sms_api.controller.request.StudentDocumentRequest;
import lk.zerocode.sms_api.controller.response.StudentDocumentResponse;
import lk.zerocode.sms_api.model.StudentDocument;
import lk.zerocode.sms_api.service.StudentDocumentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StudentDocumentController {

    private StudentDocumentService studentDocumentService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/students/{student-id}/documnets", headers = "X-Api-Version=v1")
    public List<StudentDocumentResponse> createDocument(@RequestBody StudentDocumentRequest studentDocumentRequest, @PathVariable("student-id") Long studentId) {

        List<StudentDocument> documents = studentDocumentService.createStudentDocument(studentDocumentRequest, studentId);
        return documents.stream().map(document -> modelMapper.map(document, StudentDocumentResponse.class)).collect(Collectors.toList());
    }
}
