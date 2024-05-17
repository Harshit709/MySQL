package MySQL.repository;

import MySQL.dto.response.DepartmentSalaryInfoView;
import MySQL.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /*
     * Calculates the total salary expenditure for a given department.
     *
     * @param departmentId The ID of the department.
     * @return The total salary expenditure of the department.
     */
    @Query("SELECT SUM(e.salary) FROM Department d JOIN d.employees e WHERE d.id = :departmentId")
    Double calculateTotalSalaryExpenditure(@Param("departmentId") Long departmentId);


    /*
     * Retrieves department salary data from a custom view named "department_salary_view" in the database.
     * The view should have been manually created in MySQL Workbench using the provided SQL query.
     *
     * @return A list of DepartmentSalaryInfoView objects containing department salary information.
     */
    @Query(value = "SELECT " +
            "CASE " +
            "   WHEN e.avg_salary >= 50000 THEN 'High Salary' " +
            "   WHEN e.avg_salary >= 30000 THEN 'Medium Salary' " +
            "   ELSE 'Low Salary' " +
            "END AS salaryCategory, " +
            "e.num_employees AS noOfEmployee, " +
            "e.avg_salary AS avgSalary " +
            "FROM employee_avg_salary_view e", nativeQuery = true)
    List<DepartmentSalaryInfoView> findDepartmentSalaryInfo();


    // SQL query to create  'View'

//    CREATE VIEW employee_avg_salary_view AS
//    SELECT
//            CASE
//    WHEN e.salary >= 50000 THEN 'High Salary'
//    WHEN e.salary >= 30000 THEN 'Medium Salary'
//    ELSE 'Low Salary'
//    END AS salary_category,
//    COUNT(*) AS num_employees,
//    AVG(e.salary) AS avg_salary
//    FROM
//    employees e
//    GROUP BY
//    CASE
//    WHEN e.salary >= 50000 THEN 'High Salary'
//    WHEN e.salary >= 30000 THEN 'Medium Salary'
//    ELSE 'Low Salary'
//    END;
}
