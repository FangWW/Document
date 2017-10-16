package flight2;

import java.rmi.*;

public interface FlightFactory extends Remote{
  public Flight getFlight(String flightNumber)throws RemoteException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
