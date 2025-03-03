# Lab 4

This project is a car simulation application implemented using the Model-View-Controller (MVC) design pattern. It allows users to interact with different types of cars, control their movement, and visualize their positions on a graphical interface.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Design Patterns](#design-patterns)
- [Setup and Running the Application](#setup-and-running-the-application)
- [Classes and Packages](#classes-and-packages)
  - [Controller](#controller)
  - [Model](#model)
  - [View](#view)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This car simulation project is developed to demonstrate the use of MVC design pattern and several well-known design patterns including Observer and Factory Method. It allows for the creation of different types of cars (e.g., Saab95, Volvo240, Scania) and provides an interactive GUI for controlling their movement.

## Project Structure

```
.
├── controller
│   └── CarController.java
├── model
│   ├── Car.java
│   ├── CarModel.java
│   ├── CarFactory.java
│   ├── Loadable.java
│   ├── Saab95.java
│   ├── Scania.java
│   ├── Volvo240.java
│   ├── Verkstad.java
│   ├── interfaces
│   │   ├── Drawable.java
│   │   ├── Movable.java
│   │   ├── TransportCar.java
|   │   ├── Observable.java
|   │   └── Observer.java
|   └── exceptions
│       ├── FullCapacityException.java
│       ├── LoadException.java
│       └── UnloadException.java
├── view
│   ├── CarView.java
│   └── DrawPanel.java
└── Startup.java

```

## Design Patterns

- **MVC (Model-View-Controller):** The project follows MVC design pattern to separate concerns:
  - **Model:** Contains the business logic and state (e.g., `CarModel`, `Car`, `Saab95`, `Scania`).
  - **View:** Represents the visual representation and UI components (e.g., `CarView`, `DrawPanel`).
  - **Controller:** Handles the user input and interaction between Model and View (e.g., `CarController`).

- **Observer Pattern:**
  - Used to notify views (e.g., `CarView`) whenever there's an update in the model (e.g., `CarModel`).

- **Factory Method Pattern:**
  - Used to instantiate different types of cars through `CarFactory`.

## Setup and Running the Application

### Prerequisites

- Java Development Kit (JDK) installed (version 8 or above)
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor (e.g., Visual Studio Code)

### Running the Application

1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Compile the classes:
   ```sh
   javac -d out src/**/*.java
   ```

3. Run the application:
   ```sh
   java -cp out Startup
   ```

Alternatively, you can open the project in your favorite IDE and run the `Startup.java` file directly.

## Classes and Packages

### Controller

- **CarController.java:** This class acts as the controller, handling user events and interactions, updating the model and indirectly updating the view through the Observer pattern.

### Model

- **Car.java:** Abstract class representing a generic car, implementing basic functionalities like moving and turning.
- **CarModel.java:** This class serves as the main model holding the state of all cars and notifying observers about state changes.
- **CarFactory.java:** Factory class to instantiate car objects (e.g., Saab95, Volvo240, Scania).
- **Loadable.java:** Represents a loadable structure which can load and unload cars with validations.
- **Saab95.java, Scania.java, Volvo240.java:** Specific car models extending the Car class with their unique behaviors.
- **Verkstad.java:** Represents a workshop (service shop) that can load and unload cars for service.
  
#### Interfaces

- **Drawable.java:** Interface for objects that can be drawn on the interface.
- **Movable.java:** Interface for objects that can move and turn.
- **TransportCar.java:** Marker interface for transport vehicles.

### View

- **CarView.java:** The main view class that implements Observer, containing UI elements for interacting with the cars.
- **DrawPanel.java:** Custom JPanel class for rendering the cars' graphical representation.

### Exceptions

- **FullCapacityException.java, LoadException.java, UnloadException.java:** Custom exceptions for handling specific errors in loadable operations.

## Contributing

Contributions are unfortunately not welcome as this is just a lab, i.e. school work.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
