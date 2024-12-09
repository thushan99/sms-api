package lk.zerocode.sms_api.controller.response;

import lk.zerocode.sms_api.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {

    private String name;
    private String nic;
    private String address;
    private Integer mobile;
    private LocalDate date;
    private String email;
    private String subject;
    private String imageUrl;
    private Gender gender;
    private String education;
    private String Experience;
}