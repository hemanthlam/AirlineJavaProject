package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Class TextParser that implements the edu.pdx.cs410J.AirlineParser interface.
 * A TextParser reads the contents of a text file,
 * from it creates an airline with its associated flights.*/

public class TextParser implements AirlineParser {
    private String filename;
    private String name;
    public Airline airline;

    /** parse method is acquired from the AirlineParser interface and is implemented here */
    @Override
    public AbstractAirline parse() throws ParserException {
        System.out.println("Adding Flights to the File after reading");
        try {
            File file= new File(filename);
            FileReader fr = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String intermediate;
            if(file.exists()) {
                String name = bufferedReader.readLine();
                airline = new Airline(name);

                if (name.equalsIgnoreCase(name)) {
                    while ((intermediate = bufferedReader.readLine()) != null)
                    {
                        String[] split = intermediate.split(",");
                        String number = split[0];
                        String src = split[1];
                        String dept = split[2];
                        String[] dsplit = dept.split(" ");
                        String dest = split[3];
                        String arrive = split[4];
                        String[] asplit = arrive.split(" ");

                        /**Date and Time should be in the format: mm/dd/yyyy hh:mm*/
                        if(!dsplit[0].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)")||
                                !asplit[0].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"))
                            throw new IllegalArgumentException("Please enter date and time in valid format...");
                        if(!dsplit[1].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")||
                                !asplit[1].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
                            throw new IllegalArgumentException("Please enter date and time in valid format...");
                        /** Airline Name should be only Letters or numbers*/
                        Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(name);
                        boolean b = m.find();
                        if (b) {
                            System.out.println("Please try again .. The Name should contain only numbers and letters");
                        }

                        /**Source and Destination Airport names should be only 3 letters */
                        int flag=0;
                        for (int i = 0; i < src.length(); i++)
                        {
                            if (Character.isLetter(src.charAt(i)))
                                flag++;
                        }
                        if (flag!=3)
                            System.out.println("Source Airport codes should contain 3 letters, Please re-try!");

                        flag=0;
                        for (int i = 0; i < dest.length(); i++)
                        {
                            if (Character.isLetter(dest.charAt(i)))
                                flag++;
                        }
                        if (flag!=3)
                            System.out.println("Destination Airport codes should contain 3 letters, Please re-try!");
                        Flight f = new Flight(Integer.parseInt(number), src, dept, dest, arrive);
                        airline.addFlight(f);
                    }
                    bufferedReader.close();
                }else {
                    System.out.println(filename + "doesn't match");
                    System.exit(1);
                }
            }else
            {
                System.out.println("File doesn't exist");
            }
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch(IOException ex) {
            System.out.println("File must be corrupted");
        } catch (Exception e) {
            System.out.println("Unexpected error." + " Please see -README to understand");
        }
        return airline;
    }

    /**
     Getter method for the filename is created which takes filename and name
     */
    void getFileName(String s, String name) {
        this.filename = s;
        this.name = name;
    }
}
