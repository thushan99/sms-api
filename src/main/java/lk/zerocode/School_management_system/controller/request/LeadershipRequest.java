package lk.zerocode.School_management_system.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipRequest {

    private Long gradeId;
    private String leadership;

}
