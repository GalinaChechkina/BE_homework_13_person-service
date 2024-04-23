package ait.cohort34.person.service;

import ait.cohort34.person.dto.*;

public interface PersonService {

     Boolean addPerson(PersonDto personDto);

     PersonDto findById(Integer id);

     ChildDto[] findAllChildren();

     EmployeeDto[]findEmployeesBySalary(Integer minSalary, Integer maxSalary);

     PersonDto[] findByCity(String city);

     PersonDto[] findByAge(Integer from, Integer to);

     PersonDto[] findByName(String name);

     PersonDto updateName(Integer id, String newName);

     PersonDto updateAddress(Integer id, AddressDto newAddressDto);

     PersonDto deleteById(Integer id);

     Iterable<CityPopulationDto> getCitiesPopulation();
}
