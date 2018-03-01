package ro.adlabs;

public class Ride implements Comparable<Ride> {

    private int id;

    private Point start, finish;

    private int earliest, latest;

    private boolean assigned;

    private int possiblePoints;


    public Ride(int id, Point start, Point finish, int earliest, int latest) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.earliest = earliest;
        this.latest = latest;
        this.assigned = false;

        this.possiblePoints = Utility.distance(start, finish);
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public int getEarliest() {
        return earliest;
    }

    public int getLatest() {
        return latest;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @Override
    public int compareTo(Ride ride) {
        if(possiblePoints == ride.possiblePoints) return 0;
        else if(possiblePoints > ride.possiblePoints) return -1;
        return 1;
    }

    public int getId() {
        return id;
    }
}
