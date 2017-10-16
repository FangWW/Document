package bmp;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Properties;
import java.util.Vector;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * <p>Title: 客户端</p>
 * <p>Description: 这个类演示了如何调用一个实体EJB,并进行如下操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpClientTest.java</p>
 * @author 杜江
 * @version 1.0
 */
public class bmpClientTest {
//声明变量
  private String url;
  private bmpTradeHome home;
//构造方法
  public bmpClientTest(String url)
    throws NamingException
  {
    this.url = url;
    //查找主接口，lookupHome是本例自定义方法。
    home = lookupHome();
  }
  /**
   * 在命令行运行这个实例：
   * java bmp.bmpClientTest "t3://localhost:7001"
   * 参数是可选的
   * @参数 url   URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws NamingException {
    System.out.println("\nBeginning bmp.bmpClientTest...\n");
    String url = "t3://localhost:7001";
    // 解析命令行参数,如果没有则使用默认的"t3://localhost:7001"
     if (args.length == 1) {
      url = args[0];
    }
    System.out.println("URL="+url);
    bmpClientTest bmpClientTest = null;
    try {
    	//实例化本类
      bmpClientTest = new bmpClientTest(url);
    } catch (NamingException ne) {
    	//异常处理
      log("Unable to look up the beans home: " + ne.getMessage());
      System.exit(1);
    }
    try {
    	//运行例程
      bmpClientTest.example();
} catch (Exception e) {
	//异常处理
      log("There was an exception while creating and using the Accounts.");
      log("This indicates that there was a problem communicating with the server: "+e);
    }
    System.out.println("\nEnd bmp.bmpClientTest...\n");
  }
//执行实例
  public void example()
    throws CreateException, RemoteException, FinderException,
           RemoveException
  {
    int numBeans = 10;
    //声明并创建账号数组
    bmpTrade[] bmpTrade = new bmpTrade[numBeans];
    //创建10个账号
    for (int i=1; i<numBeans; i++) {
      bmpTrade [i] = findOrCreateAccount(i+"", i * 200);
    }
    // 打印账号结算
    for (int i=1; i<numBeans; i++) {
      log("Account: :"+bmpTrade[i].getPrimaryKey()+" has a balance of "+bmpTrade[i].getBalance());
    }
    // 查找所有结算大于500的账号
    findBigAccounts(500.0);
    // 清除所有账号
    log("Removing beans...");
    for (int i=1; i<numBeans; i++) {
      bmpTrade[i].remove();
    }
  }
  /**
    * 列出所有结算大于给定值的账号
    * 这个finder方法演示返回枚举账号
    */
  private void findBigAccounts(double balanceGreaterThan)
    throws RemoteException, FinderException
  {
    log("\nQuerying for fund with a balance greater than "+balanceGreaterThan + "...");
    //调用主接口创建账号方法findBigAccounts，返回账号集合
    Collection col = home.findBigAccounts(balanceGreaterThan);
    if(col.isEmpty()) {
      log("No fund were found with a balance greater that "+balanceGreaterThan);
    }
    Iterator it = col.iterator();
    while (it.hasNext()) {
     //创建远程对象
      bmpTrade accountGT = (bmpTrade) PortableRemoteObject.narrow(it.next(),bmpTrade.class);
      //列出合乎要求的账户
      log("Account " + accountGT.getPrimaryKey() + "; fund is $" + accountGT.getBalance());
    }
  }
 //如果对应id的账号以存在，则返回这个id,否则创建它
  private bmpTrade findOrCreateAccount(String id, double balance)
    throws CreateException, RemoteException, FinderException
  {
    try {
      log("Trying to find account with id: "+id);
      return (bmpTrade) PortableRemoteObject.narrow(home.findByPrimaryKey(id),bmpTrade.class);
    } catch (ObjectNotFoundException onfe) {
      // 账号不存在，创建它
      return (bmpTrade) PortableRemoteObject.narrow(home.create(id, balance),bmpTrade.class);
    }
  }
 // 给定id和结算创建一个新的账号
   private bmpTrade createAccount(String id, double balance)
    throws CreateException, RemoteException
  {
    log("Creating account " + id + " with a balance of " +
      balance + "...");
      //创建远程账号对象
    bmpTrade ac = (bmpTrade) PortableRemoteObject.narrow(home.create(id, balance),bmpTrade.class);
    log("Account " + id + " successfully created");
    return ac;
  }
 //使用JNDI查找bean的主接口
 private bmpTradeHome lookupHome()
    throws NamingException
  {
    Context ctx = getInitialContext();
    try {
    	//查找主接口
      Object home = ctx.lookup("bmpbean");
      return (bmpTradeHome) PortableRemoteObject.narrow(home, bmpTradeHome.class);
	} catch (NamingException ne) {
	//异常处理
      log("The bmpClientTest was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " +
      "bmp on the WebLogic server at "+url);
      throw ne;
    }
  }
  //获取初始化上下文
   private Context getInitialContext() throws NamingException {
    try {
      // 设置属性对象
      Properties h = new Properties();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, url);
      return new InitialContext(h);
    } catch (NamingException ne) {
    	//异常处理
      log("We were unable to get a connection to the WebLogic server at "+url);
      log("Please make sure that the server is running.");
      throw ne;
    }
  }
//控制台输出
  private static void log(String s) {
    System.out.println(s);
  }
}
