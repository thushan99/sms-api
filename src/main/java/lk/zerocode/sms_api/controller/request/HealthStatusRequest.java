package lk.zerocode.sms_api.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HealthStatusRequest {

    private String condition;
    private LocalDate diagnosisDate;

}
