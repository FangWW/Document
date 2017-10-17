package bmp;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * <p>Title: �ͻ���</p>
 * <p>Description: �������ʾ����ε���һ��ʵ��EJB,���������²���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpClientTest.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class bmpClientTest {
//��������
  private String url;
  private bmpTradeHome home;
//���췽��
  public bmpClientTest(String url)
    throws NamingException
  {
    this.url = url;
    //�������ӿڣ�lookupHome�Ǳ����Զ��巽����
    home = lookupHome();
  }
  /**
   * ���������������ʵ����
   * java bmp.bmpClientTest "t3://localhost:7001"
   * �����ǿ�ѡ��
   * @���� url   URL such as "t3://localhost:7001" of Server
   */
  public static void main(String[] args) throws NamingException {
    System.out.println("\nBeginning bmp.bmpClientTest...\n");
    String url = "t3://localhost:7001";
    // ���������в���,���û����ʹ��Ĭ�ϵ�"t3://localhost:7001"
     if (args.length == 1) {
      url = args[0];
    }
    System.out.println("URL="+url);
    bmpClientTest bmpClientTest = null;
    try {
    	//ʵ��������
      bmpClientTest = new bmpClientTest(url);
    } catch (NamingException ne) {
    	//�쳣����
      log("Unable to look up the beans home: " + ne.getMessage());
      System.exit(1);
    }
    try {
    	//��������
      bmpClientTest.example();
} catch (Exception e) {
	//�쳣����
      log("There was an exception while creating and using the Accounts.");
      log("This indicates that there was a problem communicating with the server: "+e);
    }
    System.out.println("\nEnd bmp.bmpClientTest...\n");
  }
//ִ��ʵ��
  public void example()
    throws CreateException, RemoteException, FinderException,
           RemoveException
  {
    int numBeans = 10;
    //�����������˺�����
    bmpTrade[] bmpTrade = new bmpTrade[numBeans];
    //����10���˺�
    for (int i=1; i<numBeans; i++) {
      bmpTrade [i] = findOrCreateAccount(i+"", i * 200);
    }
    // ��ӡ�˺Ž���
    for (int i=1; i<numBeans; i++) {
      log("Account: :"+bmpTrade[i].getPrimaryKey()+" has a balance of "+bmpTrade[i].getBalance());
    }
    // �������н������500���˺�
    findBigAccounts(500.0);
    // ��������˺�
    log("Removing beans...");
    for (int i=1; i<numBeans; i++) {
      bmpTrade[i].remove();
    }
  }
  /**
    * �г����н�����ڸ���ֵ���˺�
    * ���finder������ʾ����ö���˺�
    */
  private void findBigAccounts(double balanceGreaterThan)
    throws RemoteException, FinderException
  {
    log("\nQuerying for fund with a balance greater than "+balanceGreaterThan + "...");
    //�������ӿڴ����˺ŷ���findBigAccounts�������˺ż���
    Collection col = home.findBigAccounts(balanceGreaterThan);
    if(col.isEmpty()) {
      log("No fund were found with a balance greater that "+balanceGreaterThan);
    }
    Iterator it = col.iterator();
    while (it.hasNext()) {
     //����Զ�̶���
      bmpTrade accountGT = (bmpTrade) PortableRemoteObject.narrow(it.next(),bmpTrade.class);
      //�г��Ϻ�Ҫ����˻�
      log("Account " + accountGT.getPrimaryKey() + "; fund is $" + accountGT.getBalance());
    }
  }
 //�����Ӧid���˺��Դ��ڣ��򷵻����id,���򴴽���
  private bmpTrade findOrCreateAccount(String id, double balance)
    throws CreateException, RemoteException, FinderException
  {
    try {
      log("Trying to find account with id: "+id);
      return (bmpTrade) PortableRemoteObject.narrow(home.findByPrimaryKey(id),bmpTrade.class);
    } catch (ObjectNotFoundException onfe) {
      // �˺Ų����ڣ�������
      return (bmpTrade) PortableRemoteObject.narrow(home.create(id, balance),bmpTrade.class);
    }
  }
 // ����id�ͽ��㴴��һ���µ��˺�
   private bmpTrade createAccount(String id, double balance)
    throws CreateException, RemoteException
  {
    log("Creating account " + id + " with a balance of " +
      balance + "...");
      //����Զ���˺Ŷ���
    bmpTrade ac = (bmpTrade) PortableRemoteObject.narrow(home.create(id, balance),bmpTrade.class);
    log("Account " + id + " successfully created");
    return ac;
  }
 //ʹ��JNDI����bean�����ӿ�
 private bmpTradeHome lookupHome()
    throws NamingException
  {
    Context ctx = getInitialContext();
    try {
    	//�������ӿ�
      Object home = ctx.lookup("bmpbean");
      return (bmpTradeHome) PortableRemoteObject.narrow(home, bmpTradeHome.class);
	} catch (NamingException ne) {
	//�쳣����
      log("The bmpClientTest was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " +
      "bmp on the WebLogic server at "+url);
      throw ne;
    }
  }
  //��ȡ��ʼ��������
   private Context getInitialContext() throws NamingException {
    try {
      // �������Զ���
      Properties h = new Properties();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, url);
      return new InitialContext(h);
    } catch (NamingException ne) {
    	//�쳣����
      log("We were unable to get a connection to the WebLogic server at "+url);
      log("Please make sure that the server is running.");
      throw ne;
    }
  }
//����̨���
  private static void log(String s) {
    System.out.println(s);
  }
}
