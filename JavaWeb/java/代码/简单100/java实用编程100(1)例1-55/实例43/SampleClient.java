/**
 * <p>Title: �򵥷������ͻ���</p>
 * <p>Description: ��������һ���򵥵Ŀͻ��ˣ������ͷ���������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SampleClient.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class SampleClient{
  public static void main(String[] arges){
    try{
      //��ȡһ��IP��null��ʾ����
      InetAddress addr = InetAddress.getByName(null);
      //��8888�˿ڣ����������������
      Socket sk = new Socket (addr, 8888);
      //��������
      BufferedReader in = new BufferedReader (
                         new InputStreamReader (sk.getInputStream ()));
      //�������
      PrintWriter out = new PrintWriter (
                         new BufferedWriter(
                          new OutputStreamWriter(
                           sk.getOutputStream ())), true);
     //�������������Ϣ
     out.println ("��ã�");
     //���շ�������Ϣ
     System.out.println (in.readLine ());
    }catch(Exception e){
      System.out.println(e);
    }
  }
}