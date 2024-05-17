package com.mysql.in.MySQL.repository;

import com.mysql.in.MySQL.dto.response.EmployeeFuctionResponse;
import com.mysql.in.MySQL.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    /*
     Aggregate functions
    */
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double calculateAverageSalary();

    @Query("SELECT MAX(e.salary) FROM Employee e")
    Double findMaxSalary();

    //store procedure

    //query in store procedure use to created store procedure from

//    DELIMITER //
//    CREATE PROCEDURE GetEmployeesByDepartment(IN departmentId INT)
//    BEGIN
//    SELECT * FROM employees WHERE department_id = departmentId;
//    END //
//            DELIMITER ;

    @Query(value = "CALL GetEmployeesByDepartment(:departmentId)", nativeQuery = true)
    List<Employee> getEmployeesByDepartment(@Param("departmentId") Long departmentId);

    // making an function in my workbench name of that function is countEmployeesByDepartment and param is deptId
    // query to manually create function in mysql ->
    // CREATE FUNCTION countEmployeesByDepartment(deptId INT) RETURNS INT
    //DETERMINISTIC
    //READS SQL DATA
    //BEGIN
    //    DECLARE numEmployees INT;
    //    SELECT COUNT(*) INTO numEmployees FROM employees WHERE department_id = deptId;
    //    RETURN numEmployees;
    //END //
    //
    //DELIMITER ;

    @Query(value = "SELECT countEmployeesByDepartment(:deptId) as noOfEmployee", nativeQuery = true)
    EmployeeFuctionResponse countEmployeesByDepartmentNativeQuery(Long deptId);



}
