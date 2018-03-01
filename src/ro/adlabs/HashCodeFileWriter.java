package ro.adlabs;

import java.io.*;
import java.util.List;

public class HashCodeFileWriter {

    private BufferedWriter writer;

    private List<Vehicle> vehicles;


    public HashCodeFileWriter(File file, List<Vehicle> vehicleList) throws FileNotFoundException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        vehicles = vehicleList;
    }

    public void writeIt() throws IOException {

        for(Vehicle vehicle : vehicles) {
            List<Ride> completedRides = vehicle.getCompletedRides();

            StringBuilder builder = new StringBuilder();
            builder.append(completedRides.size());
            for(Ride ride : completedRides) {
                builder.append(String.format(" %d", ride.getId()));
            }

            String line = builder.toString();
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }
}
