package MySQL.service.impl;

import MySQL.dto.response.CurserResponse;
import MySQL.dto.response.StoreProcedureResponse;
import MySQL.mapper.EmployeeMapper;
import MySQL.dto.request.EmployeeRequest;
import MySQL.dto.response.EmployeeFuctionResponse;
import MySQL.dto.response.EmployeeResponse;
import MySQL.entity.Employee;
import MySQL.repository.EmployeeRepository;
import MySQL.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse creatEmployee(EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeRequest);
        Employee createdEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toResponse(createdEmployee);

    }

    @Override
    public Double calculateAverageSalary() {
        return employeeRepository.calculateAverageSalary();
    }

    @Override
    public Double calculateMaxSalary() {
        return employeeRepository.findMaxSalary();
    }

    @Override
    public  List<StoreProcedureResponse> getEmployeesByDepartmentByStoreProcedure(String departmentName, String employeeName, Double salary) {
      List<StoreProcedureResponse> responses= employeeRepository.processDepartmentAndEmployees(departmentName, employeeName, salary);
      return responses;
    }

    @Override
    public EmployeeFuctionResponse getNoOfEmployeeFromDepIdByFunction(Long deptId) {
        return employeeRepository.countEmployeesByDepartmentNativeQuery(deptId);
    }

    @Override
    public List<CurserResponse> getDataFromStoreProcedureByCursor() {
        return employeeRepository.processEmployees();
    }

}
