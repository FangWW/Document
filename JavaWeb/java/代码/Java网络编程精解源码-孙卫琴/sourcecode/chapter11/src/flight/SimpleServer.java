package flight;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       FlightFactory factory  = new FlightFactoryImpl();
 
       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:FlightFactory", factory );
        
       System.out.println( "������ע����һ��FlightFactory����" );
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
