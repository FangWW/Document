import java.io.*;
import java.net.*;
/**
 * <p>Title: SMTP协议接收邮件</p>
 * <p>Description: 通过Socket连接POP3服务器，使用SMTP协议接收邮件服务器中的邮件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
class POP3Demo
{
/**
 *<br>方法说明：主方法，接收用户输入
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
  	if(args.length!=3){
  	 System.out.println("USE: java POP3Demo mailhost user password");
  	}
  	new POP3Demo().receive(args[0],args[1],args[2]);
  }
/**
 *<br>方法说明：接收邮件
 *<br>输入参数：String popServer 服务器地址
 *<br>输入参数：String popUser 邮箱用户名
 *<br>输入参数：String popPassword 邮箱密码
 *<br>返回类型：
 */
  public static void receive (String popServer, String popUser, String popPassword)
  {
   String POP3Server = popServer;
   int POP3Port = 110;
   Socket client = null;
   try
   {
     // 创建一个连接到POP3服务程序的套接字。
     client = new Socket (POP3Server, POP3Port);
     //创建一个BufferedReader对象，以便从套接字读取输出。
     InputStream is = client.getInputStream ();
     BufferedReader sockin;
     sockin = new BufferedReader (new InputStreamReader (is));
     //创建一个PrintWriter对象，以便向套接字写入内容。
     OutputStream os = client.getOutputStream ();
     PrintWriter sockout;
     sockout = new PrintWriter (os, true); // true for auto-flush
     // 显示POP3握手信息。
     System.out.println ("S:" + sockin.readLine ());
     
     /*--   与POP3服务器握手过程   --*/     
      System.out.print ("C:");
      String cmd = "user "+popUser;
      // 将用户名发送到POP3服务程序。
      System.out.println (cmd);
      sockout.println (cmd);
      // 读取POP3服务程序的回应消息。
      String reply = sockin.readLine ();
      System.out.println ("S:" + reply);

      System.out.print ("C:");
      cmd = "pass ";
      // 将密码发送到POP3服务程序。
      System.out.println(cmd+"*********");
      sockout.println (cmd+popPassword);
      // 读取POP3服务程序的回应消息。
      reply = sockin.readLine ();
      System.out.println ("S:" + reply);
      
           
      System.out.print ("C:");
      cmd = "stat";
      // 获取邮件数据。
      System.out.println(cmd);
      sockout.println (cmd);
      // 读取POP3服务程序的回应消息。
      reply = sockin.readLine ();
      System.out.println ("S:" + reply);
      if(reply==null) return;
      System.out.print ("C:");
      cmd = "retr 1";
      // 将接收第一丰邮件命令发送到POP3服务程序。
      System.out.println(cmd);
      sockout.println (cmd);
      
      // 输入了RETR命令并且返回了成功的回应码，持续从套接字读取输出， 
      // 直到遇到<CRLF>.<CRLF>。这时从套接字读出的输出就是邮件的内容。
      if (cmd.toLowerCase ().startsWith ("retr") &&
        reply.charAt (0) == '+')
        do
        {
          reply = sockin.readLine ();
          System.out.println ("S:" + reply);
          if (reply != null && reply.length () > 0)
            if (reply.charAt (0) == '.')
              break;
        }
        while (true);
      cmd = "quit";
      // 将命令发送到POP3服务程序。
      System.out.print (cmd);
      sockout.println (cmd);     
   }
   catch (IOException e)
   {
     System.out.println (e.toString ());
   }
   finally
   {
     try
     {  if (client != null)
          client.close ();
     }
     catch (IOException e)
     {
     }
   }
  }
}