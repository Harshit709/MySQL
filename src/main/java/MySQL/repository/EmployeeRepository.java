package MySQL.repository;

import MySQL.dto.response.CurserResponse;
import MySQL.dto.response.EmployeeFuctionResponse;
import MySQL.dto.response.StoreProcedureResponse;
import MySQL.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /*
     * Aggregate functions
     */

    // Query to calculate average salary of all employees
    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double calculateAverageSalary();


    // Query to find the maximum salary among all employees
    @Query("SELECT MAX(e.salary) FROM Employee e")
    Double findMaxSalary();

    // Stored procedure to process department and employees
    @Modifying
    @Transactional
    @Query(value = "CALL ProcessDepartmentAndEmployees(:departmentName, :employeeName, :employeeSalary)", nativeQuery = true)
    List<StoreProcedureResponse> processDepartmentAndEmployees(@Param("departmentName") String departmentName,
                                                               @Param("employeeName") String employeeName,
                                                               @Param("employeeSalary") Double employeeSalary);


    // Stored procedure to process employees using cursor
    @Query(nativeQuery = true, value = "CALL ProcessEmployees()")
    @Transactional
    List<CurserResponse> processEmployees();



    // Function call to count employees by department
    @Query(value = "SELECT countEmployeesByDepartment(:deptId) as noOfEmployee", nativeQuery = true)
    EmployeeFuctionResponse countEmployeesByDepartmentNativeQuery(Long deptId);


    // SQL query to create function 'countEmployeesByDepartment'

    // CREATE FUNCTION countEmployeesByDepartment(deptId INT) RETURNS INT
    // DETERMINISTIC
    // READS SQL DATA
    // BEGIN
    //     DECLARE numEmployees INT;
    //     SELECT COUNT(*) INTO numEmployees FROM employees WHERE department_id = deptId;
    //     RETURN numEmployees;
    // END //
    // DELIMITER ;


    //Store procedure  for processDepartmentAndEmployees

//    BEGIN
//    DECLARE departmentId INT;
//    DECLARE existingEmployeeCount INT;
//
//    -- Check if department already exists
//    SELECT department_id INTO departmentId FROM departments WHERE department_name = departmentName LIMIT 1;
//
//    -- If department doesn't exist, create it
//    IF departmentId IS NULL THEN
//    INSERT INTO departments (department_name) VALUES (departmentName);
//    SET departmentId = LAST_INSERT_ID();
//    END IF;
//
//    -- Check if employee already exists in the department
//    SELECT COUNT(*) INTO existingEmployeeCount FROM employees WHERE employee_name = employeeName AND department_id = departmentId;
//
//    -- If employee doesn't exist, insert them
//    IF existingEmployeeCount = 0 THEN
//    INSERT INTO employees (employee_name, salary, department_id) VALUES (employeeName, employeeSalary, departmentId);
//    ELSE
//        -- If employee exists, update their salary
//    UPDATE employees SET salary = employeeSalary WHERE employee_name = employeeName AND department_id = departmentId;
//    END IF;
//
//    -- Return the department ID and existing employee count
//    SELECT departmentId, existingEmployeeCount;
//    END


    //Store procedure for Cursor working in SQl

//    BEGIN
//    -- Declare variables
//    DECLARE done BOOLEAN DEFAULT FALSE;
//    DECLARE employee_name_var VARCHAR(255);
//
//    -- Declare cursor for selecting employee names
//    DECLARE cur CURSOR FOR SELECT employee_name FROM employees;
//
//    -- Declare continue handler for the cursor
//    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
//
//    -- Drop temporary table if exists
//    DROP TEMPORARY TABLE IF EXISTS employee_processing_results;
//
//    -- Create temporary table to store processing results
//    CREATE TEMPORARY TABLE employee_processing_results (
//            id INT AUTO_INCREMENT PRIMARY KEY,
//            message VARCHAR(255)
//    );
//
//    -- Open cursor
//    OPEN cur;
//
//    -- Loop through employee names
//    read_loop: LOOP
//        -- Fetch next row from cursor
//    FETCH cur INTO employee_name_var;
//
//        -- If no more rows, exit loop
//    IF done THEN
//    LEAVE read_loop;
//    END IF;
//
//        -- Process each employee name (example: print)
//    INSERT INTO employee_processing_results (message)
//    VALUES (CONCAT('Processing Employee: ', employee_name_var));
//    END LOOP;
//
//    -- Close cursor
//    CLOSE cur;
//
//    -- Select processing results from temporary table
//    SELECT * FROM employee_processing_results;
//
//    -- Drop temporary table
//    DROP TEMPORARY TABLE IF EXISTS employee_processing_results;
//    END

}

