/**
 * <p>Title: �򵥷����������</p>
 * <p>Description: ����һ���򵥵ķ������˳���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SampleServer.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class SampleServer{
  public static void main(String[] arges){
    try{
      int port = 8888;
      //ʹ��8888�˿ڴ���һ��ServerSocket
      ServerSocket mySocket = new ServerSocket(port);
      //�ȴ������Ƿ��пͻ�������
      Socket sk = mySocket.accept();
      //���뻺��
      BufferedReader in = new BufferedReader (
                         new InputStreamReader (sk.getInputStream ()));
      //�������
      PrintWriter out = new PrintWriter (
                         new BufferedWriter(
                          new OutputStreamWriter(
                           sk.getOutputStream ())), true);
      //��ӡ���յ��Ŀͻ��˷��͹�������Ϣ
      System.out.println("�ͻ�����Ϣ:"+in.readLine ());
      //��ͻ��˻���Ϣ
      out.println("��ã����Ƿ���������ʹ�õĶ˿ںţ� "+port); 
    }catch(Exception e){
      System.out.println(e);
    }
  }
}