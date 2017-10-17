package linger;
public class SimpleServer {
  public static void main(String args[])throws Exception {
    ServerSocket serverSocket = new ServerSocket(8000);
    Socket s=serverSocket.accept();
    Thread.sleep(5000);  //˯��5����ٶ�������
    InputStream in=s.getInputStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];
    int len=-1;
    do{
        len=in.read(buff);
        if(len!=-1)buffer.write(buff,0,len);
     }while(len!=-1);
    System.out.println(new String(buffer.toByteArray()));  //���ֽ�����ת��Ϊ�ַ���
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
