package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class TextParser that implements the edu.pdx.cs410J.AirlineParser interface. A TextParser
  //      reads the contents of a text file and from it creates an airline with its associated flights

public class TextParser implements AirlineParser<Airline> {
    File file;

    @Override
    public Airline parse() throws ParserException {
        String[] flightdetails = new String[8];
        int i = 0;
        Airline airline = null;
        Flight flight;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringTokenizer st = new StringTokenizer(br.readLine(), ",  ");
            while (st.hasMoreTokens()) {
                flightdetails[i] = st.nextToken();
                i++;
            }
            String name = flightdetails[0];
            //Check if the name contains only numbers and letters else it should show error message
            Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(flightdetails[0]);
            boolean checkName = m.find();
            if(checkName){
                System.out.println("Please try again .. The Name should contain only numbers and letters");
                System.exit(1);
            }
            int flightNumber = Integer.parseInt(flightdetails[1]);
            String src = flightdetails[2];
            //Check to see the Airport codes should be only 3 letters in both Source and Destination
            int flag = 0;
            for (int j = 0; j < src.length(); j++) {
                if (Character.isLetter(src.charAt(j)))
                    flag++;
            }
            if (flag != 3) {
                throw new IllegalArgumentException("Source and Destination Airport codes should contain 3 letters, Please re-try!");
            }
            String departTime = flightdetails[3] + " " + flightdetails[4];
            String dest = flightdetails[5];
            flag = 0;
            for (int j = 0; j < dest.length(); j++) {
                if (Character.isLetter(dest.charAt(j)))
                    flag++;
            }
            if (flag != 3) {
                throw new IllegalArgumentException("Source and Destination Airport codes should contain 3 letters, Please re-try!");
            }
            String arriveTime = flightdetails[6] + " " + flightdetails[7];
            checkValidDate(departTime);
            checkValidDate(arriveTime);
            flight = new Flight(flightNumber, src, departTime, dest, arriveTime);
            airline = new Airline(name, flight);
            System.out.println(airline);
            System.out.println(flight);

        } catch (IOException ex) {
            throw new ParserException("While parsing text", ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return airline;
    }

    void getFileName(File file) throws IOException {
        this.file=file;
    }

    private static void checkValidDate(String parsedate) throws ParseException {
        try {
            if (parsedate == null || !(parsedate.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}$"))) {
                System.out.println("Please enter date and time in valid format...");
                System.exit(1);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm"); //Validate Date time format
            dateFormat.setLenient(false);
            dateFormat.parse(parsedate);
        }
        catch (ParseException e) {
            System.out.println("Invalid Date or Time Formats, Please try again");
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("An exception have occured, please use -README command to understand");
            System.exit(1);
        }
    }
}
