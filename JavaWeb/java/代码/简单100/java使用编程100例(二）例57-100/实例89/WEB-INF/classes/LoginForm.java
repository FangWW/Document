
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * <p>Title: 使用servlet输出Html页面</p>
 * <p>Description: HelloWorld3实例书写html页面，初始化参数定义在web.xml文件中</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: LoginForm.java</p>
 * @author 杜江
 * @version 1.0
 */
//必须继承HttpServlet类
public class LoginForm extends HttpServlet {
  
  String defaultGreeting;
  String defaultName;
  String ICO1;
  String ICO2;
  String ICO3;
  String ICO4;
  //初始化
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
    //获取参数
    if ((defaultName = getInitParameter("name")) == null)
      defaultName = "Java";
    if ((defaultGreeting = getInitParameter("message")) == null)
      defaultGreeting = "Hello World!";
    if ((ICO1 = getInitParameter("ICO1")) == null)
      ICO1 = "images/commend4.gif";
    if ((ICO2 = getInitParameter("ICO2")) == null)
      ICO2 = "images/commend.gif";
    if ((ICO3 = getInitParameter("ICO3")) == null)
      ICO3 = "images/commend1.gif";
    if ((ICO4 = getInitParameter("ICO4")) == null)
      ICO4 = "images/commend3.gif";
  
  }
/**
 *<br>方法说明：生成HTML页面
 *<br>输入参数：
 *<br>返回类型：
 */
  public void service(HttpServletRequest req, HttpServletResponse res)
       throws IOException
  {
    // 首先设置文档类型
    res.setContentType("text/html; charset=GB2312");
    //接收参数
    String name = req.getParameter("name");
    String pwd = req.getParameter("pass");
    // 获取输出流
    PrintWriter out = res.getWriter();
    //书写页面
    out.println("<html><head><title>欢迎进入Java世界</title></head>");
    out.println("<body>");
    out.println("<h1>");
    out.println(defaultName + " " +  defaultGreeting + "!");
    out.println("</h1>");
    out.println("<img src=\""+ICO1+"\">");
    out.println("<img src=\""+ICO2+"\">");
    out.println("<img src=\""+ICO3+"\">");
    out.println("<img src=\""+ICO4+"\">");
    out.println("<p>欢迎"+name+"登陆！");
    out.println("</body></html>");
  }
}
