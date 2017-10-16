import java.io.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <p>Title: Servlet获取会话</p>
 * <p>Description: 这个简单的实例使用HttpSession类跟踪客户端访问的次数。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SessionServlet.java</p>
 * @author 杜江
 * @version 1.0
 */
//必须继承HttpServlet类
public class SessionServlet extends HttpServlet { 
  //doGet方法
  public void doGet (HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
  {
    //获取会话对象，true表示如果这个对象不存在，则创建新的
    HttpSession session = req.getSession(true);
    
    // 设置内容类型
    res.setContentType("text/html; charset=GB2312");
    
    // 获取PrintWriter对象
    PrintWriter out = res.getWriter();
    
    out.println("<HEAD><TITLE> " + "在Servlet中使用Session " +
                "</TITLE></HEAD><BODY>");
    out.println("<p>");
    out.println("<h1> Servlet中使用Session实例 </h1>");
    
    // 获取Session变量
    Integer ival = (Integer) session.getAttribute("counter");
    
    if (ival==null) 
      ival = new Integer(1);
    else 
      ival = new Integer(ival.intValue() + 1);
    //设置Session变量
    session.setAttribute("counter", ival);
    
    out.println("你刷新或点击这一页 <b>" + ival + "</b> 次.<p>");
    out.println("点击 <a href=" + res.encodeURL("SessionServlet") +
                ">这里</a>");
    out.println(" 使的更新你的Session信息 " );

    out.println("<p>");
    
    out.println("<h3>请求和会话数据:</h3>");
    //获得Session的id号
    out.println("Session ID in Request: " +
                req.getRequestedSessionId());
    //判断是否id好使用了cookie
    out.println("<br>Session ID in Request from Cookie: " +
                req.isRequestedSessionIdFromCookie());
    //Session是否从表单提交的
    out.println("<br>Session ID in Request from URL: " +
                req.isRequestedSessionIdFromURL());
    //当前Session是否激活
    out.println("<br>Valid Session ID: " +
                req.isRequestedSessionIdValid());
    out.println("<h3>Session信息:</h3>");
    //当前的Session是否首次建立
    out.println("New Session: " + session.isNew());
    //获得这个Session的ID号
    out.println("<br>Session ID: " + session.getId());
    //创建这个会话的时间
    out.println("<br>Creation Time: " + session.getCreationTime());
    //客户最后一次访问的时间
    out.println("<br>Last Accessed Time: " +
                session.getLastAccessedTime());
    
    out.println("</BODY></html>");
  }
  
}
