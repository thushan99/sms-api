package lk.zerocode.sms_api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {
    private String gradeNumber;
    private String gradeName;
    private Double classFee;
    private String teacherName;

}
