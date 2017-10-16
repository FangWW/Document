package com.riverwind;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;
/**
 * 继承Action类
 */
public final class loginAction extends Action {
  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    //定义Bean取得变量类
    loginForm login = (loginForm)form;
    //获取变量参数
    String name = login.getName();
    String pass = login.getPassword();

    javax.sql.DataSource dataSource;
    java.sql.Connection conn =null;
    try{
      //获取数据源
      dataSource = getDataSource(request);
      //获取数据库连接
      conn = dataSource.getConnection();
      String sql = "select * from tbl_user where name='"+name+"' and password='"+pass+"'";
      System.out.println("sql="+sql);
      Statement stmt = conn.createStatement();
      //执行数据库sql命令
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
	 {
	   return (mapping.findForward("success"));
	 }
   }catch(SQLException sqle){
      getServlet().log("Connection.process", sqle);
   }finally {
    //释放资源
     try {
       conn.close();
     }catch (SQLException e) {
       getServlet().log("Connection.close", e);
     }
   }
    return (mapping.findForward("failes"));
  }
}
