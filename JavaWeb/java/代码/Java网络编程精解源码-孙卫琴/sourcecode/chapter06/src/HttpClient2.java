public class HttpClient2{
  public static void main(String args[])throws IOException{
    URL url=new URL("http://www.javathinker.org/hello.htm");
    URLConnection connection=url.openConnection();
    //������Ӧ���
    System.out.println("�������ͣ�"+connection.getContentType());
    System.out.println("���ĳ��ȣ�"+connection.getContentLength());
    
    InputStream in=connection.getInputStream(); //��ȡ��Ӧ����
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];  
    int len=-1;

    while((len=in.read(buff))!=-1){
      buffer.write(buff,0,len);
    }
     
    System.out.println(new String(buffer.toByteArray()));  //���ֽ�����ת��Ϊ�ַ���  
  } 
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
