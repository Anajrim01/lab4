package controller;

import model.CarModel;
import view.CarView;
import javax.swing.Timer;

public class CarController {
    private final CarModel carModel;
    private final CarView carView;
    public final Timer timer = new Timer(50, e -> updateCars());

    public CarController(CarModel model, CarView view) {
        this.carModel = model;
        this.carView = view;

        this.addEventListeners();
        timer.start();
    }

    private void addEventListeners() {
        // Gas/Brake buttons
        carView.addGasListener(e -> carModel.gas(carView.getGasAmount()));
        carView.addBrakeListener(e -> carModel.brake(carView.getGasAmount()));

        // Saab Turbo controls
        carView.addTurboOnListener(e -> carModel.setTurboOnForSaab());
        carView.addTurboOffListener(e -> carModel.setTurboOffForSaab());

        // Scania Bed controls
        carView.addLiftBedListener(e -> carModel.raiseScaniaBed());
        carView.addLowerBedListener(e -> carModel.lowerScaniaBed());

        // Start/Stop engine buttons
        carView.addStartListener(e -> carModel.startAllCars());
        carView.addStopListener(e -> carModel.stopAllCars());

        // Add/Remove car buttons
        carView.addAddCarListener(e -> carModel.addCarToPanel());
        carView.addRemoveListener(e -> carModel.removeCarFromPanel());
    }

    private void updateCars() {
        carModel.updateCarPositions();
        carModel.getCars().forEach(carModel::onBoundaryCollision);
        carModel.getCars().forEach(carModel::checkCollisionWithVolvoVerkstad);
        // carView.updateDisplay(); using Observer pattern instead
    }
}