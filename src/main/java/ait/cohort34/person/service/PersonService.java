package ait.cohort34.person.service;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityPopulationDto;
import ait.cohort34.person.dto.PersonDto;

public interface PersonService {

     Boolean addPerson(PersonDto personDto);

     PersonDto findById(Integer id);

     Iterable<PersonDto> findByCity(String city);

     Iterable<PersonDto> findByAge(Integer from, Integer to);

     Iterable<PersonDto> findByName(String name);

     Iterable<CityPopulationDto> getCityPopulation();

     PersonDto updateName(Integer id, String newName);

     PersonDto updateAddress(Integer id, AddressDto newAddressDto);

     PersonDto deleteById(Integer id);

}
