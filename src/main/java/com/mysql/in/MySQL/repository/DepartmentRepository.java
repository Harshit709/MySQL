package com.mysql.in.MySQL.repository;

import com.mysql.in.MySQL.dto.response.DepartmentResponse;
import com.mysql.in.MySQL.dto.response.DepartmentSalaryInfoView;
import com.mysql.in.MySQL.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    /*
  Aggregate functions
 */
    @Query("SELECT SUM(e.salary) FROM Department d JOIN d.employees e WHERE d.id = :departmentId")
    Double calculateTotalSalaryExpenditure(@Param("departmentId") Long departmentId);



//View created manually in mysql workbench using this query//

    /*
    CREATE VIEW department_salary_view AS
SELECT
    d.department_id,
    d.department_name,
    COUNT(e.employee_id) AS num_employees,
    SUM(e.salary) AS total_salary
FROM
    departments d
    LEFT JOIN employees e ON d.department_id = e.department_id
GROUP BY
    d.department_id, d.department_name;
     */
    @Query(value = "SELECT d.department_id AS departmentId, d.department_name AS departmentName, " +
            "d.num_employees AS numEmployees, d.total_salary AS totalSalary " +
            "FROM department_salary_view d", nativeQuery = true)
    List<DepartmentSalaryInfoView> getDepartmentSalaryData();


}
