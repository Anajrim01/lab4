package model;

import model.interfaces.Drawable;
import model.interfaces.Observable;
import model.interfaces.Observer;
import view.CarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarModel implements Observable {
    private static final int MAX_CARS = 10;
    private final Verkstad<Volvo240> volvoVerkstad = new Verkstad<>(5);
    private final List<Car> cars = new CopyOnWriteArrayList<>(); // concurrently safe
    private final List<Observer> observers = new ArrayList<>();

    public void checkCollisionWithVolvoVerkstad(Car car) {
        if (car instanceof Volvo240 volvo && isNearVerkstad(car, volvoVerkstad)) {
            volvoVerkstad.loadCar(volvo);
            cars.remove(volvo);
            System.out.println(volvo.getModelName() + " is in the service shop");
            notifyObservers();
        }
    }

    private boolean isNearVerkstad(Car car, Verkstad<? extends Car> verkstad) {
        return Math.abs(car.getX() - verkstad.getX()) < 10
                && Math.abs(car.getY() - verkstad.getY()) < 10; // checking Y-axis is redundant
    }

    public void addCar(Car car) {
        cars.add(car);
        notifyObservers();
    }

    public void removeCar() {
        cars.removeLast();
        notifyObservers();
    }

    public void updateCarPositions() {
        for (Car car : cars) {
            car.move();
        }
        notifyObservers();
    }

    public void onBoundaryCollision(Car car) {
        int x = (int) Math.round(car.getX());
        if (x < 0 || x > CarView.width - 100) {
            car.turnLeft();
            car.turnLeft();
            notifyObservers();
        }
    }

    public void gas(int amount) {
        cars.forEach(car -> car.gas(amount / 100.0));
        notifyObservers();
    }

    public void brake(int amount) {
        cars.forEach(car -> car.brake(amount / 100.0));
        notifyObservers();
    }

    public void setTurboOnForSaab() {
        cars.stream()
                .filter(car -> car instanceof Saab95)
                .forEach(car -> ((Saab95) car).setTurboOn());
        notifyObservers();
    }

    public void setTurboOffForSaab() {
        cars.stream()
                .filter(car -> car instanceof Saab95)
                .forEach(car -> ((Saab95) car).setTurboOff());
        notifyObservers();
    }

    public void raiseScaniaBed() {
        cars.stream()
                .filter(car -> car instanceof Scania)
                .forEach(car -> ((Scania) car).raisePlatform(70));
        notifyObservers();
    }

    public void lowerScaniaBed() {
        cars.stream()
                .filter(car -> car instanceof Scania)
                .forEach(car -> ((Scania) car).lowerPlatform(70));
        notifyObservers();
    }

    public void startAllCars() {
        cars.forEach(Car::startEngine);
        notifyObservers();
    }

    public void stopAllCars() {
        cars.forEach(Car::stopEngine);
        notifyObservers();
    }

    public void addCarToPanel() {
        if (getCars().size() < MAX_CARS) {
            Car newCar = generateRandomCar();
            newCar.setY(100 + (getCars().size() * 100));
            addCar(newCar);
        }
    }

    public void removeCarFromPanel() {
        if (!cars.isEmpty()) {
            removeCar(); // Tar bort senaste bilen
        }
    }
    
    private Car generateRandomCar() {
        return CarFactory.createCar(CarFactory.CarType.getRandomType());
    }

    public List<Car> getCars() {
        return cars;
    }

    public ArrayList<Drawable> getDrawables() {
        ArrayList<Drawable> drawables = new ArrayList<>(cars);
        drawables.add(volvoVerkstad);
        return drawables;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
