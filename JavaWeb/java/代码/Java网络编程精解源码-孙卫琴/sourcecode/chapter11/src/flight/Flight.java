package flight;

public interface Flight extends Remote{
  //��ȡ�����
  public String getFlightNumber()throws RemoteException;
  //��ȡʼ��վ
  public String getOrigin()throws RemoteException;
  //��ȡ�յ�վ
  public String getDestination()throws RemoteException;
  //��ȡ�ƻ�����ʱ��
  public String getSkdDeparture()throws RemoteException;
  //��ȡ�ƻ�����ʱ��
  public String getSkdArrival()throws RemoteException;

  public void setOrigin(String origin)throws RemoteException;
  public void setDestination(String destination)throws RemoteException;
  public void setSkdDeparture(String skdDeparture)throws RemoteException;
  public void setSkdArrival(String skdArrival)throws RemoteException; 
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
