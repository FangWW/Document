import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClient {
  protected Session session;
  protected Store store;
  private String sendHost="localhost"; //发送邮件服务器
  private String receiveHost="localhost"; //接收邮件服务器
  private String sendProtocol="smtp"; //发送邮件协议
  private String receiveProtocol="imap"; //接收邮件协议
  private String username = "admin";
  private String password = "1234";
  private String fromAddr="admin@mydomain.com";  //发送者地址
  private String toAddr="admin@mydomain.com"; //接收者地址

  public void init()throws Exception{
    //设置属性
    Properties  props = new Properties();
    props.put("mail.transport.protocol", sendProtocol);
    props.put("mail.store.protocol", receiveProtocol);
    props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
    props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
    props.put("mail.smtp.host", sendHost); //设置发送邮件服务器

    // 创建Session对象
    session = Session.getDefaultInstance(props);
    //session.setDebug(true); //输出跟踪日志

    // 创建Store对象
    store = session.getStore(receiveProtocol);
    //连接到收邮件服务器
    store.connect(receiveHost,username,password);
  }
  
  public void close()throws Exception{
    store.close();
  }
  public void sendMessage(String fromAddr,String toAddr)throws Exception{
    //创建一个邮件
    Message msg = createSimpleMessage(fromAddr,toAddr);
    //发送邮件
    Transport.send(msg);
  }
  
  public Message createSimpleMessage(String fromAddr,String toAddr)throws Exception{
    //创建一封纯文本类型的邮件
    Message msg = new MimeMessage(session);
    InternetAddress[] toAddrs =InternetAddress.parse(toAddr, false);
    msg.setRecipients(Message.RecipientType.TO, toAddrs);
    msg.setSentDate(new Date());     
    msg.setSubject("hello");
    msg.setFrom(new InternetAddress(fromAddr));
    msg.setText("How are you");
    return msg; 
  }
  public void receiveMessage()throws Exception{
    browseMessagesFromFolder("inbox");
  }
  
  public void browseMessagesFromFolder(String folderName)throws Exception{
    Folder folder=store.getFolder(folderName);
    if(folder==null)
      throw new Exception(folderName+"邮件夹不存在");
    browseMessagesFromFolder(folder);
  }
  
  public void browseMessagesFromFolder(Folder folder)throws Exception{
    folder.open(Folder.READ_ONLY);
    System.out.println("You have "+folder.getMessageCount()+" messages in inbox.");
    System.out.println("You have "+folder.getUnreadMessageCount()+" unread messages in inbox.");

    //读邮件
    Message[] messages=folder.getMessages();
    for(int i=1;i<=messages.length;i++){
      System.out.println("------第"+i+"封邮件-------");
      //打印邮件信息
      folder.getMessage(i).writeTo(System.out);
      System.out.println();
    }
    folder.close(false);  //关闭邮件夹，但不删除邮件夹中标志为“deleted”的邮件
  }

  public static void main(String[] args)throws Exception {
    MailClient client=new MailClient();
    client.init();
    client.sendMessage(client.fromAddr,client.toAddr);
    client.receiveMessage();
    client.close();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
