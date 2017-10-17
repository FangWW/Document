package flight2;

public class SimpleClient{
  public static void main(String[] args){
    String url="rmi://localhost/";
    try{
      Context namingContext=new InitialContext();
      FlightFactory factory=(FlightFactory)                           namingContext.lookup(url+"FlightFactory");

      Flight flight1 = factory.getFlight("795");
      flight1.setOrigin("Shanghai");
      flight1.setDestination("Beijing");
      System.out.println("Flight "+flight1.getFlightNumber()+":");
      System.out.println("From "+flight1.getOrigin()+" to "+
                                        flight1.getDestination());
      
      Flight flight2 = factory.getFlight("795");
      System.out.println("Flight "+flight2.getFlightNumber()+":");
      System.out.println("From "+flight2.getOrigin()+" to "+
                                        flight2.getDestination());
      System.out.println("flight1��"+flight1.getClass().getName()+"��ʵ��");
      System.out.println("flight2��"+flight2.getClass().getName()+"��ʵ��");
      System.out.println("flight1==flight2:"+(flight1==flight2));
      System.out.println("flight1.equals(flight2):"+(flight1.equals(flight2)));
    }catch(Exception e){
       e.printStackTrace();
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
