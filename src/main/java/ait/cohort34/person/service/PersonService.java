package ait.cohort34.person.service;

import ait.cohort34.person.dto.*;

public interface PersonService {

     Boolean addPerson(PersonDto personDto);

     Boolean addChild(ChildDto childDto);

     Boolean addEmployee(EmployeeDto employeeDto);

     PersonDto findById(Integer id);

     Iterable<ChildDto> findAllChildren();

     EmployeeDto[]findEmployeesBySalary(Integer minSalary, Integer maxSalary);

     PersonDto[] findByCity(String city);

     PersonDto[] findByAge(Integer from, Integer to);

     PersonDto[] findByName(String name);

     Iterable<CityPopulationDto> getCityPopulation();

     PersonDto updateName(Integer id, String newName);

     PersonDto updateAddress(Integer id, AddressDto newAddressDto);

     PersonDto deleteById(Integer id);
}
