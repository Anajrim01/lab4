package model;

import model.exceptions.CarNotFound;
import java.util.Random;

public class CarFactory {
    public enum CarType {
        Saab95, Volvo240, Scania;

        public static CarType getRandomType() {
            CarType[] values = CarType.values();
            return values[new Random().nextInt(values.length)];
        }
    }

    public static Car createCar(CarType carType) {
        return switch (carType) {
            case Saab95 -> new Saab95();
            case Volvo240 -> new Volvo240();
            case Scania -> new Scania();
        };
    }
}
