package lk.zerocode.sms.api.controller.response;

import lk.zerocode.sms.api.model.DocType;
import lombok.Data;

@Data
public class StudentDocumentResponse {

    private Long id;
    private String documentPath;
    private DocType docType;
    private String docFormat;
    private String studentId;
}
