package MySQL.service;


import MySQL.dto.request.EmployeeRequest;
import MySQL.dto.response.CurserResponse;
import MySQL.dto.response.EmployeeFuctionResponse;
import MySQL.dto.response.EmployeeResponse;
import MySQL.dto.response.StoreProcedureResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse creatEmployee(EmployeeRequest employeeRequest);

    Double calculateAverageSalary();

    Double calculateMaxSalary();

    List<StoreProcedureResponse> getEmployeesByDepartmentByStoreProcedure(String departmentName, String employeeName, Double salary);

    EmployeeFuctionResponse getNoOfEmployeeFromDepIdByFunction(Long deptId);

    List<CurserResponse> getDataFromStoreProcedureByCursor();
}
