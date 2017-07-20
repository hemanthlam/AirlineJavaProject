package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J airline Project
 * Here the program takes arguments from the command line
 * -README gives the detailed description about the project that can be used in command line
 * After entering the flight details, we can use -print to get the details clearly.
 */
public class Project2 {

  public static void main(String[] args) throws ParseException {
























    //The required variables are declared here.
    int flightNumber;
    String name;
    String src;
    String dest;
    String arriveTime;
    String departTime;
//Object names are created
    Flight flight;
    Airline airline;

    if(args.length == 0) //Check if there are no arguments given
      System.err.println("Missing command line arguments");
// Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
//Try and catch blocks are used to catch unexpected behaviour of the program
    try {
//Positioning of the README shouldn't effect us, so using all possible locations and it later calls README method
      if ((args.length == 1 && args[0].contains("-README")) ||
              (args.length == 9 && args[0].contains("-README")) ||
              (args.length == 10 && args[0].contains("-README")) ||
              (args.length == 9 && args[1].contains("-README")) ||
              (args.length == 10 && args[1].contains("-README")) ||
              (args.length == 9 && args[8].contains("-README")) ||
              (args.length == 10 && args[9].contains("-README")) ||
              (args.length == 10 && args[8].contains("-README")) ||
              (args.length == 9 && args[8].contains("-README"))) {
        README();
        System.exit(1);
      }
      else if (args.length < 8) { //Check if there are not enough arguments to get the complete flight details
        throw new IllegalArgumentException("Not enough args .. Please use '-README'");
      }else if (args.length > 10) { //Check if args are more in number than expected
        throw new IllegalArgumentException("Too many arguments, if you are not sure please use '-README' to understand the scenerio!");
      }
//Assigning arg values to the declared variable names.
      name = args[0];
//Check if the name contains only numbers and letters else it should show error message
      Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(args[0]);
      boolean checkName = m.find();
      if ((args.length == 9  && args[0].contains("-print")) ||
              (args.length == 1 && args[0].contains("-print"))) {
        System.out.println("-print can be used at the end after Flight details, please use -README command to understand");
        System.exit(1);
      }
      else if(checkName){
        System.out.println("Please try again .. The Name should contain only numbers and letters");
        System.exit(1);
      }
      flightNumber = Integer.parseInt(args[1]);
      src = args[2];
//Check to see the Airport codes should be only 3 letters in both Source and Destination
      int flag = 0;
      for (int i = 0; i < src.length(); i++) {
        if (Character.isLetter(src.charAt(i)))
          flag++;
      }
      if (flag != 3) {
        throw new IllegalArgumentException("Source and Destination Airport codes should contain 3 letters, Please re-try!");
      }
      departTime = args[3] + " " + args[4];
      dest = args[5];
      flag = 0;
      for (int i = 0; i < dest.length(); i++) {
        if (Character.isLetter(dest.charAt(i)))
          flag++;
      }
      if (flag != 3) {
        throw new IllegalArgumentException("Source and Destination Airport codes should contain 3 letters, Please re-try!");
      }
      arriveTime = args[6] + " " + args[7];
// Check if the given date and Time are valid and as expected in the format mm/dd/yyyy hh:mm using checkValidDate method
      checkValidDate(departTime);
      checkValidDate(arriveTime);
//Objects for both the classes, Flight and Airline will be used and default values will be assigned.
      flight = new Flight(flightNumber, src, departTime, dest, arriveTime);
      airline = new Airline(name, flight);
//Checks if -print is given in args to print the Flight details
      if ((args.length == 9 && args[8].contains("-print"))) {
        System.out.println("Flight Travel Details Printed: " + airline.getName() + " Airlines - " + airline.getFlights());
        System.exit(1);
      }
//If Print and README are not given, to just print the number of flights and airline details the print statements are used
      System.out.println(airline);
      System.out.println(flight);
      System.exit(1);
    }
    catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      System.exit(1);
    }
    catch(Exception e){
      System.out.println("An exception have occurred: " + e);
    }
  }
//Implemented the README method
  private static void README() {
    System.out.println("\nREADME FILE for PROJECT 1 by HEMANTH LAM\n" +
            "This project will create a fundamental Airline and Flight classes\n" +
            "args are (in this order):\n" +
            "name The name of the airline\n" + "flightNumber The flight number\n" +
            "src Three-letter code of departure airport\n" +
            "departTime Departure date and time (24-hour time)\n" +
            "dest Three-letter code of arrival airport\n" +
            "arriveTime Arrival date and time (24-hour time)\n" +
            "options are (options may appear in any order): \n" +
            "-print Prints a description of the new flight \n" +
            "-README Prints a README for this project and exits\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n" +
            "Please follow the instructions!!\n \n" +
            "-print needs to be given only After entering the flight details");
  }

//Implemented the checkValidDate Method
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