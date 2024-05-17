package MySQL.mapper;

import MySQL.dto.request.EmployeeRequest;
import MySQL.dto.response.EmployeeResponse;
import MySQL.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    Employee toEntity(EmployeeRequest employeeRequest);
    EmployeeResponse toResponse(Employee employee);



}
