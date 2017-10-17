package echo;
public class EchoURLStreamHandler extends URLStreamHandler{
  public int getDefaultPort(){
    return 8000;
  }
  protected URLConnection openConnection(URL url)throws IOException{
    return new EchoURLConnection(url);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
