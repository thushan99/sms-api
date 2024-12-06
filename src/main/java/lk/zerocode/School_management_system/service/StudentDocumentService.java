package lk.zerocode.School_management_system.service;

import lk.zerocode.School_management_system.controller.request.StudentDocumentRequest;
import lk.zerocode.School_management_system.model.StudentDocument;

import java.util.List;

public interface StudentDocumentService {

    List<StudentDocument> createStudentDocument(StudentDocumentRequest request, Long studentId);


}
