package lk.zerocode.sms.api.controller.response;

import lk.zerocode.sms.api.model.AttendanceStatus;
import lk.zerocode.sms.api.model.CheckInOutState;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendanceResponse {

    private Long studentId;
    private Long gradeId;
    private AttendanceStatus status;
    private String remarks;
    private LocalDate date;
    private LocalTime time;
    private CheckInOutState checkInOut;
}
