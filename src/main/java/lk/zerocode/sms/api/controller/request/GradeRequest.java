package lk.zerocode.sms.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {

    private String gradeNumber;
    private String gradeName;
    private Double classFee;
    private Long teacherId;
}
