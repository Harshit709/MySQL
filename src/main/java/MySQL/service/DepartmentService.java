package MySQL.service;


import MySQL.dto.request.DepartmentRequest;
import MySQL.dto.response.DepartmentResponse;
import MySQL.dto.response.DepartmentSalaryInfoView;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    Double calculateTotalSalaryExpenditure(Long id);
    List<DepartmentSalaryInfoView> getDepartmentSalaryDataFromView();



}

