//声明本类定义在包examples.ejb20.message中
package mdb;
//声明本类引入的其它类
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 消息驱动的EJB
 */
public class myMDBean implements MessageDrivenBean, MessageListener {

  private static final boolean DEBUG = true;
  //声明消息驱动上下文
  private MessageDrivenContext m_context;
  //定义bean属性
  private int m_tradeLimit;

  // 也可以使用WebLogic的日志服务
  private void log(String s) {
    if (DEBUG) System.out.println(s);
  } 
  
  /**
   * EJB规范要求的方法，在这没有使用
   */
  public void ejbActivate() {
    log("ejbActivate called");
  }

  /**
   * EJB规范要求的方法，在这没有使用
   */
  public void ejbRemove() {
    log("ejbRemove called");
  }

  /**
   * EJB规范要求的方法，在这没有使用
   */
  public void ejbPassivate() {
    log("ejbPassivate called");
  }

  /**
   * 设置会话上下文
   *
   * @参数 ctx   MessageDrivenContext 会话上下文
   */
  public void setMessageDrivenContext(MessageDrivenContext ctx) {
    log("setMessageDrivenContext called");
    m_context = ctx;
  }

  /**
   * 这个方法与"TraderHome.java"中的create方法对应
   * 两个方法的参数是一致的。当客户端调用TraderHome.create()方法，容器分配一个
   * EJBean实例并调用ejbCreate()方法。
   *
   * @异常               javax.ejb.CreateException if there is
   *                          a communications or systems failure
   */
  public void ejbCreate () throws CreateException {
    log("ejbCreate called");

  }

  // 接口MessageListener定义的方法

  /**
   * 消息响应方法
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


