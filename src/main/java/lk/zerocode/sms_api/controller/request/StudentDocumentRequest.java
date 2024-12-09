package lk.zerocode.sms_api.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentDocumentRequest {

    private String birthCertificate;
    private List<String> otherDocuments;

}
