import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClientSendAttach extends MailClientForCitiz{
  private String fromAddr="java_mail@citiz.net";  //发送者地址
  private String toAddr="java_mail@citiz.net"; //接收者地址

  public void sendMessage(String fromAddr,String toAddr) throws Exception{
    //创建一个邮件
    Message msg = new MimeMessage(session);
    InternetAddress[] toAddrs =InternetAddress.parse(toAddr, false);
    msg.setRecipients(Message.RecipientType.TO, toAddrs);
    msg.setSubject("hello");
    msg.setSentDate(new Date());
    msg.setFrom(new InternetAddress(fromAddr));
    
    String attch1="attch1.rar";
    String attch2="attch2.rar";

    Multipart multipart=new MimeMultipart();
    
    //加入文本内容
    MimeBodyPart mimeBodyPart1=new MimeBodyPart(); 
    mimeBodyPart1.setText("How are you");
    multipart.addBodyPart(mimeBodyPart1); 
    
    //加入第一个附件
    MimeBodyPart mimeBodyPart2=new MimeBodyPart(); 
    FileDataSource fds=new FileDataSource(attch1); //得到数据源 
    mimeBodyPart2.setDataHandler(new DataHandler(fds)); 
    mimeBodyPart2.setDisposition(Part.ATTACHMENT);
    mimeBodyPart2.setFileName(fds.getName()); //设置文件名
    multipart.addBodyPart(mimeBodyPart2); 

    //加入第二个附件
    MimeBodyPart mimeBodyPart3=new MimeBodyPart(); 
    fds=new FileDataSource(attch2); //得到数据源 
    mimeBodyPart3.setDataHandler(new DataHandler(fds)); 
    mimeBodyPart3.setDisposition(Part.ATTACHMENT);
    mimeBodyPart3.setFileName(fds.getName()); //设置文件名
    multipart.addBodyPart(mimeBodyPart3); 

    //设置邮件的正文
    msg.setContent(multipart); 
    
    //发送邮件
    Transport.send(msg);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientSendAttach client=new MailClientSendAttach();
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
