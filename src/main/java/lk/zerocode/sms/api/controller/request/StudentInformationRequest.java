package lk.zerocode.sms.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudentInformationRequest {

    private String profileImageUrl;
    private String registrationNumber;
    private String name;
    private LocalDate admissionDate;
    private Long grade;
    private String address;
    private LocalDate birthDate;
    private String gender;
    private String religion;
}
