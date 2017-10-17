public class SimpleClient {
  public static void main(String args[])throws Exception {
    Socket s1 = new Socket("localhost",8000);
    System.out.println("��һ�����ӳɹ�");
    Socket s2 = new Socket("localhost",8000);
    System.out.println("�ڶ������ӳɹ�");
    Socket s3 = new Socket("localhost",8000);
    System.out.println("���������ӳɹ�");
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
