package lk.zerocode.sms.api.dto;

import lk.zerocode.sms.api.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

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
