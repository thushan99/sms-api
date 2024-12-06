package lk.zerocode.School_management_system.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraActivitiesRequest {

    private Long gradeId;
    private String extraActivity;

}
