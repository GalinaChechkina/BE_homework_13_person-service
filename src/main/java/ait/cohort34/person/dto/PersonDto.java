package ait.cohort34.person.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
// идентификатор name включается как PROPERTY с полем type
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")//в классе д.б. инф-ия о типе
@JsonSubTypes({
        @JsonSubTypes.Type(name = "child", value = ChildDto.class),
        @JsonSubTypes.Type(name = "employee", value = EmployeeDto.class),
        @JsonSubTypes.Type(name = "person", value = PersonDto.class),
})
public class PersonDto {
//мы хотим, чтобы наследники Person попадали в бд в свои соответствующие таблицы, а не только
//в таблицу persons
    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address;
}
