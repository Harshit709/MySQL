package com.mysql.in.MySQL.service.impl;

import com.mysql.in.MySQL.entity.Department;
import com.mysql.in.MySQL.entity.Employee;
import com.mysql.in.MySQL.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void create() {
        Department department = Department.builder()
                .name("SBI").build();
        Employee employee = Employee.builder()
                .name("ragav")
                .salary(1500.0)
                .department(department)
                .build();
        employeeRepository.save(employee);
    }

    @Test
    void getStoreProcedure() {
        System.out.println(employeeRepository.getEmployeesByDepartment(1L));
    }

}