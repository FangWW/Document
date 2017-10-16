package flight;

import java.rmi.*;
import java.rmi.server.*;

public class FlightImpl extends UnicastRemoteObject implements Flight{
  protected String flightNumber;
  protected String origin;
  protected String destination;
  protected String skdDeparture;
  protected String skdArrival;

  public FlightImpl(String aFlightNumber, String anOrigin,
          String aDestination, String aSkdDeparture, String aSkdArrival)
          throws RemoteException{
    flightNumber = aFlightNumber;
    origin = anOrigin;
    destination = aDestination;
    skdDeparture = aSkdDeparture;
    skdArrival = aSkdArrival;
  }
  public String getFlightNumber()throws RemoteException{
    System.out.println("调用getFilghtNumber()，返回"+flightNumber);
    return flightNumber;
  }

  public String getOrigin()throws RemoteException{
    return origin;
  }

  public String getDestination()throws RemoteException{
    return destination;
  }

  public String getSkdDeparture()throws RemoteException{
    return skdDeparture;
  }

  public String getSkdArrival()throws RemoteException{
    return skdArrival;
  }

  public void setOrigin(String origin)throws RemoteException{
    this.origin=origin;
  }

  public void setDestination(String destination)throws RemoteException{
    this.destination=destination;
  }
  
  public void setSkdDeparture(String skdDeparture)throws RemoteException{
    this.skdDeparture=skdDeparture;
  }

  public void setSkdArrival(String skdArrival)throws RemoteException{
    this.skdArrival=skdArrival;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
