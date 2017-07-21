package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractFlight;

/**
 * Created by hemanth on 7/12/2017.
 * This is a Flight class where FlightNumber, Source, Depart Time, destination, Arrival Time are given
 * This in turn returns the details to Project2, the main class and can store its values.
 */
public class Flight extends AbstractFlight {
  public int flightNumber;
  public String src;
  public String dest;
  public String arriveTime;
  public String departTime;

  /**A constructor is declared for the Flight Class*/
  Flight(int flightNumber, String src, String departTime, String dest, String arriveTime){
    this.flightNumber = Integer.parseInt(String.valueOf(flightNumber));
    this.src = src;
    this.departTime = departTime;
    this.dest = dest;
    this.arriveTime = arriveTime;
  }

  /**Getters methods are defined below including the Abstract methods extracted from AbstractFlight class.*/
  @Override
  public int getNumber()
  {
    return flightNumber;
  }

  @Override
  public String getSource() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return src;
  }

  @Override
  public String getDepartureString() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return departTime;
  }

  @Override
  public String getDestination() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return dest;
  }

  @Override
  public String getArrivalString() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return arriveTime;
  }
}
