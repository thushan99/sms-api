package lk.zerocode.sms_api.controller.request;

import lk.zerocode.sms_api.model.GuardianType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRequest {

    private GuardianType guardianType;
    private String name;
    private String address;
    private String nic;
    private String occupation;
    private String mobile;
    private String religion;
    private Double monthlyAvgIncome;
}
