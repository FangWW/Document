package stock;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       StockQuoteRegistryImpl registry=new StockQuoteRegistryImpl();
 
       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:StockQuoteRegistry", registry);
       System.out.println( "������ע����һ��StockQuoteRegistry����" );

       new Thread(registry).start();
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
