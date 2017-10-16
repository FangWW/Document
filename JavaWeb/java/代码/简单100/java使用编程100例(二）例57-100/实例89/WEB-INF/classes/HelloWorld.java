
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * <p>Title: 使用servlet输出Html页面</p>
 * <p>Description: HelloWorld实例书写html页面，初始化参数定义在web.xml文件中</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: HelloWorld.java</p>
 * @author 杜江
 * @version 1.0
 */
//必须继承HttpServlet类
public class HelloWorld extends HttpServlet {
  
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
    // 获取输出流
    PrintWriter out = res.getWriter();
    //书写页面
    out.println("<html><head><title>欢迎进入Java世界</title></head>");
    out.println("<body>");
    out.println("<h1>");
    out.println(defaultName + " " +  defaultGreeting + "!");
    out.println("</h1>");
    out.println("<ul>");
    out.println("<li><img src=\""+ICO1+"\">");
    out.println("<li><img src=\""+ICO2+"\">");
    out.println("<li><img src=\""+ICO3+"\">");
    out.println("<li><img src=\""+ICO4+"\">");
    out.println("</ul>");
    out.println("</body></html>");
  }
}
