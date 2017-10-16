package mdb;
//声明本类引入的其它类
import java.rmi.RemoteException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;



import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * 这个类演示调用一个消息驱动的bean并在主题消息中发布报价。
 *
 */

public class Client {
  static private String TOPIC_NAME = "myQQ";
  //声明服务器url
  private String m_url;
  //声明上下文
  private Context m_context;
  //声明主题连接
  private TopicConnection m_topicConnection;
  //构造方法
  public Client(String url)
    throws NamingException
  {
    m_url = url;
        
    try {
      // 创建上下文
      m_context = getInitialContext();
      // 创建连接并启动
      TopicConnectionFactory cf =
        (TopicConnectionFactory) m_context.lookup("weblogic.jms.ConnectionFactory");
      m_topicConnection = cf.createTopicConnection();
      m_topicConnection.start();
      
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }


  /**
   * 用命令行运行本实例:
    */
  public static void main(String[] args) throws Exception {
    String url  = "t3://localhost:7001";
    
    // 解析命令行参数
     if (args.length == 1) {
          url = args[0];
    }

    Client client = null;
    try {
    //本类实例
      client = new Client(url);
    } catch (NamingException ne) {
      System.exit(1);
    }

    try {
    //调用例程
      client.send();
    }
    catch (Exception e) {
    //异常处理
      System.out.println(e);
    } 
  }

  /**
   * 发送消息
   */
  public void send()
    throws RemoteException, JMSException, NamingException
  {
  //声明主题	
    Topic newTopic = null;
    //声明主题会话
    TopicSession session = null;
    try {
    //创建主题会话	
      session =
        m_topicConnection.createTopicSession(false,   // non transacted
                                             Session.AUTO_ACKNOWLEDGE);
      //查找主题
      newTopic = (Topic) m_context.lookup(TOPIC_NAME);
    }
    catch(NamingException ex) {
    //异常处理
      newTopic = session.createTopic(TOPIC_NAME);
      m_context.bind(TOPIC_NAME, newTopic);
    }
    //创建消息发布
    TopicPublisher sender = session.createPublisher(newTopic);
    //创建文本消息
    TextMessage tm = session.createTextMessage();
    String[] quotes = new String[] {
      "CPU AMD $350", "HD IBM $680", "CD-ROM acr52X $220"
    };
    for (int i = 0; i < quotes.length; i++) {
    //发送消息
      tm.setText(quotes[i]);
      sender.publish(tm);
    }
  }


  /**
   * 初始化上下文
   */
  private Context getInitialContext() throws NamingException {
    
    try {
      // 属性对象
      Properties p = new Properties();
      p.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      p.put(Context.PROVIDER_URL, m_url);
      return new InitialContext(p);
    }
    catch (NamingException e) {
      log("can't get a connection to the WebLogic server at "+m_url);
      throw e;
    }
  }

  private static void log(String s) {
    System.out.println(s);
  }
  
}
