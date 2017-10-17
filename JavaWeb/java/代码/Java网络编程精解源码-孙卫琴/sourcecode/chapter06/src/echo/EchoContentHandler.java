package echo;
public class EchoContentHandler extends ContentHandler{
  public Object getContent(URLConnection connection)throws IOException{
     InputStream in=connection.getInputStream();
     BufferedReader br=new BufferedReader(new InputStreamReader(in));
     return br.readLine();
  }
  public Object getContent(URLConnection connection,Class[] classes)throws IOException{
    InputStream in=connection.getInputStream();
    for(int i=0;i<classes.length;i++){
      if(classes[i]==InputStream.class)
        return in;
      else if(classes[i]==String.class)
        return getContent(connection);
    }
    return null;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
