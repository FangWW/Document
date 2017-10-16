//文件名：StatefulShopTestClient.java
//有状态会话EJB测试客户端
package stateful;
import javax.naming.*;
import java.util.Properties;
import java.util.Vector;
import javax.rmi.PortableRemoteObject;
public class StatefulShopTestClient {
  private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
  private static final int MAX_OUTPUT_LINE_LENGTH = 100;
  private boolean logging = true;
  private StatefulShopHome StatefulShopHome = null;
  private StatefulShop StatefulShop = null;
  //构造器
  public StatefulShopTestClient() {
   log("Initializing bean access.");
    try {
      //得到context上下文
      Context ctx = getInitialContext();
      //查询StatefulShop
      Object ref = ctx.lookup("StatefulShop");
      //创建Home主接口
      StatefulShopHome = (StatefulShopHome) PortableRemoteObject.narrow(ref, StatefulShopHome.class);
      log("Succeeded initializing bean access.");
    }
    catch(Exception e) {
      if (logging) {
        log("Failed initializing bean access.");
      }
      e.printStackTrace();
    }
  }
//方法说明：初始化上下文
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
//方法说明：创建EJB实例
public StatefulShop create() {
    log("Calling create()");
    try {
      StatefulShop = StatefulShopHome.create();
      if (logging) {
        log("Succeeded: create()");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: create()");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from create(): " + StatefulShop + ".");
    }
    return StatefulShop;
  }
//方法说明：添加账户资金
  public void addGoods(int id,String goods,double value) {
    if (StatefulShop == null) {
      System.out.println("Error in addGoods(): " + ERROR_NULL_REMOTE);
      return ;
    }
    log("Calling addGoods(" + id+","+goods+","+value + ")");
    try {
      StatefulShop.addGoods(id,goods,value);
      if (logging) {
        log("Succeeded: addGoods(" + id+","+goods+","+value + ")");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: addGoods(" + id+","+goods+","+value +  ")");
      }
      e.printStackTrace();
    }
  }
//方法说明：提取账户资金
 public void removeGoods(int id) {
    if (StatefulShop == null) {
      System.out.println("Error in removeGoods(): " + ERROR_NULL_REMOTE);
      return ;
    }
   log("Calling removeGoods(" + id + ")");
    try {
      StatefulShop.removeGoods(id);
      if (logging) {
        log("Succeeded: removeGoods(" + id + ")");
       }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: removeGoods(" + id + ")");
      }
      e.printStackTrace();
    }
  }
//方法说明：获取账户资金数
  public Vector seeGoods() {
    Vector returnValue = new Vector();;
    if (StatefulShop == null) {
      System.out.println("Error in seeGoods(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    log("Calling seeGoods()");
    try {
      returnValue = StatefulShop.seeGoods();
      if (logging) {
        log("Succeeded: seeGoods()");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: seeGoods()");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from seeGoods(): " + returnValue + ".");
    }
    return returnValue;
  }
//方法说明：显示信息
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
  //Main method
  public static void main(String[] args) {
    StatefulShopTestClient client = new StatefulShopTestClient();
    client.create();
    client.seeGoods();
    client.addGoods(1,"ACR光驱",190);
    client.addGoods(2,"CPU AMD",280);
    client.addGoods(3,"精英主板",680);
    client.removeGoods(2);
    client.log("现在的商品:"+client.seeGoods());
  }
}
