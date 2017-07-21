package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**Class TextDumper that implements the edu.pdx.cs410J.AirlineDumper interface.
 * A TextDumper dumps the contents of an airline
 * (including flights) to a text file.*/

public class TextDumper implements AirlineDumper {
    private String filename;

    /** dump method is acquired from the AirlineDumper interface and is implemented here */
    @Override
    public void dump(AbstractAirline abstractAirline) throws IOException {

        File file = new File(filename);
        FileWriter fw =new FileWriter(filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            fw.write(abstractAirline.getName());

            Iterator i = abstractAirline.getFlights().iterator();
            while (i.hasNext()){
                Flight flight = (Flight)i.next();
                fw.write("\n");
                fw.write(flight.flightNumber+","+flight.src+","+flight.departTime+","+flight.dest+","+flight.arriveTime);
            }
            fw.write("\n");
            fw.flush();
            fw.close();

        } catch (IOException x) {
            System.err.println(x.getMessage());
            System.err.println("The file name needs to be given");
            System.exit(1);
        }
    }

    /**
     Getter method for the filename is created
     */
    public void getFileName(String s){
        this.filename = s;
    }

}
