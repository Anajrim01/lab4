package model;

import model.exceptions.CarNotFound;

public class CarFactory {
    public static Car createCar(String carType) { // maybe use enum?
        return switch (carType) {
            case "Saab95" -> new Saab95();
            case "Volvo240" -> new Volvo240();
            case "Scania" -> new Scania();
            default -> throw new CarNotFound("Unknown car type: " + carType);
        };
    }
}