package lk.zerocode.School_management_system.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentDocumentRequest {

    private String birthCertificate;
    private List<String> otherDocuments;

}
