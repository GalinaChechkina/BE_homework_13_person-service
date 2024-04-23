package ait.cohort34.person.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class EmployeeDto extends PersonDto{

    String company;
    Integer salary; //BigDecimal лучше использовать этот тип для денег
}
