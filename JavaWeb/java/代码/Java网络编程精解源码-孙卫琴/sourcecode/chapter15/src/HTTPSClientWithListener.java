public class HTTPSClientWithListener extends HTTPSClient{
  public void createSocket() throws Exception{
    factory=(SSLSocketFactory)SSLSocketFactory.getDefault();
    socket=(SSLSocket)factory.createSocket(host,port);
    String[] supported=socket.getSupportedCipherSuites();
    socket.setEnabledCipherSuites(supported);
    
    socket.addHandshakeCompletedListener(new HandshakeCompletedListener(){
      public void handshakeCompleted(HandshakeCompletedEvent event){
        System.out.println("���ֽ���"); 
        System.out.println("�����׼�Ϊ��"+event.getCipherSuite()); 
        System.out.println("�ỰΪ��"+event.getSession()); 
        System.out.println("ͨ�ŶԷ�Ϊ��"+event.getSession().getPeerHost()); 
      }
    });
  }

  public static void main(String args[])throws Exception{
    HTTPSClientWithListener client=new HTTPSClientWithListener();
    client.createSocket();
    client.communicate();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
