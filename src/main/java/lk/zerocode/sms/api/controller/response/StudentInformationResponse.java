package lk.zerocode.sms.api.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentInformationResponse {

    private Long id;
    private String profileImageUrl;
    private String registrationNumber;
    private String studentName;
    private LocalDate admissionDate;
    private Long gradeId;
    private String address;
    private LocalDate birthDate;
    private String gender;
    private String religion;
}
