package com.mysql.in.MySQL.service;

import com.mysql.in.MySQL.dto.request.EmployeeRequest;
import com.mysql.in.MySQL.dto.response.EmployeeFuctionResponse;
import com.mysql.in.MySQL.dto.response.EmployeeResponse;


import java.util.List;

public interface EmployeeService {
    EmployeeResponse creatEmployee(EmployeeRequest employeeRequest);
    Double  calculateAverageSalary();
    Double calculateMaxSalaray();
    List<EmployeeResponse> getEmployeesByDepartmentByStoreProcedure(Long departmentId);
    EmployeeFuctionResponse getNoOfEmployeeFromDepIdByFunction(Long deptId);
}
