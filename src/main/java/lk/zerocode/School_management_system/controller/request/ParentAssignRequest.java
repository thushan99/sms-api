package lk.zerocode.School_management_system.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentAssignRequest {

    private List<Long> parentIds;
}
