/**
 * <p>Title: ����ϵͳ����</p>
 * <p>Description:����һ��ϵͳ�������ʾʹ��Runtime�ࡣ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CmdExec.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class CmdExec {
/**
 *<br>����˵����������������ϵͳ����
 *<br>���������String cmdline �����ַ�
 *<br>�������ͣ�
 */
  public CmdExec(String cmdline) {
    try {
     String line;
     //����ϵͳ����
     Process p = Runtime.getRuntime().exec(cmdline);
     //ʹ�û�����������ȡ��Ļ�����
     BufferedReader input = 
       new BufferedReader
         (new InputStreamReader(p.getInputStream()));
     //��ȡ��Ļ���
     while ((line = input.readLine()) != null) {
       System.out.println("java print:"+line);
       }
     //�ر�������
     input.close();
     } 
    catch (Exception err) {
     err.printStackTrace();
     }
   }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
public static void main(String argv[]) {
   new CmdExec("myprog.bat");
  }
}

