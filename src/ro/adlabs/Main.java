package ro.adlabs;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] inputs = {"a_example.in", "b_should_be_easy.in", "c_no_hurry.in", "d_metropolis.in", "e_high_bonus.in"};

        int limit = 3;
        for(int i = 0; i < limit; i++) {
            System.out.println("Processing file " + i);
            String fileIn = inputs[i];

            File in = new File(fileIn);
            File out = new File(fileIn.replace(".in", ".out"));

            HashCodeFileReader reader = new HashCodeFileReader(in);
            City theCity = reader.readIt();
            theCity.doRides();

            HashCodeFileWriter writer = new HashCodeFileWriter(out, theCity.getVehicles());
            writer.writeIt();
        }
    }
}
