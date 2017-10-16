package stock;
import java.rmi.*;
import javax.naming.*;

public class SimpleClient{

  public static void main( String args[] ){
    String url="rmi://localhost/";
    try{
      Context namingContext=new InitialContext();
      StockQuoteRegistry registry=
          (StockQuoteRegistry           )namingContext.lookup(url+"StockQuoteRegistry");
      
      StockQuote client=new StockQuoteImpl();
      registry.registerClient(client);    
  
    }catch( Exception e){
       e.printStackTrace();
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
