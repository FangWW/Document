package com.riverwind;

import org.apache.struts.action.*;

import javax.servlet.http.*;
/**
 * �̳�Action��
 */
public final class loginAction extends Action {
  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    //����Beanȡ�ñ�����
    loginForm login = (loginForm)form;
    //��ȡ��������
    String name = login.getName();
    String pass = login.getPassword();

    javax.sql.DataSource dataSource;
    java.sql.Connection conn =null;
    try{
      //��ȡ����Դ
      dataSource = getDataSource(request);
      //��ȡ���ݿ�����
      conn = dataSource.getConnection();
      String sql = "select * from tbl_user where name='"+name+"' and password='"+pass+"'";
      System.out.println("sql="+sql);
      Statement stmt = conn.createStatement();
      //ִ�����ݿ�sql����
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
	 {
	   return (mapping.findForward("success"));
	 }
   }catch(SQLException sqle){
      getServlet().log("Connection.process", sqle);
   }finally {
    //�ͷ���Դ
     try {
       conn.close();
     }catch (SQLException e) {
       getServlet().log("Connection.close", e);
     }
   }
    return (mapping.findForward("failes"));
  }
}
