package sync;

public class SimpleServer{
  public static void main( String args[] ){
    try{
       Stack stack = new StackImpl("a stack");

       Context namingContext=new InitialContext();
       namingContext.rebind( "rmi:MyStack", stack );
        
       System.out.println( "������ע����һ��Stack����" );
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
