package edu.pdx.cs410J.lamhem;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hemanth on 7/12/2017.
 * This is a Airline class where 'Name of the Airline' and 'List of Flights' available is given
 */
public class Airline extends AbstractAirline {
    String name;
    ArrayList<AbstractFlight> flightList;
Airline(String name){
    this.name = name;
}

    Airline(String name, Flight flight){ // Constructor has been declared
        this.name=name;
        flightList= new ArrayList<>();
        addFlight(flight);
    }
//Getters methods are defined below including the Abstract methods extracted from AbstractAirline Class.
    @Override
    public String getName() { //name is returned to the toString
        //return null;
        return name;
    }

    @Override
    public void addFlight(AbstractFlight abstractFlight) { //Given flight is added
        flightList.add(abstractFlight);
    }

    @Override
    public Collection getFlights() { //All available flights are displayed, Here we consider only 1 flight for now.
        //return null;
        return flightList;
    }
}
