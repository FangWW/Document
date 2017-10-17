public class Client {
  public static void main(String args[])throws Exception{
    final int length=100;
    String host="localhost";
    int port=8000;

    Socket[] sockets=new Socket[length];
    for(int i=0;i<length;i++){  //��ͼ����100������
      sockets[i]=new Socket(host, port);
      System.out.println("��"+(i+1)+"�����ӳɹ�");
    }
    Thread.sleep(3000);
    for(int i=0;i<length;i++){
      sockets[i].close();  //�Ͽ�����
    } 
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
