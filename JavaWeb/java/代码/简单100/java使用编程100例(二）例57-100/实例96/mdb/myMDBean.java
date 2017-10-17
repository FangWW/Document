//�������ඨ���ڰ�examples.ejb20.message��
package mdb;
//�������������������
import javax.ejb.CreateException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ��Ϣ������EJB
 */
public class myMDBean implements MessageDrivenBean, MessageListener {

  private static final boolean DEBUG = true;
  //������Ϣ����������
  private MessageDrivenContext m_context;
  //����bean����
  private int m_tradeLimit;

  // Ҳ����ʹ��WebLogic����־����
  private void log(String s) {
    if (DEBUG) System.out.println(s);
  } 
  
  /**
   * EJB�淶Ҫ��ķ���������û��ʹ��
   */
  public void ejbActivate() {
    log("ejbActivate called");
  }

  /**
   * EJB�淶Ҫ��ķ���������û��ʹ��
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  /**
   * EJB�淶Ҫ��ķ���������û��ʹ��
   */
  public void ejbPassivate() {
    log("ejbPassivate called");
  }

  /**
   * ���ûỰ������
   *
   * @���� ctx   MessageDrivenContext �Ự������
   */
  public void setMessageDrivenContext(MessageDrivenContext ctx) {
    log("setMessageDrivenContext called");
    m_context = ctx;
  }

  /**
   * ���������"TraderHome.java"�е�create������Ӧ
   * ���������Ĳ�����һ�µġ����ͻ��˵���TraderHome.create()��������������һ��
   * EJBeanʵ��������ejbCreate()������
   *
   * @�쳣               javax.ejb.CreateException if there is
   *                          a communications or systems failure
   */
  public void ejbCreate () throws CreateException {
    log("ejbCreate called");

  }

  // �ӿ�MessageListener����ķ���

  /**
   * ��Ϣ��Ӧ����
   */
  public void onMessage(Message msg) {
    TextMessage tm = (TextMessage) msg;
    try {
      String text = tm.getText();
      log("Received new Message : " + text);
    }
    catch(JMSException ex) {
      ex.printStackTrace();
    }
  }


}


