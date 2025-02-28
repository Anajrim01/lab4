package model;

import model.interfaces.Drawable;

public class Verkstad<T extends Car> implements Drawable {
    private final Loadable<T> loadable;
    private double x = 300;
    private double y = 300;

    public Verkstad(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must be a positive integer");
        loadable = new Loadable<>(null, capacity);
    }

    public void loadCar(T car) {
        loadable.loadCar(car);
    }

    public T getLastCar() {
        return loadable.unloadCar();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double _x) {
        x = _x;
    }

    @Override
    public void setY(double _y) {
        y = _y;
    }
}
