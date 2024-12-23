package lk.zerocode.sms.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GradeRequest {

    private String gradeNumber;
    private String gradeName;
    private Double classFee;
    private Long teacherId;
}
