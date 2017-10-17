package store;
public class StoreServer {
  public static void main( String args[] ){
    try{
      System.setProperty("java.security.policy",StoreClient.class.getResource("secure.policy").toString());
      System.setSecurityManager(new RMISecurityManager());

      StoreModel storeModel=new StoreModelImpl();
      Context namingContext=new InitialContext();
      namingContext.rebind( "rmi:storeModel", storeModel );
      System.out.println( "������ע����StoreModel����" );
    }catch( Exception e ){
      e.printStackTrace();
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
