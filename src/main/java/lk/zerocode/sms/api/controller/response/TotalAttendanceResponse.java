package lk.zerocode.sms.api.controller.response;

import lombok.Data;

@Data
public class TotalAttendanceResponse {

    private Integer totalPresent;
    private Integer totalAbsent;
    private Integer totalLate;
}
