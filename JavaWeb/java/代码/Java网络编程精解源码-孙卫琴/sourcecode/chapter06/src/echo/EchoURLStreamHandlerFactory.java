package echo;
public class EchoURLStreamHandlerFactory implements URLStreamHandlerFactory{
  public URLStreamHandler createURLStreamHandler(String protocol){
    if(protocol.equals("echo"))
      return new EchoURLStreamHandler();
    else
      return null;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
