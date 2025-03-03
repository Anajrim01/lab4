import controller.CarController;
import model.*;
import view.CarView;
import view.DrawPanel;

public class Startup {
    public static void main(String[] args) {
        CarModel model = createModel();

        DrawPanel drawPanel = new DrawPanel(model);
        CarView view = new CarView("CarSim 1.1", drawPanel);
        model.addObserver(view); // Add the view as an observer to the model

        CarController controller = new CarController(model, view);

        controller.timer.start();
    }

    public static CarModel createModel(){
        CarModel model = new CarModel();

        Car saab = CarFactory.createCar("Saab95");
        saab.setY(100);
        Car volvo = CarFactory.createCar("Volvo240");
        volvo.setY(300);
        Car scania = CarFactory.createCar("Scania");
        scania.setY(200);

        model.addCar(saab);
        model.addCar(volvo);
        model.addCar(scania);

        return model;
    }
}