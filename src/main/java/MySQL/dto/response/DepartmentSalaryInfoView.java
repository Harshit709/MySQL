package MySQL.dto.response;

public interface DepartmentSalaryInfoView {
    /*
      We have used this  interface for our custom response by get data  from view in our mysql workbench
     */
    String getSalaryCategory();

    Long getNoOfEmployee();

    Long getAvgSalary();



}
