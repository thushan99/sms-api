package lk.zerocode.sms.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeDetailsResponse {

    private String gradeNumber;
    private String gradeName;
    private String teacherName;
}