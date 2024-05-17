package com.mysql.in.MySQL.dto.response;

public interface DepartmentSalaryInfoView {
    /*
      We have used this  interface for our custom response by get data  from view in our mysql workbench
     */
    Long getDepartmentId();

    String getDepartmentName();

    Long getNumEmployees();

    Double getTotalSalary();

}
