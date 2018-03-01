package ro.adlabs;

import java.util.ArrayList;
import java.util.List;

public class City {

    private int rows;
    private int cols;
    private int vehiclesNumber;
    private int ridesNumber;
    private int bonus;
    private int steps;

    private List<Vehicle> vehicles;
    private List<Ride> rides;


    public City(int rows, int cols, int vehiclesNumber, List<Ride> rides, int bonus, int steps) {
        this.rows = rows;
        this.cols = cols;
        this.vehiclesNumber = vehiclesNumber;
        this.bonus = bonus;
        this.steps = steps;

        this.rides = rides;

        init();
    }

    private void init() {
        vehicles = new ArrayList<>(vehiclesNumber);
        for (int i = 0; i < vehiclesNumber; i++) {
            vehicles.add(new Vehicle());
        }
    }

    public void doRides() {
//        rides.sort(null);

        checkCars(-1);
        for (int i = 0; i < steps; i++) {
            System.out.printf("Processing step %dfrom %d%n", i, steps);
            moveCars();

            checkCars(i);
        }
    }

    private void checkCars(int currentStep) {
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isBusy()) {
                Ride optimumRide = vehicle.findNearestRide(rides, currentStep, steps);

                if(optimumRide != null) {
                    vehicle.setAssignedRide(optimumRide);
                    optimumRide.setAssigned(true);
                }
            }


        }
    }

    private void moveCars() {
        for (Vehicle vehicle : vehicles) {


            vehicle.move();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getRidesNumber() {
        return ridesNumber;
    }

    public int getBonus() {
        return bonus;
    }

    public int getSteps() {
        return steps;
    }
}
