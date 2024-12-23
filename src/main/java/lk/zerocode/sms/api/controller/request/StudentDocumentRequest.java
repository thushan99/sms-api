package lk.zerocode.sms.api.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentDocumentRequest {

    private String birthCertificate;
    private List<String> otherDocuments;
}
