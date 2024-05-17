package com.mysql.in.MySQL.service.impl;

import com.mysql.in.MySQL.dto.request.DepartmentRequest;
import com.mysql.in.MySQL.dto.response.DepartmentResponse;
import com.mysql.in.MySQL.dto.response.DepartmentSalaryInfoView;
import com.mysql.in.MySQL.entity.Department;
import com.mysql.in.MySQL.entity.Employee;
import com.mysql.in.MySQL.repository.DepartmentRepository;
import com.mysql.in.MySQL.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {

        Department department = Department.builder()
                .name(departmentRequest.getName())
                .build();
        List<Employee> employees = departmentRequest.getEmployees().stream()
                .map(employeeRequest -> {
                    Employee employee = Employee.builder()
                            .name(employeeRequest.getName())
                            .salary(employeeRequest.getSalary())
                            .department(department) // Set department for each employee
                            .build();
                    return employee;
                }).toList();

        department.setEmployees(employees);
        Department createdDepartment = departmentRepository.save(department);
        return DepartmentResponse.builder()
                .id(createdDepartment.getId())
                .name(createdDepartment.getName())
                .employees(createdDepartment.getEmployees())
                .build();
    }

    @Override
    public Double calculateTotalSalaryExpenditure(Long id) {
        return departmentRepository.calculateTotalSalaryExpenditure(id);

    }
    @Override
    public List<DepartmentSalaryInfoView> getDepartmentSalaryDataFromView() {
        List<DepartmentSalaryInfoView> data = departmentRepository.getDepartmentSalaryData();
        return data;
    }


    }


