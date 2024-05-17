package MySQL.mapper;

import MySQL.dto.request.DepartmentRequest;
import MySQL.dto.response.DepartmentResponse;
import MySQL.entity.Department;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    @Mapping(target = "employees",ignore = true)
    Department toEntity(DepartmentRequest departmentRequest);
    DepartmentResponse toResponse(Department department);

}
