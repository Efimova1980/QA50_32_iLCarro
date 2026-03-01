package utils;

import dto.Car;
import enums.Fuel;
import net.datafaker.Faker;

public class CarFactory {
    static Faker faker = new Faker();
    public static Car positiveCar(){
        Car car = Car.builder()
                .city("Rehovot")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween(1885, 2025)))
                .regNumber(faker.vehicle().licensePlate())
                .seats(faker.number().numberBetween(2,4))
                .fuel(faker.options().option(Fuel.values()))
                .classAuto(faker.vehicle().carType())
                .price(faker.number().randomDouble(2,1, 10))
                .about("My car")
                .image("src/main/resources/car.png")
                .build();
        return car;
    }
}
