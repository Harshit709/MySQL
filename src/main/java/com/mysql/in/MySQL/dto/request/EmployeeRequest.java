package com.mysql.in.MySQL.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeRequest {
    String name;
    Double salary;

}
