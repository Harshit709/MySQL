package com.mysql.in.MySQL.service.impl;


import com.mysql.in.MySQL.dto.request.EmployeeRequest;
import com.mysql.in.MySQL.dto.response.EmployeeFuctionResponse;
import com.mysql.in.MySQL.dto.response.EmployeeResponse;
import com.mysql.in.MySQL.entity.Employee;
import com.mysql.in.MySQL.repository.EmployeeRepository;
import com.mysql.in.MySQL.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse creatEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .salary(employeeRequest.getSalary())
                .build();
        Employee createdEmployee = employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .id(createdEmployee.getId())
                .name(createdEmployee.getName())
                .salary(createdEmployee.getSalary())
                .build();

    }

    @Override
    public Double calculateAverageSalary() {
        return employeeRepository.calculateAverageSalary();
    }

    @Override
    public Double calculateMaxSalaray() {
        return employeeRepository.findMaxSalary();
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentByStoreProcedure(Long departmentId) {
        List<Employee> employees = employeeRepository.getEmployeesByDepartment(departmentId);
        return employees.stream()
                .map(this::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeFuctionResponse getNoOfEmployeeFromDepIdByFunction(Long deptId) {
        return employeeRepository.countEmployeesByDepartmentNativeQuery(deptId);
    }

    EmployeeResponse employeeToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .salary(employee.getSalary())
                .build();
    }
}
