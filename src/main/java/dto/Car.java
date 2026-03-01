package dto;

import enums.Fuel;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Car {
    private String city;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private int seats;
    private String classAuto;
    private String regNumber;
    private double price;
    private String about;
    private String image;
}

