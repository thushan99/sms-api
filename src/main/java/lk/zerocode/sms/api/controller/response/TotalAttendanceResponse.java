package lk.zerocode.sms.api.controller.response;

import lombok.Data;

@Data
public class TotalAttendanceResponse {

    private String yearMonth;
    private Long presentCount;
    private Long lateCount;
    private Long absentCount;
}
