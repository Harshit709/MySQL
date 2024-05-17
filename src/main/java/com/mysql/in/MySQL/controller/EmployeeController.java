package com.mysql.in.MySQL.controller;

import com.mysql.in.MySQL.dto.BaseResponse;
import com.mysql.in.MySQL.dto.request.EmployeeRequest;
import com.mysql.in.MySQL.dto.response.EmployeeFuctionResponse;
import com.mysql.in.MySQL.dto.response.EmployeeResponse;
import com.mysql.in.MySQL.service.EmployeeService;
import com.mysql.in.MySQL.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mysql.in.MySQL.util.UrlConstants.CALCULATE_SALARY;
import static com.mysql.in.MySQL.util.UrlConstants.CREATE_EMPLOYEE;
import static com.mysql.in.MySQL.util.UrlConstants.EMPLOYEE_URL;
import static com.mysql.in.MySQL.util.UrlConstants.EXECUTE_STORE_PROCEDURE;
import static com.mysql.in.MySQL.util.UrlConstants.GET_DATA_FROM_FUNCTION;
import static com.mysql.in.MySQL.util.UrlConstants.MAX_SALARY;

@RestController
@RequestMapping(EMPLOYEE_URL)
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @PostMapping(CREATE_EMPLOYEE)
    public ResponseEntity<BaseResponse<EmployeeResponse>> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.creatEmployee(employeeRequest);
        if (employeeResponse != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>(employeeResponse,
                            Message.EMPLOYEE_CREATED_SUCCESSFULLY
                    ), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new BaseResponse<>
                    (Message.ERROR_FOUND),
                    HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(CALCULATE_SALARY)
    public ResponseEntity<BaseResponse<Double>> averageSalary() {
        Double salaryAverage = employeeService.calculateAverageSalary();
        return new ResponseEntity<>(
                new BaseResponse<>(
                        salaryAverage,
                        Message.OPERATION_SUCCESSFUL),
                HttpStatus.OK
        );

    }

    @GetMapping(MAX_SALARY)
    public ResponseEntity<BaseResponse<Double>> maxSalary() {
        Double maxSalary = employeeService.calculateMaxSalaray();
        return new ResponseEntity<>(new BaseResponse<>(
                maxSalary,
                Message.OPERATION_SUCCESSFUL),
                HttpStatus.OK);
    }

    @GetMapping(EXECUTE_STORE_PROCEDURE)
    public ResponseEntity<BaseResponse<List<EmployeeResponse>>> getEmployeesByDepartmentByStoreProcedure(@RequestParam Long departmentId) {
        List<EmployeeResponse> employees = employeeService.getEmployeesByDepartmentByStoreProcedure(departmentId);
        return new ResponseEntity<>(new BaseResponse<>(
                employees,
                Message.OPERATION_SUCCESSFUL)
                , HttpStatus.OK);

    }

    @GetMapping(GET_DATA_FROM_FUNCTION)
    public ResponseEntity<BaseResponse<EmployeeFuctionResponse>> getNoOfEmployeeFromDepIdByFunction(Long deptId) {
        EmployeeFuctionResponse employeeFuctionResponse = employeeService.getNoOfEmployeeFromDepIdByFunction(deptId);
        return new ResponseEntity<>(new BaseResponse<>(
                employeeFuctionResponse,
                Message.OPERATION_SUCCESSFUL),
                HttpStatus.OK);


    }


}
