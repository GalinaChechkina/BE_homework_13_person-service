package ait.cohort34.person.dao;

import ait.cohort34.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p WHERE p.address.city = :city")
    List<Person> findByCity(@Param("city")String city);

    List<Person> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);


    List<Person> findByName(String name);

}
