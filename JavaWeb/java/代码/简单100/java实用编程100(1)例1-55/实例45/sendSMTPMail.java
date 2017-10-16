import java.net.*;
import java.io.*;
/**
 * <p>Title: 使用SMTP发送邮件</p>
 * <p>Description: 本实例通过使用socket方式，根据SMTP协议发送邮件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: sendSMTPMail.java</p>
 * @author 杜江
 * @version 1.0
 */
public class sendSMTPMail {
/**
 *<br>方法说明：主方法
 *<br>输入参数：1。服务器ip；2。对方邮件地址
 *<br>返回类型：
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
 *<br>方法说明：发送邮件
 *<br>输入参数：String mailServer 邮件接收服务器
 *<br>输入参数：String recipient 接收邮件的地址
 *<br>返回类型：
 */
public void sendMail(String mailServer, String recipient) {
   try {
   	  //有Socket打开25端口
      Socket s = new Socket(mailServer, 25);
      //缓存输入和输出
      BufferedReader in = new BufferedReader
          (new InputStreamReader(s.getInputStream(), "8859_1"));
      BufferedWriter out = new BufferedWriter
          (new OutputStreamWriter(s.getOutputStream(), "8859_1"));
      //发出“HELO”命令，表示对服务器的问候
      send(in, out, "HELO theWorld");
      //告诉服务器我的邮件地址，有些服务器要校验这个地址
      send(in, out, "MAIL FROM: <dujiang@mailhost.com>");
      //使用“RCPT TO”命令告诉服务器解释邮件的邮件地址
      send(in, out, "RCPT TO: " + recipient);
      //发送一个“DATA”表示下面将是邮件主体
      send(in, out, "DATA");
      //使用Subject命令标注邮件主题
      send(out, "Subject: 这是一个测试程序！");
      //使用“From”标注邮件的来源
      send(out, "From: riverwind <dujiang@mailhost.com>");
      send (out, "\n");
      //邮件主体
      send(out, "这是一个使用SMTP协议发送的邮件！如果打扰请删除！");
      send(out, "\n.\n");
      //发送“QUIT”端口邮件的通讯
      send(in, out, "QUIT");
      s.close();
      }
   catch (Exception e) {
      e.printStackTrace();
      }
   }
/**
 *<br>方法说明：发送信息，并接收回信
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：重载方法。向socket写入信息
 *<br>输入参数：BufferedWriter out 输出缓冲器
 *<br>输入参数：String s 写入的信息
 *<br>返回类型：
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
