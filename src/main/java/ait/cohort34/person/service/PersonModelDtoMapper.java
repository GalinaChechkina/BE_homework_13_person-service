package ait.cohort34.person.service;

import ait.cohort34.person.dto.ChildDto;
import ait.cohort34.person.dto.EmployeeDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.model.Child;
import ait.cohort34.person.model.Employee;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersonModelDtoMapper {
    final ModelMapper modelMapper;

    public Person mapToModel(PersonDto personDto){
//чтобы сложить в бд не просто Person, а Child или Employee, н. сравнить объекты по типу класса
// при помощи либо метода instanceOf, либо метода getClass

        if (personDto.getClass()== ChildDto.class){
            return modelMapper.map(personDto, Child.class);
        }
        if (personDto.getClass()== EmployeeDto.class){
            return modelMapper.map(personDto, Employee.class);
        }

        return modelMapper.map(personDto, Person.class);
    }

    public PersonDto mapToDto(Person person){
//чтобы достать из бд не просто PersonDto, а ChildDto или EmployeeDto, н. сравнить объекты по типу класса
// при помощи либо метода instanceOf, либо метода getClass

        if (person.getClass()== Child.class){
            return modelMapper.map(person, ChildDto.class);
        }
        if (person.getClass()== Employee.class){
            return modelMapper.map(person, EmployeeDto.class);
        }

        return modelMapper.map(person, PersonDto.class);
    }
}
