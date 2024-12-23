package lk.zerocode.sms.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PreviousSchoolRequest {

    private String schoolName;
    private LocalDate startDate;
    private LocalDate endDate;
}
