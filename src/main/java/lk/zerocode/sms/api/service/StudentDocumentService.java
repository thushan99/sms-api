package lk.zerocode.sms.api.service;

import lk.zerocode.sms.api.controller.request.StudentDocumentRequest;
import lk.zerocode.sms.api.model.StudentDocument;

import java.util.List;

public interface StudentDocumentService {

    List<StudentDocument> createStudentDocument(StudentDocumentRequest request, Long studentId);


}
