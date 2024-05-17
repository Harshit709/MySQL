package MySQL.controller;


import MySQL.dto.BaseResponse;
import MySQL.dto.request.EmployeeRequest;
import MySQL.dto.response.CurserResponse;
import MySQL.dto.response.EmployeeFuctionResponse;
import MySQL.dto.response.EmployeeResponse;
import MySQL.dto.response.StoreProcedureResponse;
import MySQL.service.EmployeeService;
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

import static MySQL.util.Message.ERROR_FOUND;
import static MySQL.util.Message.OPERATION_SUCCESSFUL;
import static MySQL.util.UrlConstants.CALCULATE_SALARY;
import static MySQL.util.UrlConstants.CREATE_EMPLOYEE;
import static MySQL.util.UrlConstants.EMPLOYEE;
import static MySQL.util.UrlConstants.EXECUTE_STORE_PROCEDURE;
import static MySQL.util.UrlConstants.GET_DATA_FROM_FUNCTION;
import static MySQL.util.UrlConstants.GET_DATA_FROM_STORE_PROCEDURE_CURSOR;
import static MySQL.util.UrlConstants.MAX_SALARY;


@RestController
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @PostMapping(CREATE_EMPLOYEE)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.creatEmployee(employeeRequest);
        if (employeeResponse != null) {
            return ResponseEntity.ok(
                    new BaseResponse<>(HttpStatus.OK.value(),
                            OPERATION_SUCCESSFUL, employeeResponse));

        } else {
            return ResponseEntity.ok(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),
                    ERROR_FOUND));

        }

    }

    @GetMapping(CALCULATE_SALARY)
    public ResponseEntity<?> averageSalary() {
        Double salaryAverage = employeeService.calculateAverageSalary();
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, salaryAverage));

    }

    @GetMapping(MAX_SALARY)
    public ResponseEntity<?> maxSalary() {
        Double maxSalary = employeeService.calculateMaxSalary();
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, maxSalary));
    }

    @GetMapping(EXECUTE_STORE_PROCEDURE)
    public ResponseEntity<?> getEmployeesByDepartmentByStoreProcedure(@RequestParam String departmentName,
                                                                      @RequestParam String employeeName
            , @RequestParam Double salary) {
        List<StoreProcedureResponse> responses = employeeService.
                getEmployeesByDepartmentByStoreProcedure(departmentName, employeeName, salary);
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, responses));


    }

    @GetMapping(GET_DATA_FROM_FUNCTION)
    public ResponseEntity<?> getNoOfEmployeeFromDepIdByFunction(Long deptId) {
        EmployeeFuctionResponse employeeFuctionResponse = employeeService.getNoOfEmployeeFromDepIdByFunction(deptId);
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, employeeFuctionResponse));


    }

    @GetMapping(GET_DATA_FROM_STORE_PROCEDURE_CURSOR)
    public ResponseEntity<?> getDataFromStoreProcedureByCursor() {
        List<CurserResponse> listByCursor = employeeService.getDataFromStoreProcedureByCursor();
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, listByCursor));

    }


}
