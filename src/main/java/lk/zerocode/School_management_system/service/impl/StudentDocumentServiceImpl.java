package lk.zerocode.School_management_system.service.impl;

import lk.zerocode.School_management_system.controller.request.StudentDocumentRequest;
import lk.zerocode.School_management_system.model.DocType;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.model.StudentDocument;
import lk.zerocode.School_management_system.repository.StudentDocumentRepository;
import lk.zerocode.School_management_system.repository.StudentRepository;
import lk.zerocode.School_management_system.service.StudentDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentDocumentServiceImpl implements StudentDocumentService {

    private StudentDocumentRepository studentDocumentRepository;
    private StudentRepository studentRepository;

    @Override
    public List<StudentDocument> createStudentDocument(StudentDocumentRequest request, Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        StudentDocument birthCertificateDocument = buildStudentDocument(request.getBirthCertificate(), DocType.BIRTH_CERTIFICATE, student);
        studentDocumentRepository.save(birthCertificateDocument);

        List<StudentDocument> otherDocuments = request.getOtherDocuments().stream().map(docPath -> buildStudentDocument(docPath, DocType.OTHER_CERTIFICATE, student)).collect(Collectors.toList());

        studentDocumentRepository.saveAll(otherDocuments);

        otherDocuments.add(birthCertificateDocument);
        return otherDocuments;
    }

    private StudentDocument buildStudentDocument(String documentPath, DocType docType, Student student) {
        StudentDocument document = new StudentDocument();
        document.setDocumentPath(documentPath);
        document.setDocType(docType);
        document.setDocFormat(getFileFormat(documentPath));
        document.setStudent(student);
        return document;
    }

    private String getFileFormat(String documentPath) {
        if (documentPath != null && documentPath.contains(".")) {
            return documentPath.substring(documentPath.lastIndexOf(".") + 1);
        }
        return "unknown";
    }
}
