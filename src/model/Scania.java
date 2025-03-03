package model;

import model.interfaces.Transportable;

import java.awt.*;

public class Scania extends Car implements Transportable {
    private final Loadable<Car> loadable = new Loadable<>(this, 0);

    public Scania() {
        super(2, Color.white, 600, "Scania");
    }

    @Override
    public double getCurrentAngle() {
        return loadable.getAngle();
    }

    @Override
    public void raisePlatform(double deg) {
        loadable.raise(deg);
    }

    @Override
    public void lowerPlatform(double deg) {
        loadable.lower(deg);
    }

    @Override
    public void gas(double amount) {
        if (loadable.getAngle() > 0)
            throw new IllegalStateException("Transport can't move while ramp is up.");
        super.gas(amount);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
