package lk.zerocode.sms.api.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PreviousSchoolRequest {

    private String schoolName;
    private LocalDate startDate;
    private LocalDate endDate;
}
