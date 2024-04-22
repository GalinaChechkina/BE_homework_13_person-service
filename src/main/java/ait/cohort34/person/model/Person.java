package ait.cohort34.person.model;

import ait.cohort34.person.dto.AddressDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity  //теперь Person стал сущностью
// @Table(name = "persons")//если у таблицы в бд другое название

public class Person {

    @Id//обязательно для реляционной бд
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    @Embedded//поля адреса будут встроены в персону
    Address address;

}
