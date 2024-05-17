package com.mysql.in.MySQL.service;

import com.mysql.in.MySQL.dto.request.DepartmentRequest;
import com.mysql.in.MySQL.dto.response.DepartmentResponse;
import com.mysql.in.MySQL.dto.response.DepartmentSalaryInfoView;


import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    Double calculateTotalSalaryExpenditure(Long id);
    List<DepartmentSalaryInfoView> getDepartmentSalaryDataFromView();



}

