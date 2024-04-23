package ait.cohort34.person.controller;

import ait.cohort34.person.dto.*;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    final PersonService personService;

    @PostMapping
    public boolean addPerson(@RequestBody PersonDto personDto){

        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @GetMapping("/children")
    public ChildDto[] findAllChildren() {
        return personService.findAllChildren();
    }

    @GetMapping("/salary/{minSalary}/{maxSalary}")
    public EmployeeDto[] findEmployeesBySalary(@PathVariable Integer minSalary,@PathVariable Integer maxSalary) {
        return personService.findEmployeesBySalary(minSalary,maxSalary);
    }

    @GetMapping("/city/{city}")
    public PersonDto[] findByCity(@PathVariable String city) {
        return personService.findByCity(city);
    }

    @GetMapping("/ages/{from}/{to}")
    public PersonDto[] findByAge(@PathVariable Integer from, @PathVariable Integer to) {
        return personService.findByAge(from,to);
    }

    @GetMapping("/name/{name}")
    public PersonDto[] findByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCitiesPopulation(){ return personService.getCitiesPopulation();}

    //@Transactional можно ставить транзакцию в контроллере, но Эдуард ставит в сервисе
    @PutMapping("/{id}/name/{newName}")
    public PersonDto updateName(@PathVariable Integer id, @PathVariable String newName) {
        return personService.updateName(id, newName);
    }
    //@Transactional
    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto newAddressDto) {
        return personService.updateAddress(id, newAddressDto);
    }

    @DeleteMapping("/{id}")
    public PersonDto deleteById(@PathVariable Integer id) {
        return personService.deleteById(id);
    }
}
