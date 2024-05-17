package MySQL.controller;

import MySQL.dto.BaseResponse;
import MySQL.dto.request.DepartmentRequest;
import MySQL.dto.response.DepartmentResponse;
import MySQL.dto.response.DepartmentSalaryInfoView;
import MySQL.service.DepartmentService;
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

import static MySQL.util.Message.OPERATION_SUCCESSFUL;
import static MySQL.util.UrlConstants.CREATE_DEPARTMENT;
import static MySQL.util.UrlConstants.DATA_FROM_VIEW;
import static MySQL.util.UrlConstants.DEPARTMENT;
import static MySQL.util.UrlConstants.SALARY_EXPENDITURE;


@RestController
@RequestMapping(DEPARTMENT)
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @PostMapping(CREATE_DEPARTMENT)
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, departmentResponse));
    }

    @GetMapping(SALARY_EXPENDITURE)
    public ResponseEntity<?> calculateTotalSalaryExpenditure(@RequestParam Long departmentId) {
        Double salaryExpenditure = departmentService.calculateTotalSalaryExpenditure(departmentId);
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, salaryExpenditure));

    }
    @GetMapping(DATA_FROM_VIEW)
    public ResponseEntity<?> getDepartmentSalaryDataFromView() {
        List<DepartmentSalaryInfoView> data = departmentService.getDepartmentSalaryDataFromView();
        return ResponseEntity.ok(
                new BaseResponse<>(HttpStatus.OK.value(),
                        OPERATION_SUCCESSFUL, data));

    }

}
