package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J airline Project2
 * Here the program takes arguments from the command line
 * -README gives the detailed description about the project that can be used in command line
 * After entering the flight details, we can use -print to get the details clearly.
 * -textFile File name to store the flight details
 */
public class Project2 {
  public static void main(String[] args) {
    int flightNumber;
    String name;
    String src;
    String dest;
    String arriveTime;
    String departTime;
    String fileName;
    AbstractAirline<Flight> airline = null;
    AbstractAirline airline1;
    Flight flight1;
    TextDumper textDumper = new TextDumper();
    TextParser textParser = new TextParser();

    boolean boo = false;
    try {
      for (String arg : args) {
        if (arg.contains("-README")) {
          boo = true;
        }
      }

      if (boo == true)
      {
        System.out.println("\nREADME FILE for PROJECT 1 by HEMANTH LAM\n" +
                "This project will create boo fundamental Airline and Flight classes\n" +
                "args are (in this order):\n" +
                "name The name of the airline\n" + "flightNumber The flight number\n" +
                "src Three-letter code of departure airport\n" +
                "departTime Departure date and time (24-hour time)\n" +
                "dest Three-letter code of arrival airport\n" +
                "arriveTime Arrival date and time (24-hour time)\n" +
                "options are (options may appear in any order): \n" +
                "-print Prints boo description of the new flight \n" +
                "-README Prints boo README for this project and exits\n" +
                "Date and time should be in the format: mm/dd/yyyy hh:mm\n" +
                "Please follow the instructions!!\n \n" +
                "-print needs to be given only After entering the flight details");
        System.exit(1);
      } else {
        if ((args.length == 11) && (args[0].contentEquals("-textFile")) && !(args[1].startsWith("-")) && (args[2].contentEquals("-print")))
        {
          fileName = args[1];
          if(!args[6].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)")||!args[9].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");
          if(!args[7].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")||!args[10].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");


          Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(args[3]);
          boolean b = m.find();
          if (b) {
            System.out.println("Please try again .. The Name should contain only numbers and letters");
          }

          int flag=0;
          for (int i = 0; i < args[5].length(); i++)
          {
            if (Character.isLetter(args[5].charAt(i)))
              flag++;
          }
          if (flag!=3)
            System.out.println("Source Airport codes should contain 3 letters, Please re-try!");

          flag=0;
          for (int i = 0; i < args[8].length(); i++)
          {
            if (Character.isLetter(args[8].charAt(i)))
              flag++;
          }
          if (flag!=3)
            System.out.println("Destination Airport codes should contain 3 letters, Please re-try!");

          if (true) {
            try {
              File file = new File(fileName);
              if (file.exists()) {
                System.out.println("File already exists!!");
                name = args[3];
                textParser.getFileName(fileName, name);
                airline = textParser.parse();
                flight1 = new Flight(Integer.parseInt(args[4]), args[5], args[6] + " " + args[7], args[8] ,args[9] + " " + args[10]);
                airline.addFlight(flight1);
                textDumper.getFileName(fileName);
                textDumper.dump(airline);
                System.out.println(flight1);
              } else {
                file.createNewFile();
                System.out.println("Created a new file");
                airline = new Airline(args[3]);
                //name = args[3];
                flight1 = new Flight(Integer.parseInt(args[4]), args[5], args[6] + " " + args[7], args[8] ,args[9] + " " + args[10]);
                airline.addFlight(flight1);
                System.out.println(airline);
                textDumper.getFileName(fileName);
                textDumper.dump(airline);
                System.out.println(flight1);
              }
            } catch (FileNotFoundException filename) {
              System.out.println("File not found");
            } catch (ParserException e) {
              System.out.println("Parsing Exception have occurred");
              e.printStackTrace();
            } catch (IOException e) {
              System.out.println("IO Exception is occurred");
              e.printStackTrace();
            }
          }
        }

        if ((args.length == 11) && (args[0].contentEquals("-print") && (args[1].contentEquals("-textFile")) && !(args[2].startsWith("-"))))
        {
          fileName = args[2];
          //to check the format of date and time
          if(!args[6].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)")||!args[9].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");
          if(!args[7].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")||!args[10].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");

          Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(args[3]);
          boolean b = m.find();
          if (b) {
            System.out.println("Please try again .. The Name should contain only numbers and letters");
          }


          int flag1=0;
          for (int i = 0; i < args[5].length(); i++)
          {
            if (Character.isLetter(args[5].charAt(i)))
              flag1++;
          }
          if (flag1!=3)
            System.out.println("Source Airport codes should contain 3 letters, Please re-try!");

          //to check number of letters in the name of the destination airport 
          flag1=0;
          for (int i = 0; i < args[8].length(); i++)
          {
            if (Character.isLetter(args[8].charAt(i)))
              flag1++;
          }
          if (flag1!=3)
            System.out.println("Destination Airport codes should contain 3 letters, Please re-try!");

          if (true) {
            try {
              File file = new File(fileName);

              if (file.exists()) {
                System.out.println("File exists!!");
                name = args[3];
                textParser.getFileName(fileName, name);
                airline = textParser.parse();
                flight1 = new Flight(Integer.parseInt(args[4]), args[5], args[6] + " " + args[7], args[8] ,args[9] + " " + args[10]);
                airline.addFlight(flight1);
                textDumper.getFileName(fileName);
                textDumper.dump(airline);
                System.out.println(flight1);

              } else {
                file.createNewFile();
                System.out.println("Created a new file");
                airline = new Airline(args[3]);
                //name = args[3];
                flight1 = new Flight(Integer.parseInt(args[4]), args[5], args[6] + " " + args[7], args[8] ,args[9] + " " + args[10]);
                airline.addFlight(flight1);
                System.out.println(airline);
                textDumper.getFileName(fileName);
                textDumper.dump(airline);
                System.out.println(flight1);
              }
            } catch (FileNotFoundException filename) {
              System.out.println("File not found");
            } catch (ParserException e) {
              System.out.println("Parsing Exception have occurred");
              e.printStackTrace();
            } catch (IOException e) {
              System.out.println("IO Exception is occurred");
              e.printStackTrace();
            }
          }
        }

        else if((args.length == 10)  && (args[0].contentEquals("-textFile")) && (!(args[1].startsWith("-")))) {
          fileName = args[1];

          if (!args[5].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)") || !args[8].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");
          if (!args[6].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]") || !args[9].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");

          //to check if airline name consists of any special characters
          Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(args[2]);
          boolean b = m.find();
          if (b) {
            System.out.println("Please try again .. The Name should contain only numbers and letters");
          }

          //to check number of letters in the name of the source airport 
          int flag3 = 0;
          for (int i = 0; i < args[4].length(); i++) {
            if (Character.isLetter(args[4].charAt(i)))
              flag3++;
          }
          if (flag3 != 3)
            System.out.println("Source Airport codes should contain 3 letters, Please re-try!");

          //to check number of letters in the name of the destination airport 
          flag3 = 0;
          for (int i = 0; i < args[7].length(); i++) {
            if (Character.isLetter(args[7].charAt(i)))
              flag3++;
          }
          if (flag3 != 3)
            System.out.println("Destination Airport codes should contain 3 letters, Please re-try!");

          if (true) {
            try {
              File file = new File(fileName);

              if (file.exists()) {
                System.out.println("File exits!!");
                name = args[2];
                textParser.getFileName(fileName, name);
                airline = textParser.parse();
                flight1 = new Flight(Integer.parseInt(args[3]), args[4], args[5] + " " + args[6], args[7], args[8] + " " + args[9]);
                airline.addFlight(flight1);
                textDumper.getFileName(fileName);
                textDumper.dump(airline);

              } else {
                file.createNewFile();
                System.out.println("Created a new file");
                airline = new Airline(args[2]);
                airline.addFlight(new Flight(Integer.parseInt(args[3]), args[4], args[5] + " " + args[6], args[7], args[8] + " " + args[9]));
                textDumper.getFileName(fileName);
                textDumper.dump(airline);
              }
            } catch (FileNotFoundException filename) {
              System.out.println("file not found");
            } catch (ParserException e) {
              System.out.println("Parsing Exception have occurred");
              e.printStackTrace();
            } catch (IOException e) {
              System.out.println("IO Exception is occurred");
              e.printStackTrace();
            }
          }
        }

        else if((args.length == 9)  && (args[0].equals("-print"))){

          //to check the format of date and time
          if(!args[4].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)")||!args[7].matches("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");
          if(!args[5].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")||!args[8].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
            throw new IllegalArgumentException("Please enter date and time in valid format...");

          //to check if airline name consists of any special characters
          Pattern p = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(args[1]);
          boolean b = m.find();
          if (b) {
            System.out.println("Please try again .. The Name should contain only numbers and letters");
          }

          //to check number of letters in the name of the source airport 
          int counter1=0;
          for (int i = 0; i < args[3].length(); i++)
          {
            if (Character.isLetter(args[3].charAt(i)))
              counter1++;
          }
          if (counter1!=3)
            System.out.println("Source Airport codes should contain 3 letters, Please re-try!");

          //to check number of letters in the name of the destination airport 
          int counter2=0;
          for (int i = 0; i < args[6].length(); i++)
          {
            if (Character.isLetter(args[6].charAt(i)))
              counter2++;
          }
          if (counter2!=3)
            System.out.println("Destination Airport codes should contain 3 letters, Please re-try!");

          if (true) {
            airline = new Airline(args[1]);
            flight1 = new Flight(Integer.parseInt(args[2]), args[3], args[4] + " " + args[5], args[6] ,args[7] + " " + args[8]);
            airline.addFlight(flight1);
            if (args[0].contentEquals("-print")) {
              System.out.println("Airline name : " + airline.getName());
              System.out.println(flight1.toString());
            }
          }
        }

        else if(args.length==8 ){
          for (String arg : args) {
            System.out.println(arg);
          }
          System.out.println("No options are given.");
        }

        else if(args.length<8)
        {
          System.out.println("Not enough args... Please see -README and try again!!");
        }
      }
    }  catch (IllegalArgumentException e) {
      System.out.println("Invalid Arguments\n" + e.getMessage());
      System.exit(1);
    }
    System.exit(1);
  }

}