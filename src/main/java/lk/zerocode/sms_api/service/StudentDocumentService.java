package lk.zerocode.sms_api.service;

import lk.zerocode.sms_api.controller.request.StudentDocumentRequest;
import lk.zerocode.sms_api.model.StudentDocument;

import java.util.List;

public interface StudentDocumentService {

    List<StudentDocument> createStudentDocument(StudentDocumentRequest request, Long studentId);


}
