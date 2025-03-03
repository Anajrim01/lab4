import controller.CarController;
import model.*;
import view.CarView;

public class Startup {
    public static void main(String[] args) {
        CarModel model = createModel();
        CarView view = new CarView("CarSim 1.1", model);
        model.addObserver(view); // Add the view as an observer to the model

        new CarController(model, view);
    }

    public static CarModel createModel(){
        CarModel model = new CarModel();

        Car saab = CarFactory.createCar("Saab95");
        saab.setY(100);
        Car scania = CarFactory.createCar("Scania");
        scania.setY(200);
        Car volvo = CarFactory.createCar("Volvo240");
        volvo.setY(300);

        model.addCar(saab);
        model.addCar(volvo);
        model.addCar(scania);

        return model;
    }
}