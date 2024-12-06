package lk.zerocode.School_management_system.controller.response;

import lk.zerocode.School_management_system.model.DocType;
import lombok.Data;

@Data
public class StudentDocumentResponse {
    private Long id;
    private String documentPath;
    private DocType docType;
    private String docFormat;
    private String studentId;
}
