package MySQL.dto.response;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface StoreProcedureResponse {
    Long getDepartmentId();
    Integer getExistingEmployeeCount();


}
