package cmp;
import java.sql.Date;
import java.util.Properties;

import javax.rmi.PortableRemoteObject;
/**
 * <p>Title: �ͻ���</p>
 * <p>Description: ����CMP��ֱ�ӵ��õ��ǻỰEJB</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: userinfoTestClient.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class userinfoTestClient {
  private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
  private static final int MAX_OUTPUT_LINE_LENGTH = 100;
  private boolean logging = true;
  private userinfoHome userinfoHomeObject = null;
  private userinfo userinfoObject = null;
  private String url = null;
  //���� EJB �ͻ���
  public userinfoTestClient(String url) {
    long startTime = 0;
    if (logging) {
      log("Initializing bean access.");
      startTime = System.currentTimeMillis();
    }
    this.url=url;
    try {
      //����������
      Context ctx = getInitialContext();
      //��ѯuserinfo
      Object ref = ctx.lookup("userinfo");
      //����Home�˿�
      userinfoHomeObject = (userinfoHome) PortableRemoteObject.narrow(ref, userinfoHome.class);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded initializing bean access.");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed initializing bean access.");
      }
      e.printStackTrace();
    }
  }
//��ʼ��������
  private Context getInitialContext() throws Exception {
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
/*
*����˵��������Զ�̽ӿ�
*/
  public userinfo create() {
    long startTime = 0;
    if (logging) {
      log("Calling create()");
      startTime = System.currentTimeMillis();
    }
    try {
      userinfoObject = userinfoHomeObject.create();
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: create()");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: create()");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from create(): " + userinfoObject + ".");
    }
    return userinfoObject;
  }
/*
*����˵������Ӽ�¼
* @������String name �û���
* @������String phone ��ϵ�绰
* @������String home��ͥסַ
* @������Date birthday ����
*/
  public void ist_info(String name, String phone, String home, Date brithday) {
    if (userinfoObject == null) {
      System.out.println("Error in ist_info(): " + ERROR_NULL_REMOTE);
      return ;
    }
    long startTime = 0;
    if (logging) {
      log("Calling ist_info(" + name + ", " + phone + ", " + home + ", " + brithday + ")");
      startTime = System.currentTimeMillis();
    }
    try {
      userinfoObject.ist_info(name, phone, home, brithday);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: ist_info(" + name + ", " + phone + ", " + home + ", " + brithday + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: ist_info(" + name + ", " + phone + ", " + home + ", " + brithday + ")");
      }
      e.printStackTrace();
    }
  }
/*
*����˵����ɾ����¼
* @������Integer id����id
* @���أ�int 0��ʧ�� 1���ɹ� ��1������
*/
  public int del_info(Integer id) {
    int returnValue = 0;
    if (userinfoObject == null) {
      System.out.println("Error in del_info(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling del_info(" + id + ")");
      startTime = System.currentTimeMillis();
    }
    try {
      returnValue = userinfoObject.del_info(id);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: del_info(" + id + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: del_info(" + id + ")");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from del_info(" + id + "): " + returnValue + ".");
    }
    return returnValue;
  }
/*
*����˵�����޸ļ�¼
* @������Integer id ����id
* @������String name �û���
* @������String phone ��ϵ�绰
* @������String home��ͥסַ
* @������Date birthday ����
* @���أ�int 0��ʧ�� 1���ɹ� ��1������
*/
  public int up_info(Integer id, String name, String phone, String home, Date brithday) {
    int returnValue = 0;
    if (userinfoObject == null) {
      System.out.println("Error in up_info(): " + ERROR_NULL_REMOTE);
      return returnValue;
    }
    long startTime = 0;
    if (logging) {
      log("Calling up_info(" + id + ")");
      startTime = System.currentTimeMillis();
    }
    try {
      returnValue = userinfoObject.up_info(id,name,phone,home,brithday);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: up_info(" + id + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: up_info(" + id + ")");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from up_info(" + id + "): " + returnValue + ".");
    }
    return returnValue;
  }
/*
*����˵������ѯ��¼
* @������Integer id����id
* @���أ�Vector �û���Ϣ��
*/  
public java.util.Vector find_id(Integer id) {
    java.util.Vector returnValue = new java.util.Vector();
    if (userinfoObject == null) {
      System.out.println("Error in find_id(): " + ERROR_NULL_REMOTE);
      return null;
    }
    long startTime = 0;
    if (logging) {
      log("Calling find_id(" + id + ")");
      startTime = System.currentTimeMillis();
    }
    try {
      returnValue = userinfoObject.find_id(id);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: find_id(" + id + ")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: find_id(" + id + ")");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from find_id(" + id + "): " + returnValue + ".");
    }
    return returnValue;
  }
/*
*����˵������ѯ��¼
* @������
* @���أ�
*/  
public int findUser(String username) {
    int returnValue=-1 ;
    if (userinfoObject == null) {
      System.out.println("Error in find_id(): " + ERROR_NULL_REMOTE);
      return -1;
    }
    long startTime = 0;
    if (logging) {
      log("Calling findUser("+username+")");
      startTime = System.currentTimeMillis();
    }
    try {
      returnValue = userinfoObject.findUser(username);
      if (logging) {
        long endTime = System.currentTimeMillis();
        log("Succeeded: findUser("+username+")");
        log("Execution time: " + (endTime - startTime) + " ms.");
      }
    }
    catch(Exception e) {
      if (logging) {
        log("Failed: findUser("+username+")");
      }
      e.printStackTrace();
    }
    if (logging) {
      log("Return value from findUser("+username+"): " + returnValue + ".");
    }
    return returnValue;
  }
//��ʾ��Ϣ
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
    String url       = "t3://localhost:7001";         
    // ���������в���
     if (args.length != 1) {
      System.out.println("Usage: java cmp.userinfoTestClient t3://hostname:port");   
      return;
    } else {
      url = args[0];
    }
    userinfoTestClient client = new userinfoTestClient(url);
    client.create();
    //����û���Ϣ
    client.ist_info("����","010-88669977","�����йش�",java.sql.Date.valueOf("1965-09-18")) ;
    //�����¼����
    java.lang.Integer  temp = new Integer(client.findUser("����")); //Integer.valueOf("1");
    java.util.Vector vTemp  = client.find_id(temp) ;
    client.up_info(temp,"����","0316-65845878","�ӱ��ȷ�",java.sql.Date.valueOf("1965-12-9")) ;
    //ɾ���û�
    client.del_info(temp);
 }
}
