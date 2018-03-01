package ro.adlabs;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private Point location;

    private boolean busy;

    private Ride assignedRide;

    private Point whereTo;

    private int distanceToWhere;

    private List<Ride> completedRides;

    public Vehicle() {
        location = new Point(0, 0);
        busy = false;

        completedRides = new ArrayList<>();
    }

    public Point getLocation() {
        return location;
    }

    public Vehicle setLocation(Point location) {
        this.location = location;
        return this;
    }

    public boolean isBusy() {
        return busy;
    }

    public Vehicle setBusy(boolean busy) {
        this.busy = busy;
        return this;
    }

    public Ride getAssignedRide() {
        return assignedRide;
    }

    public Vehicle setAssignedRide(Ride assignedRide) {
        if(assignedRide == null) return null;

        this.assignedRide = assignedRide;
        whereTo = location.equals(assignedRide.getStart()) ? assignedRide.getFinish() : assignedRide.getStart();
        distanceToWhere = Utility.distance(location, whereTo);

        setBusy(true);

        return this;
    }

    public List<Ride> getCompletedRides() {
        return completedRides;
    }

    public Ride findNearestRide(List<Ride> rides, int currentStep, int totalSteps) {
        if(rides == null || rides.size() == 0 || busy) return null;

        Ride nearest = findFirstFreeRide(rides);
        if(nearest == null) return null;

        int min = Utility.distance(location, nearest.getStart());
        for(Ride ride : rides) {
            int distance = Utility.distance(location, ride.getStart());

            if(ride.isAssigned() || !hasTime(distance, currentStep, totalSteps)) continue;

            if(distance == ride.getEarliest()) {
                return ride;
            }

            if(distance < min) {
                nearest = ride;
                min = distance;
            }
        }

        return nearest;
    }

    private Ride findFirstFreeRide(List<Ride> rides) {
        for(Ride r : rides) {
            if(!r.isAssigned()) {
                return r;
            }
        }

        return null;
    }

    private boolean hasTime(int distance, int currentStep, int totalSteps) {
        return totalSteps - currentStep > distance;
    }

    public void move() {
        if(assignedRide == null) return;

        if(distanceToWhere > 0) {
            distanceToWhere--;
        } else {
            if(whereTo != null) { //no more rides to assign
                setLocation(whereTo);

                if (location.equals(assignedRide.getStart())) {
                    whereTo = assignedRide.getFinish();
                } else if (location.equals(assignedRide.getFinish())) {
                    setBusy(false);

                    completedRides.add(assignedRide);
                    whereTo = null;
                }
            }
        }
    }
}
