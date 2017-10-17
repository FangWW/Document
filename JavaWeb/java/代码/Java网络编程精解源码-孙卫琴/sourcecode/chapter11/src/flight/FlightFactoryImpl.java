package flight;

public class FlightFactoryImpl extends UnicastRemoteObject
                                     implements FlightFactory{
    protected Hashtable<String,Flight> flights; //���Flight����Ļ���

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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
