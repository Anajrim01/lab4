package model;

import model.exceptions.*;
import model.interfaces.TransportCar;

import java.util.Stack;

public class Loadable<T extends Car> {
    private final int MAX_CARS;
    private final Stack<T> carStack = new Stack<>();
    private final Car transport;

    private static final double MAX_DISTANCE = 2;
    private double angle = 0;

    public Loadable(Car transportCar, int maxCars) {
        this.MAX_CARS = maxCars;
        this.transport = transportCar;
    }

    public void loadCar(T car) {
        if (!validateLoad(car))
            throw new FullCapacityException("There's no space for this vehicle.");;
        carStack.push(car);
    }

    public T unloadCar() {
        if (angle != 0 || carStack.isEmpty())
            throw new UnloadException("Unloading not allowed in current state");
        return carStack.pop();
    }

    public void lower(double deg) {
        if (angle - deg >= 0 && transport.getCurrentSpeed() == 0) {
            angle -= deg;
        } else {
            throw new IllegalArgumentException("Cannot lower platform below 0 degrees.");
        }
    }

    public void raise(double deg) {
        if (deg < 0) throw new IllegalArgumentException("Degrees must be positive");
        if (transport.getCurrentSpeed() != 0) {
            throw new PlatformException("Platform adjustment while moving");
        }
        if (angle + deg > 70) {
            throw new PlatformException(
                    String.format("Angle must be between 0 and %.1f degrees", 70.0));
        }
        angle += deg;
    }

    public void setCarsPosition() {
        for (T car : carStack) {
            car.setX(transport.getX());
            car.setY(transport.getY());
        }
    }

    public double getAngle() {
        return angle;
    }

    protected boolean validateLoad(Car car) {
        return angle == 0 &&
                carStack.size() < MAX_CARS &&
                !(car instanceof TransportCar) &&
                calculateDistance(car);
    }

    private boolean calculateDistance(Car car) {
        if (transport == null) return true;
        if (Math.sqrt(Math.pow(car.getX() - transport.getX(), 2) +
                Math.pow(car.getY() - transport.getY(), 2)) > MAX_DISTANCE) {
            throw new LoadException("Car is too far away.");
        }
        return true;
    }
}
