package com.mysql.in.MySQL.controller;

import com.mysql.in.MySQL.dto.BaseResponse;
import com.mysql.in.MySQL.dto.request.DepartmentRequest;
import com.mysql.in.MySQL.dto.response.DepartmentResponse;
import com.mysql.in.MySQL.dto.response.DepartmentSalaryInfoView;
import com.mysql.in.MySQL.service.DepartmentService;
import com.mysql.in.MySQL.util.UrlConstants;
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

import static com.mysql.in.MySQL.util.Message.OPERATION_SUCCESSFUL;
import static com.mysql.in.MySQL.util.Message.DEPARTMENT_CREATED_SUCCESSFULLY;

@RestController
@RequestMapping(UrlConstants.DEPARTMENT_URL)
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    @PostMapping(UrlConstants.CREATE_DEPARTMENT)
    public ResponseEntity<BaseResponse<DepartmentResponse>> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(
                new BaseResponse<>(departmentResponse,
                        DEPARTMENT_CREATED_SUCCESSFULLY)
                , HttpStatus.CREATED);
    }

    @GetMapping(UrlConstants.SALARY_EXPENDITURE)
    public ResponseEntity<BaseResponse<Double>> calculateTotalSalaryExpenditure(@RequestParam Long departmentId) {
        Double salaryExpenditure = departmentService.calculateTotalSalaryExpenditure(departmentId);
        return new ResponseEntity<>(
                new BaseResponse<>(salaryExpenditure
                        , OPERATION_SUCCESSFUL)
                , HttpStatus.OK
        );
    }

    @GetMapping(UrlConstants.DATA_FROM_VIEW)
    public ResponseEntity<BaseResponse<List<DepartmentSalaryInfoView>>> getDepartmentSalaryDataFromView() {
        List<DepartmentSalaryInfoView> data = departmentService.getDepartmentSalaryDataFromView();
        return new ResponseEntity<>(new BaseResponse<>(
                data,
                OPERATION_SUCCESSFUL
        ), HttpStatus.OK);


    }


}
