package lk.zerocode.School_management_system.dto;

import lk.zerocode.School_management_system.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
