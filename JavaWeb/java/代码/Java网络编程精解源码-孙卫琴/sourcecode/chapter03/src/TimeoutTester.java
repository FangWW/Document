public class TimeoutTester{
  public static void main(String args[])throws IOException{
    ServerSocket serverSocket=new ServerSocket(8000);
    serverSocket.setSoTimeout(6000); //�ȴ��ͻ����ӵ�ʱ�䲻����6��
    Socket socket=serverSocket.accept(); 
    socket.close();
    System.out.println("�������ر�");
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
