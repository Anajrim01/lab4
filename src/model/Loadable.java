package model;

import model.exceptions.*;
import model.interfaces.Transportable;

import java.util.Stack;

public class Loadable<T extends Car> {
    private final int maxCars;
    private final Stack<T> carStack = new Stack<>();
    private final Car transportCar;

    private static final double MAX_DISTANCE = 2.0;
    private static final double MAX_ANGLE = 70.0;
    private double angle = 0;

    public Loadable(Car transportCar, int maxCars) {
        if (maxCars < 0) {
            throw new IllegalArgumentException("Maximum number of cars must be non-negative.");
        }
        this.maxCars = maxCars;
        this.transportCar = transportCar;
    }

    public void loadCar(T car) {
        if (!canLoadCar(car)) {
            throw new FullCapacityException("Cannot load car: either full capacity or other validation conditions not met.");
        }
        carStack.push(car);
    }

    public T unloadCar() {
        if (!canUnloadCar()) {
            throw new UnloadException("Unloading not allowed in current state.");
        }
        return carStack.pop();
    }

    public void lower(double degrees) {
        if (degrees < 0) {
            throw new IllegalArgumentException("Degrees must be positive for lowering.");
        }
        if (transportCar.getCurrentSpeed() != 0) {
            throw new PlatformException("Cannot lower platform while moving.");
        }
        angle = Math.max(angle - degrees, 0);
    }

    public void raise(double degrees) {
        if (degrees < 0) {
            throw new IllegalArgumentException("Degrees must be positive for raising.");
        }
        if (transportCar.getCurrentSpeed() != 0) {
            throw new PlatformException("Cannot raise platform while moving.");
        }
        angle = Math.min(angle + degrees, MAX_ANGLE);
    }

    public double getAngle() {
        return angle;
    }

    public void setCarsPosition() {
        for (T car : carStack) {
            car.setX(transportCar.getX());
            car.setY(transportCar.getY());
        }
    }

    protected boolean canLoadCar(T car) {
        return angle == 0 &&
                carStack.size() < maxCars &&
                !(car instanceof Transportable) &&
                isWithinDistance(car);
    }

    private boolean canUnloadCar() {
        return angle == 0 && !carStack.isEmpty();
    }

    private boolean isWithinDistance(Car car) {
        double distance = calculateDistance(car);
        if (distance > MAX_DISTANCE) {
            throw new LoadException("Car is too far away to be loaded.");
        }
        return true;
    }

    private double calculateDistance(Car car) {
        if (!(car instanceof Transportable)) {
            return 0.0;
        }

        double dx = car.getX() - transportCar.getX();
        double dy = car.getY() - transportCar.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
