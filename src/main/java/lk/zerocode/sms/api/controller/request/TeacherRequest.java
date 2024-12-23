package lk.zerocode.sms.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lk.zerocode.sms.api.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeacherRequest {

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
    private String experience;
}
