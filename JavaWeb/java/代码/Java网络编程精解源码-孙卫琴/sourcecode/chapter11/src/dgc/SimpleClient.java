package dgc;
public class SimpleClient{
  public static void main( String args[] ){
    String url="rmi://localhost/";
    try{
      Context namingContext=new InitialContext();
      HelloService service=(HelloService)namingContext.lookup(url+"HelloService");
      service.access();
      Thread.sleep(10000);
      service.bye();
    }catch( Exception e){
       e.printStackTrace();
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
