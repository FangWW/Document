import java.net.*;
import java.io.*;

public class MailSender{
  private String smtpServer="smtp.mydomain.com";  //SMTP邮件服务器的主机名
  //private String smtpServer="localhost";
  private int port=25;

  public static void main(String[] args){
    Message msg=new Message("tom@abc.com",   //发送者的邮件地址
                            "linda@def.com",  //接收者的邮件地址
                           "hello",  //邮件标题
                           "hi,I miss you very much."); //邮件正文
    new MailSender().sendMail(msg);
  }

  public void sendMail(Message msg){
    Socket socket=null;
    try{
      socket = new Socket(smtpServer,port);  //连接到邮件服务器
      BufferedReader br =getReader(socket);
      PrintWriter pw = getWriter(socket);
      String localhost= InetAddress.getLocalHost().getHostName();   //客户主机的名字

      sendAndReceive(null,br,pw); //仅仅是为了接收服务器的响应数据
      sendAndReceive("HELO " + localhost,br,pw);
      sendAndReceive("MAIL FROM: <" + msg.from+">",br,pw);
      sendAndReceive("RCPT TO: <" + msg.to+">",br,pw);
      sendAndReceive("DATA",br,pw);  //接下来开始发送邮件内容
      pw.println(msg.data);  //发送邮件内容
      System.out.println("Client>"+msg.data);
      sendAndReceive(".",br,pw);  //邮件发送完毕
      sendAndReceive("QUIT",br,pw);  //结束通信
    }catch (IOException e){
      e.printStackTrace();
    }finally{
      try{
        if(socket!=null)socket.close();
      }catch (IOException e) {e.printStackTrace();}
    }
  }

  /** 发送一行字符串，并接收一行服务器的响应数据*/
  private void sendAndReceive(String str,BufferedReader br,PrintWriter pw) throws IOException{
    if (str != null){
      System.out.println("Client>"+str);
      pw.println(str);  //发送完str字符串后，还会发送“\r\n”。
    }
    String response;
    if ((response = br.readLine()) != null)
      System.out.println("Server>"+response);
  }

   private PrintWriter getWriter(Socket socket)throws IOException{
     OutputStream socketOut = socket.getOutputStream();
     return new PrintWriter(socketOut,true);
   }
   private BufferedReader getReader(Socket socket)throws IOException{
     InputStream socketIn = socket.getInputStream();
     return new BufferedReader(new InputStreamReader(socketIn));
  }
}

class Message{  //表示邮件
  String from;  //发送者的邮件地址
  String to;  //接收者的邮件地址
  String subject;  //邮件标题
  String content;  //邮件正文
  String data;  //邮件内容，包括邮件标题和正文
  public Message(String from,String to, String subject, String content){
    this.from=from;
    this.to=to;
    this.subject=subject;
    this.content=content;
    data="Subject:"+subject+"\r\n"+content;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
