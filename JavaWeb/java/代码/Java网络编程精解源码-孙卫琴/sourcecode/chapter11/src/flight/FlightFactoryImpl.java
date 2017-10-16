package flight;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class FlightFactoryImpl extends UnicastRemoteObject
                                     implements FlightFactory{
    protected Hashtable<String,Flight> flights; //存放Flight对象的缓存

    public FlightFactoryImpl()throws RemoteException{
        flights = new Hashtable<String,Flight>();
    }

    public Flight getFlight(String flightNumber)throws RemoteException{
      Flight flight = flights.get(flightNumber);
      if (flight != null) return flight;

      flight = new FlightImpl(flightNumber, null, null, null, null);
      flights.put(flightNumber, flight);
      return flight;
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
