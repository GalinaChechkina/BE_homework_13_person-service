package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.*;
import ait.cohort34.person.dto.exceptions.PersonNotFoundException;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Child;
import ait.cohort34.person.model.Employee;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
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
    public Iterable<ChildDto> findAllChildren() {
        return null;
    }

    @Override
    public EmployeeDto[] findEmployeesBySalary(Integer minSalary, Integer maxSalary) {
        return new EmployeeDto[0];
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDto[] findByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(e->modelMapper.map(e,PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDto[] findByAge(Integer from, Integer to) {
        LocalDate fromDate = LocalDate.now().minusYears(to);
        LocalDate toDate = LocalDate.now().minusYears(from);

        return   personRepository.findByBirthDateBetween(fromDate, toDate)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    @Transactional(readOnly = true)//так пойдет, т.к. хоть Transactional неэффективна, но с readOnly = true
//чтение б. происходить с параллельном режиме, а изменение в транзакционном
    public PersonDto[] findByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(e->modelMapper.map(e, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personRepository.getCitiesPopulation();//вот так нужно!

//        Map<String,Integer> cityPopulationMap = new HashMap<>();
//        Iterable <Person> persons = personRepository.findAll();
//        for(Person person: persons) {
//            String city = person.getAddress().getCity();
////взяла у каждого жителя его город и положила в мапу:
////ключ: сам город, значение: если такого города еще нет, то 0+1, если уже есть, то взяла значение мапы, кот. там уже лежит +1
//            cityPopulationMap.put(city, cityPopulationMap.getOrDefault(city, 0) + 1);
//        }
////мапу перевела в сет, застримила, в методе мэп создала из пар ключ:значение объекты CityPopulationDto и в лист
//        return cityPopulationMap.entrySet().stream()
//                .map(entry-> new CityPopulationDto(entry.getKey(), entry.getValue()))
//                .toList();
    }

    @Override
    @Transactional//берем транзакцию от спринга (плюс реляционных бд,
    // нужна для того, чтобы одновременно не м. произойти несколько обновлений)
    public PersonDto updateName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
        if(newName!=null){
            person.setName(newName);
//            person = personRepository.save(person); в транзакционных методах save не нужен, т.к.
// в них по завершении все равно делается commit
        }
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updateAddress(Integer id, AddressDto newAddressDto) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);

        person.setAddress(modelMapper.map(newAddressDto, Address.class));// так лучше

//        String city= newAddressDto.getCity();
//        if(city!=null){
//            person.getAddress().setCity(city);
//        }
//        String street= newAddressDto.getStreet();
//        if(street!=null){
//            person.getAddress().setStreet(street);
//        }
//        Integer building= newAddressDto.getBuilding();
//        if(building!=0){
//            person.getAddress().setBuilding(building);
//        }
//        person= personRepository.save(person);

        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto deleteById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(RuntimeException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
    @Transactional
    @Override
    public void run(String... args)throws Exception{
        if(personRepository.count()==0){
            Person person = new Person(1000, "Jo",LocalDate.of(1985,3,11),
                    new Address("Berlin", "Alexander pl.",50));
            Child child = new Child(2000,"Karl", LocalDate.of(1985,3,11),
                    new Address("Hamburg","HBH",12),"Sunny");
            Employee employee = new Employee(3000, "Nina",LocalDate.of(1980,6,11),
                    new Address("Frankfurt", "Uni str.",12), "Mercury",8000);
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);
        }
    }
}
