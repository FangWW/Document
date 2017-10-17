package mdb;
//�������������������
import java.rmi.RemoteException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * �������ʾ����һ����Ϣ������bean����������Ϣ�з������ۡ�
 *
 */

public class Client {
  static private String TOPIC_NAME = "myQQ";
  //����������url
  private String m_url;
  //����������
  private Context m_context;
  //������������
  private TopicConnection m_topicConnection;
  //���췽��
  public Client(String url)
    throws NamingException
  {
    m_url = url;
        
    try {
      // ����������
      m_context = getInitialContext();
      // �������Ӳ�����
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
   * �����������б�ʵ��:
    */
  public static void main(String[] args) throws Exception {
    String url  = "t3://localhost:7001";
    
    // ���������в���
     if (args.length == 1) {
          url = args[0];
    }

    Client client = null;
    try {
    //����ʵ��
      client = new Client(url);
    } catch (NamingException ne) {
      System.exit(1);
    }

    try {
    //��������
      client.send();
    }
    catch (Exception e) {
    //�쳣����
      System.out.println(e);
    } 
  }

  /**
   * ������Ϣ
   */
  public void send()
    throws RemoteException, JMSException, NamingException
  {
  //��������	
    Topic newTopic = null;
    //��������Ự
    TopicSession session = null;
    try {
    //��������Ự	
      session =
        m_topicConnection.createTopicSession(false,   // non transacted
                                             Session.AUTO_ACKNOWLEDGE);
      //��������
      newTopic = (Topic) m_context.lookup(TOPIC_NAME);
    }
    catch(NamingException ex) {
    //�쳣����
      newTopic = session.createTopic(TOPIC_NAME);
      m_context.bind(TOPIC_NAME, newTopic);
    }
    //������Ϣ����
    TopicPublisher sender = session.createPublisher(newTopic);
    //�����ı���Ϣ
    TextMessage tm = session.createTextMessage();
    String[] quotes = new String[] {
      "CPU AMD $350", "HD IBM $680", "CD-ROM acr52X $220"
    };
    for (int i = 0; i < quotes.length; i++) {
    //������Ϣ
      tm.setText(quotes[i]);
      sender.publish(tm);
    }
  }


  /**
   * ��ʼ��������
   */
  private Context getInitialContext() throws NamingException {
    
    try {
      // ���Զ���
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
