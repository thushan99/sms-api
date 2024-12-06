package lk.zerocode.School_management_system.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
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
