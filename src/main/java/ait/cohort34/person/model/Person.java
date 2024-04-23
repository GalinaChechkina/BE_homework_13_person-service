package ait.cohort34.person.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity  //теперь Person стал сущностью
@Table(name = "persons")//если у таблицы в бд другое название
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id//обязательно для реляционной бд
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    //@Embedded//поля адреса будут встроены в персону
    Address address;

}
