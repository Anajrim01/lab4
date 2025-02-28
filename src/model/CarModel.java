package model;

import model.interfaces.Drawable;
import view.CarView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarModel {
    private final Verkstad<Volvo240> volvoVerkstad = new Verkstad<>(5);
    private final List<Car> cars = new CopyOnWriteArrayList<>(); // concurrently safe

    public void checkCollisionWithVolvoServiceShop(Car car) {
        if (isNearVolvoServiceShop(car) && car instanceof Volvo240 volvo) {
            volvoVerkstad.loadCar(volvo);
            car.stopEngine();
            cars.remove(volvo);
        }
    }

    private boolean isNearVolvoServiceShop(Car car) {
        return Math.abs(car.getX() - volvoVerkstad.getX()) < 5 && Math.abs(car.getY() - volvoVerkstad.getY()) < 5;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(){
        cars.removeLast();
    }

    public void updateCarPositions() {
        for (Car car : cars) {
            car.move();
        }
    }

    public void checkBoundaryCollision(Car car) {
        int x = (int) Math.round(car.getX());
        if (x < 0 || x > CarView.width - 100) {
            car.turnLeft();
            car.turnLeft();
        }
    }

    public void gas(int amount) {
        cars.forEach(car -> car.gas(amount / 100.0));
    }

    public void brake(int amount) {
        cars.forEach(car -> car.brake(amount / 100.0));
    }

    public void setTurboOnForSaab() {
        cars.stream()
                .filter(car -> car instanceof Saab95)
                .forEach(car -> ((Saab95) car).setTurboOn());
    }

    public void setTurboOffForSaab() {
        cars.stream()
                .filter(car -> car instanceof Saab95)
                .forEach(car -> ((Saab95) car).setTurboOff());
    }

    public void raiseScaniaBed() {
        cars.stream()
                .filter(car -> car instanceof Scania)
                .forEach(car -> ((Scania) car).raisePlatform(70));
    }

    public void lowerScaniaBed() {
        cars.stream()
                .filter(car -> car instanceof Scania)
                .forEach(car -> ((Scania) car).lowerPlatform(70));
    }

    public void startAllCars() {
        cars.forEach(Car::startEngine);
    }
    public void stopAllCars() {
        cars.forEach(Car::stopEngine);
    }

    public List<Car> getCars() {
        return cars;
    }

    public ArrayList<Drawable> getDrawables() {
        ArrayList<Drawable> drawables = new ArrayList<>(cars);
        drawables.add(volvoVerkstad);
        return drawables;
    }
}
