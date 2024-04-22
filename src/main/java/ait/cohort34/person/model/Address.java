package ait.cohort34.person.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//создали сеттеры, геттеры, ....
@AllArgsConstructor
@NoArgsConstructor//н. для modelmapper
@Embeddable//поля адреса будут встроены в персону- можно аннотацией у класса, можно аннотацией
// у поля Address в классе Person, можно и там и там
public class Address {

    String city;
    String street;
    Integer building;

}
