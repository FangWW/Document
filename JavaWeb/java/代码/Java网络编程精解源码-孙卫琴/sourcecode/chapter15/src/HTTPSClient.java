public class HTTPSClient {
  String host="www.usps.com";
  int port=443;
  SSLSocketFactory factory;
  SSLSocket socket;

  public void createSocket()throws Exception{
    factory=(SSLSocketFactory)SSLSocketFactory.getDefault();
    socket=(SSLSocket)factory.createSocket(host,port);
    String[] supported=socket.getSupportedCipherSuites();
    socket.setEnabledCipherSuites(supported);
  }
  
  public void communicate()throws Exception{
    StringBuffer sb=new StringBuffer("GET http://"+host+"/ HTTP/1.1\r\n");
    sb.append("Host:"+host+"\r\n");
    sb.append("Accept: */*\r\n");
    sb.append("\r\n");

    //����HTTP����
    OutputStream socketOut=socket.getOutputStream();
    socketOut.write(sb.toString().getBytes());
        
    //������Ӧ���
    InputStream socketIn=socket.getInputStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];  
    int len=-1;
    while((len=socketIn.read(buff))!=-1){
        buffer.write(buff,0,len);
    }
    //���ֽ�����ת��Ϊ�ַ���������ֻ��ʾ��������
    System.out.println(new String(buffer.toByteArray()).substring(1,1000));  
    
    socket.close();
  } 

  public static void main(String args[])throws Exception{
    HTTPSClient client=new HTTPSClient();
    client.createSocket();
    client.communicate();
  } 
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
