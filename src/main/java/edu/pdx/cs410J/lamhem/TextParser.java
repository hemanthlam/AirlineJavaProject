package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.StringTokenizer;

//Class TextParser that implements the edu.pdx.cs410J.AirlineParser interface. A TextParser
  //      reads the contents of a text file and from it creates an airline with its associated flights

public class TextParser implements AirlineParser<Airline> {
    File file;

    @Override
    public Airline parse() throws ParserException {
        String[] flightdetails;
        int i=0;
        Airline airline = new Airline();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringTokenizer st = new StringTokenizer(br.readLine(),"  ");
            while (st.hasMoreTokens()) {
                flightdetails[i] = st.nextToken();
                i++;
            }

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                airline.addFlight(new Flight(Integer.parseInt(line)));
            }

        } catch (IOException ex) {
            throw new ParserException("While parsing text", ex);
        }

        return airline;
    }

}
