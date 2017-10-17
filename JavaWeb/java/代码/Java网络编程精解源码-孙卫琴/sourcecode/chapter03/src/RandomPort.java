public class RandomPort{
  public static void main(String args[])throws IOException{
    ServerSocket serverSocket=new ServerSocket(0);
    System.out.println("�����Ķ˿�Ϊ:"+serverSocket.getLocalPort());
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
