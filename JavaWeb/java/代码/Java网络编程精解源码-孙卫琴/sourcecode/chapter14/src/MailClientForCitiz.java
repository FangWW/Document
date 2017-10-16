import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

public class MailClientForCitiz extends MailClient{
  private Authenticator ma;
  private String sendHost="smtp.citiz.net";
  private String receiveHost="pop.citiz.net";
  private String receiveProtocol="pop3";
  private String username = "java_mail";
  private String password = "123456";
  private String fromAddr="java_mail@citiz.net";  //发送者地址
  private String toAddr="java_mail@citiz.net"; //接收者地址
 
  public void init()throws Exception{
    //设置属性
    Properties  props = new Properties();
    props.put("mail.smtp.host", sendHost);
    props.put("mail.smtp.auth","true"); //SMTP服务器需要身份验证

    ma=new Authenticator(){
         public PasswordAuthentication getPasswordAuthentication() {
           System.out.println("call getPasswordAuthentication()");
           return new PasswordAuthentication(username,password);
         }
    };
    
    session = Session.getDefaultInstance(props,ma);

    // 创建Store对象
    store = session.getStore(receiveProtocol);
    //连接到邮件服务器上的账户
    store.connect(receiveHost,null, null);
  }
  
  public static void main(String[] args)throws Exception {
    MailClientForCitiz client=new MailClientForCitiz();
    client.init();
    client.sendMessage(client.fromAddr,client.toAddr);
    client.receiveMessage();
    client.close();
    System.exit(0);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
