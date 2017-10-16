import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClientFullURL extends MailClient {
  private Folder folder;
  public void init()throws Exception{
    init(new URLName("imap://admin:1234@localhost/inbox"));
    //init(new URLName("pop3://java_mail:123456@pop.citiz.net/inbox"));
  }
  public void init(URLName urlName)throws Exception{
    //设置属性
    Properties props = new Properties();
     
    // 创建Session对象
    session = Session.getDefaultInstance(props);
    folder=session.getFolder(urlName);
    if(folder==null){ 
      System.out.println(urlName.getFile()+"邮件夹不存在");
      System.exit(0);
    }
   }
  
  public static void main(String[] args)throws Exception {
    MailClientFullURL client=new MailClientFullURL();
    client.init();
    client.browseMessagesFromFolder(client.folder);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
