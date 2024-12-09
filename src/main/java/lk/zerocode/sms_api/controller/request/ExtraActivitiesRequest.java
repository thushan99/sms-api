package lk.zerocode.sms_api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraActivitiesRequest {

    private Long gradeId;
    private String extraActivity;

}
