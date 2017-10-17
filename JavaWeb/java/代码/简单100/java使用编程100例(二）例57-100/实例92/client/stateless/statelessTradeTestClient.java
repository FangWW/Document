package stateless;
import java.util.Properties;

import javax.rmi.PortableRemoteObject;
/**
 * <p>Title: �޻ỰEJB���Կͻ�����</p>
 * <p>Description: ������״̬�ỰEJB�Ĺ����Ƿ���Ч��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: statelessTradeTestClient.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class statelessTradeTestClient {
  private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
  private static final int MAX_OUTPUT_LINE_LENGTH = 100;
  private boolean logging = true;
  private statelessTradeHome statelessTradeHomeObject = null;
  private statelessTrade statelessTradeObject = null;
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public statelessTradeTestClient() {
    log("Initializing bean access.");
    try {
      //�õ�������
      Context ctx = getInitialContext();
      //��ѯstatelessTrade
      Object ref = ctx.lookup("statelessTrade");
      //����home�˿�
      statelessTradeHomeObject = (statelessTradeHome) PortableRemoteObject.narrow(ref, statelessTradeHome.class);
      if (logging) {
        log("Succeeded initializing bean access.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed initializing bean access.");
      }
      e.printStackTrace();
    }
  }

/**
 *<br>����˵������ȡ��ʼ��������
 *<br>���������
 *<br>�������ͣ�Context
 */
  private Context getInitialContext() throws Exception {
    String url = "t3://localhost:7001";
    String user = null;
    String password = null;
    Properties properties = null;
    try {
      properties = new Properties();
      properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
      properties.put(Context.PROVIDER_URL, url);
      if (user != null) {
        properties.put(Context.SECURITY_PRINCIPAL, user);
        properties.put(Context.SECURITY_CREDENTIALS, password == null ? "" : password);
      }
      return new InitialContext(properties);
    }
    catch(Exception e) {
      log("Unable to connect to WebLogic server at " + url);
      log("Please make sure that the server is running.");
      throw e;
    }
  }
/**
 *<br>����˵����ʹ��home�˿ڴ���Զ�̽ӿ�
 *<br>���������
 *<br>�������ͣ�
 */
  public statelessTrade create() {
    log("Calling create()");
    try {
      statelessTradeObject = statelessTradeHomeObject.create();
      log("Succeeded: create()");
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: create()");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from create(): " + statelessTradeObject + ".");
    }
    return statelessTradeObject;
  }

/**
 *<br>����˵��������˻��ʽ�
 *<br>���������
 *<br>�������ͣ�
 */
  public void addFunds(double fund) {
    if (statelessTradeObject == null) {
      System.out.println("Error in addFunds(): " + ERROR_NULL_REMOTE);
      return ;
    }
    log("Calling addFunds(" + fund + ")");
    try {
      statelessTradeObject.addFunds(fund);
      if (logging) {
        log("Succeeded: addFunds(" + fund + ")");
       }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: addFunds(" + fund + ")");
      }
      e.printStackTrace();
    }
  }
/**
 *<br>����˵������ȡ�ʽ�
 *<br>���������
 *<br>�������ͣ�
 */
  public void removeFunds(double fund) {
    if (statelessTradeObject == null) {
      System.out.println("Error in removeFunds(): " + ERROR_NULL_REMOTE);
      return ;
    }
    log("Calling removeFunds(" + fund + ")");
    try {
      statelessTradeObject.removeFunds(fund);
      if (logging) {
        log("Succeeded: removeFunds(" + fund + ")");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: removeFunds(" + fund + ")");
      }
      e.printStackTrace();
    }
  }
/**
 *<br>����˵������ȡ�˻��ʽ���
 *<br>���������
 *<br>�������ͣ�
 */
  public double getBalance() {
    double returnValue = 0f;
    if (statelessTradeObject == null) {
      System.out.println("Error in getBalance(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    log("Calling getBalance()");
    try {
      returnValue = statelessTradeObject.getBalance();
      if (logging) {
        log("Succeeded: getBalance()");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: getBalance()");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from getBalance(): " + returnValue + ".");
    }
    return returnValue;
  }
/**
 *<br>����˵������ʾ��Ϣ
 *<br>���������
 *<br>�������ͣ�
 */
  private void log(String message) {
    if (message == null) {
      System.out.println("-- null");
      return ;
    }
    if (message.length() > MAX_OUTPUT_LINE_LENGTH) {
      System.out.println("-- " + message.substring(0, MAX_OUTPUT_LINE_LENGTH) + " ...");
    }
    else {
      System.out.println("-- " + message);
    }
  }

/**
 *<br>����˵���������������г���
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args) {
    statelessTradeTestClient client = new statelessTradeTestClient();
    client.create();
    System.out.println("Ŀǰ�ʽ�����"+client.getBalance());
    client.addFunds(400.0);
    System.out.println("Ŀǰ�ʽ�����"+client.getBalance());
    client.removeFunds(200);
    System.out.println("Ŀǰ�ʽ�����"+client.getBalance());
  }
}
