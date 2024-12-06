package lk.zerocode.School_management_system.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeSummaryResponse {

    private String gradeNumber;
    private String gradeName;
    private String teacherName;
    private Long femaleCount;
    private Long maleCount;

}
