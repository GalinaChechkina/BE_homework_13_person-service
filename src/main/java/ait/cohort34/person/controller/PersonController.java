package ait.cohort34.person.controller;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityPopulationDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController{

    final PersonService personService;

    @PostMapping
    public boolean addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @GetMapping("/city/{city}")
    public Iterable<PersonDto> findByCity(@PathVariable String city) {
        return personService.findByCity(city);
    }

    @GetMapping("/ages/{from}/{to}")
    public Iterable<PersonDto> findByAge(@PathVariable Integer from, @PathVariable Integer to) {
        return personService.findByAge(from,to);
    }

    @GetMapping("/name/{name}")
    public Iterable<PersonDto> findByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCityPopulation(){ return personService.getCityPopulation();}

    @PutMapping("/{id}/name/{newName}")
    public PersonDto updateName(@PathVariable Integer id, @PathVariable String newName) {
        return personService.updateName(id, newName);
    }

    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto newAddressDto) {
        return personService.updateAddress(id, newAddressDto);
    }

    @DeleteMapping("/{id}")
    public PersonDto deleteById(@PathVariable Integer id) {
        return personService.deleteById(id);
    }
}
