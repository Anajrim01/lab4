package model.interfaces;

public interface TransportCar {
    void raisePlatform(double degree);

    void lowerPlatform(double degree);

    double getCurrentAngle();
}
