package ait.cohort34.person.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Getter
@Entity
public class Employee extends Person{
    String company;
    int Salary;

    public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, int salary) {
        super(id, name, birthDate, address);
        this.company = company;
        Salary = salary;
    }
}
