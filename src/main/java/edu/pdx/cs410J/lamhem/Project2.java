package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.*;
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

    // Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    //System.err.println("Missing command line arguments");
    Airline airline;
    Flight flight;
    List<String> argList = new ArrayList(); // arglist stores appointment details entered through command line
    TextDumper textDumper = new TextDumper();
    TextParser textParser = new TextParser();
    String fileName = null;
    boolean printFlag = false;


    for (String arg : args) argList.add(arg);

    if (argList.contains("-README")) {
      System.out.println("\nREADME FILE - PROJECT 2 - POOJA MANE\n" +
              "This project is used to create appointment book for owner including appointment details entered through commandline.\n" +
              "and optionally creating text file for that appointmentbook\n"+
              "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n" +
              "args are (n this order):\n" +
              "owner -The person whose owns the appointment book\n" +
              "description- A description of the appointment\n" +
              "beginTime- When the appointment begins (24-hour time)\n" +
              "endTime- When the appointment ends (24-hour time)\n" +
              "options are (options may appear in any order):\n" +
              "-print Prints a description of the new appointment\n" +
              "-README Prints a README for this project and exits\n" +
              "-textFile file creates text file for appointment book\n"+
              "Date and time should be in the format: mm/dd/yyyy hh:mm\n" +
              "Description should not be empty\n" +
              "If -print and appointment details are specified it prints appointment description");
      System.exit(1);
    }
    try {
      for (int i = 0; i <= 1; i++) {
        if (argList.get(i).contentEquals("-textFile") && !(argList.get(i + 1).startsWith("-"))) {
          fileName = argList.get(i+1);
          break;
        }
      }

      if(argList.contains("-textFile") && fileName == null)
      {
        System.out.println("Invalid Arguments ..Text file name not provided.. Please try again");
        System.exit(1);
      }

      if (argList.size() == 2 && (fileName != null)){
        System.out.println("Invalid arguments .. appointment details not specified");
        System.exit(1);
      }

      if (argList.size() == 11 && (fileName != null) && ((argList.get(0).contentEquals("-print")) || (argList.get(2).contentEquals("-print")))) {
        argList.remove(0);
        argList.remove(0);
        argList.remove(0);
        printFlag = true;
      }

      if (argList.size() == 10 && (fileName != null) && !(argList.get(0).contentEquals("-print"))) {
        argList.remove(0);
        argList.remove(0);

      }
      if ((argList.size() == 9 && argList.get(0).equals("-print"))) {
        argList.remove(0);
        printFlag = true;
      }
      if ((argList.size() == 8) && !(argList.get(0).equals("-print"))) {

        //if dates are invalid format and description is not empty creates appointment book with specified appointment
        // checkValidDate(argList.get(2) + " " + argList.get(3));
        //checkValidDate(argList.get(4) + " " + argList.get(5));

        // check for description is not empty
        if ((!(argList.get(1).trim().isEmpty()))) {

          String name = argList.get(0);
          int flightNumber = Integer.parseInt(argList.get(1));
          String src = argList.get(2);
          String departTime = argList.get(3) + " " + argList.get(4);
          String dest = argList.get(5);
          String arriveTime = argList.get(6) + " " + argList.get(7);

          flight = new Flight(flightNumber, src, departTime, dest, arriveTime);
          airline = new Airline(name);
          airline.addFlight(flight);

          if (printFlag)
            System.out.println(flight);

          if(fileName==null && !(printFlag)) {
            System.out.println(airline);
            System.out.println(flight);
          }

          if (fileName != null) {

            File file = new File(fileName);
            Airline appointmentBook1;
            if(file.exists())
            {
              FileInputStream fstream = new FileInputStream(file);
              BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
              String ownerName = br.readLine();
              if ((airline.getName() != null && airline.getName().equalsIgnoreCase(ownerName))) {
                textDumper.getFileName(file);
                textParser.getFileName(file);
                appointmentBook1=textParser.parse(); //Read file and create Appointmentbook using parse()
                appointmentBook1.addFlight(flight); // Add appointment to created Appointmentbook
                textDumper.dump(appointmentBook1); //Write back Appointmentbook to file
                System.out.println("Appointmentbook has been created and added to file..");
                System.out.println(appointmentBook1);
              }
              else {
                System.out.println("Files are different ..Please try again");
                System.exit(1);
              }
            }
            else
            {
              file.createNewFile();
              textDumper.getFileName(file);
              textDumper.dump(airline);
              System.out.println("Appointmentbook has been created and added to file..");
              System.out.println(airline);
            }


          }

        } else
          throw new Exception("Empty string in description.. Please try again");
      } else
        throw new Exception(" ");
    } catch (IOException e) {
      System.out.println("Bad format of data..");
    } catch (ParseException e) {
      System.out.println("Invalid Date Formats .. Please try again");
    }catch (ParserException e)        {
      System.out.println("Error while parsing files.. Please try again");
    }catch (StringIndexOutOfBoundsException e){
      System.out.println("Data Malformed in text file,unable to parse.. Please try again");
    }
    catch (Exception e) {
      if (e.getMessage().equalsIgnoreCase("Empty string in description.. Please try again")) {
        System.out.println(e.getMessage());
      } else {
        System.out.println("Invalid arguments .. Please try again");
      }
    }
    System.exit(1);
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