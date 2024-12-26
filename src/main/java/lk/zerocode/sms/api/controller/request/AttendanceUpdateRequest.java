package lk.zerocode.sms.api.controller.request;

import lk.zerocode.sms.api.model.AttendanceStatus;
import lk.zerocode.sms.api.model.CheckInOutState;
import lombok.Data;

@Data
public class AttendanceUpdateRequest {

    private AttendanceStatus status;
    private String remarks;
    private CheckInOutState checkInOut;
}
