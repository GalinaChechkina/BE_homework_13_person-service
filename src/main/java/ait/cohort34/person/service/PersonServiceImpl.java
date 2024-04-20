package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityPopulationDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.exceptions.PersonNotFoundException;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if(personRepository.existsById(personDto.getId())){
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findByCity(String city) {
        List<Person> persons = personRepository.findByCity(city);
        return persons.stream()
                .map(e->modelMapper.map(e,PersonDto.class))
                .toList();
    }

    @Override
    public Iterable<PersonDto> findByAge(Integer from, Integer to) {
        LocalDate fromDate = LocalDate.now().minusYears(to);
        LocalDate toDate = LocalDate.now().minusYears(from);

        List<Person> persons = personRepository.findByBirthDateBetween(fromDate, toDate);
        return   persons.stream()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PersonDto> findByName(String name) {
        List<Person> persons = personRepository.findByName(name);
        return persons.stream()
                .map(e->modelMapper.map(e, PersonDto.class))
                .toList();
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {
        Map<String,Integer> cityPopulationMap = new HashMap<>();
        Iterable <Person> persons = personRepository.findAll();
        for(Person person: persons) {
            String city = person.getAddress().getCity();
//взяла у каждого жителя его город и положила в мапу:
//ключ: сам город, значение: если такого города еще нет, то 0+1, если уже есть, то взяла значение мапы, кот. там уже лежит +1
            cityPopulationMap.put(city, cityPopulationMap.getOrDefault(city, 0) + 1);
        }
//мапу перевела в сет, застримила, в методе мэп создала из пар ключ:значение объекты CityPopulationDto и в лист
        return cityPopulationMap.entrySet().stream()
                .map(entry-> new CityPopulationDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public PersonDto updateName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
        if(newName!=null){
            person.setName(newName);
            person = personRepository.save(person);
        }
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updateAddress(Integer id, AddressDto newAddressDto) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
        String city= newAddressDto.getCity();
        if(city!=null){
            person.getAddress().setCity(city);
        }
        String street= newAddressDto.getStreet();
        if(street!=null){
            person.getAddress().setStreet(street);
        }
        Integer building= newAddressDto.getBuilding();
        if(building!=0){
            person.getAddress().setBuilding(building);
        }
        person= personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deleteById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }
}
