package flight;

import java.rmi.*;
public interface Flight extends Remote{
  //读取航班号
  public String getFlightNumber()throws RemoteException;
  //读取始发站
  public String getOrigin()throws RemoteException;
  //读取终点站
  public String getDestination()throws RemoteException;
  //读取计划出发时间
  public String getSkdDeparture()throws RemoteException;
  //读取计划到达时间
  public String getSkdArrival()throws RemoteException;

  public void setOrigin(String origin)throws RemoteException;
  public void setDestination(String destination)throws RemoteException;
  public void setSkdDeparture(String skdDeparture)throws RemoteException;
  public void setSkdArrival(String skdArrival)throws RemoteException; 
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
