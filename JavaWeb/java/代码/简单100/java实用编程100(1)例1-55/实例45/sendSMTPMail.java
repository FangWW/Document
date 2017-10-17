/**
 * <p>Title: ʹ��SMTP�����ʼ�</p>
 * <p>Description: ��ʵ��ͨ��ʹ��socket��ʽ������SMTPЭ�鷢���ʼ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: sendSMTPMail.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class sendSMTPMail {
/**
 *<br>����˵����������
 *<br>���������1��������ip��2���Է��ʼ���ַ
 *<br>�������ͣ�
 */	
public static void main(String[] arges) {
  if(arges.length!=2){
  	 System.out.println("use java sendSMTPMail hostname | mail to");
  	 return;
  	}
   sendSMTPMail t = new sendSMTPMail();
   t.sendMail(arges[0], arges[1]);
   }
/**
 *<br>����˵���������ʼ�
 *<br>���������String mailServer �ʼ����շ�����
 *<br>���������String recipient �����ʼ��ĵ�ַ
 *<br>�������ͣ�
 */
public void sendMail(String mailServer, String recipient) {
   try {
   	  //��Socket��25�˿�
      Socket s = new Socket(mailServer, 25);
      //������������
      BufferedReader in = new BufferedReader
          (new InputStreamReader(s.getInputStream(), "8859_1"));
      BufferedWriter out = new BufferedWriter
          (new OutputStreamWriter(s.getOutputStream(), "8859_1"));
      //������HELO�������ʾ�Է��������ʺ�
      send(in, out, "HELO theWorld");
      //���߷������ҵ��ʼ���ַ����Щ������ҪУ�������ַ
      send(in, out, "MAIL FROM: <dujiang@mailhost.com>");
      //ʹ�á�RCPT TO��������߷����������ʼ����ʼ���ַ
      send(in, out, "RCPT TO: " + recipient);
      //����һ����DATA����ʾ���潫���ʼ�����
      send(in, out, "DATA");
      //ʹ��Subject�����ע�ʼ�����
      send(out, "Subject: ����һ�����Գ���");
      //ʹ�á�From����ע�ʼ�����Դ
      send(out, "From: riverwind <dujiang@mailhost.com>");
      send (out, "\n");
      //�ʼ�����
      send(out, "����һ��ʹ��SMTPЭ�鷢�͵��ʼ������������ɾ����");
      send(out, "\n.\n");
      //���͡�QUIT���˿��ʼ���ͨѶ
      send(in, out, "QUIT");
      s.close();
      }
   catch (Exception e) {
      e.printStackTrace();
      }
   }
/**
 *<br>����˵����������Ϣ�������ջ���
 *<br>���������
 *<br>�������ͣ�
 */
 public void send(BufferedReader in, BufferedWriter out, String s) {
   try {
      out.write(s + "\n");
      out.flush();
      System.out.println(s);
      s = in.readLine();
      System.out.println(s);
      }
   catch (Exception e) {
      e.printStackTrace();
      }
   }
/**
 *<br>����˵�������ط�������socketд����Ϣ
 *<br>���������BufferedWriter out ���������
 *<br>���������String s д�����Ϣ
 *<br>�������ͣ�
 */
 public void send(BufferedWriter out, String s) {
   try {
      out.write(s + "\n");
      out.flush();
      System.out.println(s);
      }
   catch (Exception e) {
      e.printStackTrace();
      }
   }
}
