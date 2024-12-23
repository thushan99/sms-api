package lk.zerocode.sms.api.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentPreviousSchoolResponse {

    private String studentId;
    private String studentName;
    private String schoolName;
    private LocalDate startDate;
    private LocalDate endDate;
}
