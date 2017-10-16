package stateless;
import javax.naming.*;
import java.util.Properties;
import javax.rmi.PortableRemoteObject;
/**
 * <p>Title: 无会话EJB测试客户程序</p>
 * <p>Description: 测试无状态会话EJB的功能是否有效。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: statelessTradeTestClient.java</p>
 * @author 杜江
 * @version 1.0
 */
public class statelessTradeTestClient {
  private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
  private static final int MAX_OUTPUT_LINE_LENGTH = 100;
  private boolean logging = true;
  private statelessTradeHome statelessTradeHomeObject = null;
  private statelessTrade statelessTradeObject = null;
/**
 *<br>方法说明：构造器
 *<br>输入参数：
 *<br>返回类型：
 */
  public statelessTradeTestClient() {
    log("Initializing bean access.");
    try {
      //得到上下文
      Context ctx = getInitialContext();
      //查询statelessTrade
      Object ref = ctx.lookup("statelessTrade");
      //创建home端口
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
 *<br>方法说明：获取初始化上下文
 *<br>输入参数：
 *<br>返回类型：Context
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
 *<br>方法说明：使用home端口创建远程接口
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：添加账户资金
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：提取资金
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：获取账户资金数
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：显示信息
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：主方法，运行程序
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args) {
    statelessTradeTestClient client = new statelessTradeTestClient();
    client.create();
    System.out.println("目前资金数："+client.getBalance());
    client.addFunds(400.0);
    System.out.println("目前资金数："+client.getBalance());
    client.removeFunds(200);
    System.out.println("目前资金数："+client.getBalance());
  }
}
