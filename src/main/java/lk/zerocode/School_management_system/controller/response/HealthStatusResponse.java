package lk.zerocode.School_management_system.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HealthStatusResponse {

    private Long id;
    private String condition;
    private LocalDate diagnosisDate;

}
