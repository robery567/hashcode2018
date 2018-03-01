package ro.adlabs;

public class Utility {

    public static int distance(Point start, Point stop) {
        return Math.abs(start.getY() - stop.getY()) + Math.abs(start.getX() - stop.getX());
    }

}
