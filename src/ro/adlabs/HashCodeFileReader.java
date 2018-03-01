package ro.adlabs;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HashCodeFileReader {

    private File theFile;

    public HashCodeFileReader(@NotNull File file) {
        theFile = file;
    }

    public City readIt() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream(theFile));

        String firstLine = scanner.nextLine();
        String[] data = firstLine.split(" ");

        if(data.length != 6)
            throw new InvalidRowFormat();

        int rows = Integer.valueOf(data[0]);
        int cols = Integer.valueOf(data[1]);
        int vehiclesNumber = Integer.valueOf(data[2]);
        int ridesNumber = Integer.valueOf(data[3]);
        int bonusPerRide = Integer.valueOf(data[4]);
        int numberOfSteps = Integer.valueOf(data[5]);

        List<Ride> ridesList= new ArrayList<>(ridesNumber);
        for(int i = 0; i < ridesNumber; i++) {
            String line = scanner.nextLine();
            data = line.split(" ");

            if (data.length != 6)
                throw new InvalidRowFormat();

            int a = Integer.valueOf(data[0]);
            int b = Integer.valueOf(data[1]);
            int x = Integer.valueOf(data[2]);
            int y = Integer.valueOf(data[3]);
            int s = Integer.valueOf(data[4]);
            int f = Integer.valueOf(data[5]);

            Point start = new Point(a, b);
            Point finish = new Point(x, y);

            ridesList.add(new Ride(i, start, finish, s, f));
        }

        return new City(rows, cols, vehiclesNumber, ridesList, bonusPerRide, numberOfSteps);
    }


}
