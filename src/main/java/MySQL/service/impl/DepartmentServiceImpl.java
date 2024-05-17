package MySQL.service.impl;


import MySQL.mapper.DepartmentMapper;
import MySQL.dto.request.DepartmentRequest;
import MySQL.dto.response.DepartmentResponse;
import MySQL.dto.response.DepartmentSalaryInfoView;
import MySQL.entity.Department;
import MySQL.repository.DepartmentRepository;
import MySQL.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {

        Department department =
                DepartmentMapper.
                        INSTANCE.toEntity(departmentRequest);
        Department createdDepartment = departmentRepository.save(department);
        return DepartmentMapper.
                INSTANCE.
                toResponse(createdDepartment);
    }

    @Override
    public Double calculateTotalSalaryExpenditure(Long id) {
        return departmentRepository.calculateTotalSalaryExpenditure(id);

    }

    @Override
    public List<DepartmentSalaryInfoView> getDepartmentSalaryDataFromView() {
        List<DepartmentSalaryInfoView> data = departmentRepository.findDepartmentSalaryInfo();
        return data;
    }


}


