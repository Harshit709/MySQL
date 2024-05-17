package MySQL.dto.response;


import MySQL.entity.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DepartmentResponse {
    Long id;
    String name;
    List<Employee> employees;

}
