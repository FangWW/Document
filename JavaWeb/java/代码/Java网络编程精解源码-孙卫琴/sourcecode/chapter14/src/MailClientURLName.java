import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClientURLName extends MailClient {
  private String sendHost="localhost"; //发送邮件服务器
  private String receiveHost;
  private String receiveProtocol; //接收邮件协议
  private String username;
  private String password;
  private String fromAddr="admin@mydomain.com";  //发送者地址
  private String toAddr="admin@mydomain.com"; //接收者地址

  public void init()throws Exception{
    init(new URLName("imap://admin:1234@localhost/"));
  }
  public void init(URLName urlName)throws Exception{
    receiveProtocol=urlName.getProtocol();   
    receiveHost=urlName.getHost();
    username=urlName.getUsername();
    password=urlName.getPassword();
   
    //设置属性
    Properties props = new Properties();
    props.put("mail.smtp.host", sendHost);
    
    // 创建Session对象
    session = Session.getDefaultInstance(props);

    // 创建Store对象
    store = session.getStore(receiveProtocol);
    //连接到邮件服务器
    store.connect(receiveHost,username,password);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientURLName client=new MailClientURLName();
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
