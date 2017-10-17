package echo;
public class EchoContentHandlerFactory implements ContentHandlerFactory{
  public ContentHandler createContentHandler(String mimetype){
    if(mimetype.equals("text/plain")){
      return new EchoContentHandler();
    }else{
      return null;
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
