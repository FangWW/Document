package bmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>Title: ʵ����</p>
 * <p>Description: EJBean�����JDBC�����Թ�����������������ļ��еĴ���ֱ�ӷ������ݿ⣻</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTradeBean.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class bmpTradeBean implements EntityBean {
//�����Ƿ��ӡ����̨
  final static private boolean VERBOSE = true;
//����ʵ�������ı���
  private EntityContext ctx;
  private String FundId;
  private double baseFunds;
  /**
   * ΪEJBean����ʵ��EJB������
   * @���� ctx    EntityContext
   */
  public void setEntityContext(EntityContext ctx) {
    log("setEntityContext called");
    this.ctx = ctx;
  }
 //ȡ��ʵ������������
 public void unsetEntityContext() {
    log("unsetEntityContext (" + id() + ")");
    this.ctx = null;
  }
  //���Ǳ������ʵ�ֵķ������ڱ�����û���õ�
   public void ejbActivate() {
    log("ejbActivate (" + id() + ")");
  }
 //���Ǳ������ʵ�ֵķ������ڱ�����û���õ�
  public void ejbPassivate() {
    log("ejbPassivate (" + id() + ")");
  }
   /**
   * �����ݿ��м���EJB
   * @�쳣  javax.ejb.NoSuchEntityException ��������ݿ���û���ҵ�Bean
   * @�쳣    javax.ejb.EJBException    ͨѶ��ϵͳ����
   */
  public void ejbLoad() {
    log("ejbLoad: (" + id() +  ")");
    //�������ݿ����Ӷ���
    Connection con = null;
    //����SQL����Ԥ�������
    PreparedStatement ps = null;
    //�ҵ��˺�����
    FundId = (String) ctx.getPrimaryKey();
    try {
    	//��ȡ���ݿ�����
      con = getConnection();
      //����SQL�����ȡ��¼
      ps  = con.prepareStatement("select fund from tbl_Funds where id = ?");
      ps.setString(1, FundId);
      //ִ��SQL
      ps.executeQuery();
      //��ȡSQL���
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
      	//ȡ������
        baseFunds = rs.getDouble(1);
      } else {
        String error = "ejbLoad: beansTeadeBean (" + FundId + ") not found !";
        log(error);
        throw new NoSuchEntityException (error);
       }
    } catch (SQLException sqe) {
    	//���ݿ��쳣����
      log("SQLException:  " + sqe);
      throw new EJBException(sqe);
    } finally {
      cleanup(con, ps);
    }
  }
 /**
   * ���ݿ��д���EJBean
   * @�쳣   javax.ejb.NoSuchEntityException     ��������ݿ���û���ҵ�Bean
   * @�쳣   javax.ejb.EJBException     ͨѶ��ϵͳ����
   */
  public void ejbStore() {
    log("ejbStore (" + id() + ")");
    //�������ݿ����Ӷ���
    Connection con = null;
    //����SQL����Ԥ�������
    PreparedStatement ps = null;
    try {
     //��ȡ���ݿ�����
      con = getConnection();
      //����SQL����,�������ݿ�
      ps = con.prepareStatement("update tbl_Funds set fund = ? where id = ?");
      ps.setDouble(1, baseFunds);
      ps.setString(2, FundId);
      //ִ��SQL
      if (!(ps.executeUpdate() > 0)) {
        String error = "ejbStore: bmpTradeBean (" + FundId + ") not updated !";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch(SQLException sqe) {
    	//���ݿ�β����쳣����
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }
  }
  /**
   * ���������"TblUserInfoBean.java"�ж���ĵ�Bean��"ejbCreate"�������Ӧ
   * �����������Ĳ���Ӧ����ͬ�����ͻ��˵���"TblUserInfoHome.create()"����ʱ��EJB����
   * ���ҵ�EJBean��ʵ��������������"ejbCreate()"������
   * �����������ejb,ejbCreate��������Ϊnull����bean�����ejb�����ص��������ࡣ
   * @���� FundId         String �˺�ID
   * @���� initialbaseFunds    double ��ʼ������ֵ
   * @�쳣 javax.ejb.CreateException   ����bean����ʱ�׳����쳣
   */
  public String ejbCreate(String FundId, double initialbaseFunds)
    throws CreateException
  {
  	//��־��Ϣ
    log("bmpTradeBean.ejbCreate( id = " +FundId+ ", " + "initial baseFunds = $ " + initialbaseFunds + ")");
    this.FundId = FundId;
    this.baseFunds = initialbaseFunds;
    //�������ݿ�����
    Connection con = null;
    PreparedStatement ps = null;
    try {
      //��ȡ���ݿ�����
      con = getConnection();
      //ִ��sql��䣬�����¼
      ps = con.prepareStatement("insert into tbl_Funds (id, fund) values (?, ?)");
      ps.setString(1, FundId);
      ps.setDouble(2, baseFunds);
      if (ps.executeUpdate() != 1) {
        String error = "JDBC did not create any row";
        log(error);
        throw new CreateException (error);
      }
      log("JDBC create one row!");
      return FundId;
    } catch (SQLException sqe) {
      ///�쳣����
      try {
      	//��������
        ejbFindByPrimaryKey(FundId);
      } catch(ObjectNotFoundException onfe) {
        String error = "SQLException: " + sqe;
        log(error);
        throw new CreateException (error);
      }
      String error = "An Account already exists in the database with Primary Key " + FundId;
      log(error);
      throw new DuplicateKeyException(error);
    } finally {
      cleanup(con, ps);
    }
  }
  //���Ǳ������ʵ�ֵķ������ڱ�����û���õ�
 public void ejbPostCreate(String FundId, double initialbaseFunds) {
    log("ejbPostCreate (" + id() + ")");
  }
  /**
   * �����ݿ���ɾ��EJBean
   * @�쳣  javax.ejb.NoSuchEntityException   ������ݿ���û�ҵ����ejb
   * @�쳣  javax.ejb.EJBException     ͨ�Ŵ����׳����쳣
   */
  public void ejbRemove() {
    log("ejbRemove (" + id() + ")");
    //�������ݿ�����
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//��ȡ����
      con = getConnection();
      //��ȡ����
      FundId = (String) ctx.getPrimaryKey();
      //ִ��SQL��䣬ɾ����¼
      ps = con.prepareStatement("delete from tbl_Funds where id = ?");
      ps.setString(1, FundId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "bmpTradeBean (" + FundId + " not found";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch (SQLException sqe) {
      //�쳣����
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
     //���
      cleanup(con, ps);
    }
  }
  /**
   * ������������EJBean
   * @���� pk    String ����
   * @�쳣  javax.ejb.ObjectNotFoundException   EJBeanû�����׳����쳣
   * @�쳣  javax.ejb.EJBException  ϵͳ����ͨѶ����ʱ�׳�
   */
  public String ejbFindByPrimaryKey(String pk)
    throws ObjectNotFoundException
  {
    log("ejbFindByPrimaryKey (" + pk + ")");
    //�������ݿ�����
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//��ȡ����
      con = getConnection();
      //��ѯ������Ӧ�ļ�¼
      ps  = con.prepareStatement("select fund from tbl_Funds where id = ?");
      ps.setInt(1, Integer.parseInt(pk));
      ps.executeQuery();
      //��ȡ�����
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        baseFunds = rs.getDouble(1);
      } else {
      	//û�з����������ֵ��ejb
        String error = "ejbFindByPrimaryKey: beansTeadeBean (" + pk + ") not found";
        log(error);
        throw new ObjectNotFoundException (error);
       }
    } catch (SQLException sqe) {
    	//�쳣����
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
    	//���
      cleanup(con, ps);
    }
    log("ejbFindByPrimaryKey (" + pk + ") found");
    return pk;
  }
  /**
   * �������н�����ڸ���ֵ��EJBeans
   * @���� baseFundsGreaterThan double �˻��ʽ�
   * @����  Collection
   * @�쳣  javax.ejb.EJBException    ͨ�Ŵ����׳����쳣
   */
  public Collection ejbFindBigAccounts(double baseFundsGreaterThan) {
    log("ejbFindBigAccounts (baseFunds > " + baseFundsGreaterThan + ")");
    //�������ݿ�����
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//��ȡ����
      con = getConnection();
      ps = con.prepareStatement("select id from tbl_Funds where fund > ?");
      ps.setDouble(1, baseFundsGreaterThan);
      ps.executeQuery();
      //��ȡ�����
      ResultSet rs = ps.getResultSet();
      Vector v = new Vector();
      String pk;
      while (rs.next()) {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      //���ؼ���
      return v;
    } catch (SQLException sqe) {
    	//�쳣����
      log("SQLException: " + sqe);
      throw new EJBException (sqe);
    } finally {
    	//���
      cleanup(con, ps);
    }
  }
/*
*����˵��������ʽ�
* @������baseFunds �ʽ���
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����ʱ
*/
  public void addFunds(double baseFunds) throws Exception {
    if (baseFunds<0)
        throw new Exception("Invalid baseFunds");
    this.baseFunds+=baseFunds;
  }
/*
*����˵������ȡ�ʽ�
* @������baseFunds �ʽ���
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����������ȡ�ʽ𳬹��˻����ʽ�ʱ
*/
  public void removeFunds(double baseFunds) throws Exception {
    if(baseFunds<0)
        throw new Exception("Invalid baseFunds");
    if(this.baseFunds<baseFunds)
        throw new Exception("the baseFunds less than baseFunds");
   this.baseFunds-=baseFunds;
  }
/*
*����˵������ѯ�˻��ʽ���
* @���أ�double �ʽ���
*/
  public double getBalance() {
    return this.baseFunds;
  }
  /**
   * �����ӳ��л�ȡ��ǰ����
   * @����   ����
   * @�쳣  javax.ejb.EJBException   ͨ�Ŵ���
   */
  private Connection getConnection()
    throws SQLException
  {
  	//������ʼ��������
    InitialContext initCtx = null;
    try {
      initCtx = new InitialContext();
      //��������Դ
      DataSource ds = (javax.sql.DataSource)
        initCtx.lookup("java:comp/env/jdbc/myDB");
        //��������Դ����
      return ds.getConnection();
    } catch(NamingException ne) {
    	//���쳣
      log("UNABLE to get a connection from myDB!");
      log("Please make sure that you have setup the connection pool properly");
      throw new EJBException(ne);
    } finally {
      try {
        if(initCtx != null) initCtx.close();
      } catch(NamingException ne) {
        log("Error closing context: " + ne);
        throw new EJBException(ne);
      }
    }
  }
  // Ҳ����ʹ��WebLogic����־����
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  }
  // �������beans��id
  private String id() {
    return "PK = " + (String) ctx.getPrimaryKey();
  }
  //�������
  private void cleanup(Connection con, PreparedStatement ps) {
    try {
      if (ps != null) ps.close();
    } catch (Exception e) {
      log("Error closing PreparedStatement: "+e);
      throw new EJBException (e);
    }
    try {
      if (con != null) con.close();
    } catch (Exception e) {
      log("Error closing Connection: " + e);
      throw new EJBException (e);
    }
  }
}
