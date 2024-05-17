package com.mysql.in.MySQL.dto.request;

import com.mysql.in.MySQL.entity.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class DepartmentRequest {
    String name;
    List<Employee> employees;

}
