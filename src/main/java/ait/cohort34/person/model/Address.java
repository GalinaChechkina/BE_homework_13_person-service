package ait.cohort34.person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//создали сеттеры, геттеры, ....
@AllArgsConstructor
@NoArgsConstructor//н. для modelmapper
public class Address {

    String city;
    String street;
    Integer building;

}
