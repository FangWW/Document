/**
 * <p>Title: ��ȡ�������ƺ�IP��ַ</p>
 * <p>Description: ʹ��InetAddress����ȡ�������ƺ�IP��ַ��Ϣ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: NetInfo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class NetInfo {
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String[] args) {
    new NetInfo().say();
    }
/**
 *<br>����˵�����鿴�������ƺ�IP��ַ
 *<br>���������
 *<br>�������ͣ�
 */
 public void say() {
   try {
   InetAddress i = InetAddress.getLocalHost();
   System.out.println(i);                  //��������ƺ�IP
   System.out.println(i.getHostName());    //����
   System.out.println(i.getHostAddress()); //ֻ���IP
   }
   catch(Exception e){e.printStackTrace();}
 }
}

