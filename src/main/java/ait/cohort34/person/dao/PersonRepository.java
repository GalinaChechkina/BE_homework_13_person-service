package ait.cohort34.person.dao;

import ait.cohort34.person.dto.CityPopulationDto;
import ait.cohort34.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {

//@Query("select p from Person p where p.address.city=:cityName")//:cityName
// и не забудем указать аннотацию @Param("cityName") в аргументе метода
    List<Person> findByAddressCityIgnoreCase(String city);

    List<Person> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);

//@Query("select p from Person p where p.name=?1")//?1 первый аргумент из метода
    Stream <Person> findByNameIgnoreCase(String name);

@Query("select new ait.cohort34.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count (p) desc ")
List<CityPopulationDto> getCitiesPopulation();

}
