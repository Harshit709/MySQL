package MySQL.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class EmployeeResponse {
    Long id;
    String name;
    Double salary;
}
