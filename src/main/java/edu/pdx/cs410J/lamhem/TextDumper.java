package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

//Class TextDumper that implements the edu.pdx.cs410J.AirlineDumper interface. A TextDumper
//dumps the contents of an airline3
        //(including flights) to a text file

public class TextDumper implements AirlineDumper<Airline> {

    File file;

    @Override
    public void dump(Airline airline) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        fw.write(airline.getName());
        ArrayList<Flight> list = (ArrayList<Flight>) airline.getFlights();
        for (Flight flight : list) {
            fw.write("\n");
            fw.write(flight.getNumber()+"  "+flight.getSource()+"  "+flight.getDeparture()+"  "+flight.getDestination()+"  "+flight.getDestination());
        }
        fw.flush();
        fw.close();
    }
    void getFileName(File file) throws IOException {
        this.file=file;
    }
}
