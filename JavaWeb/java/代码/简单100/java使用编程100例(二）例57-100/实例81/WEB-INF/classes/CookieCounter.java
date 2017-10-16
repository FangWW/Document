
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: servlet读取cookie</p>
 * <p>Description: 这个servlet演示怎样创建和获取cookie并设置cookie的期限</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CookieCounter.java</p>
 * @author 杜江
 * @version 1.0
 */
//必须继承HttpServlet类
public class CookieCounter extends HttpServlet {
  private int pageCount = 0;

/**
 *<br>方法说明：初始化
 *<br>输入参数：ServletConfig config 服务器配置对象
 *<br>返回类型：
 */

  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
  }
/**
 *<br>方法说明：实现service方法
 *<br>输入参数：HttpServletRequest req 客户请求对象
 *<br>输入参数：HttpServletResponse res 服务器应答对象
 *<br>返回类型：
 */
  public void service(HttpServletRequest req, HttpServletResponse res)
       throws IOException
  {
    boolean cookieFound = false;
    Cookie thisCookie = null;
    
    // 设置内容类型
    res.setContentType("text/html; charset=GB2312");
    // 调用getWriter()
    PrintWriter out = res.getWriter();
    
    // 从请求获取coolies
    Cookie[] cookies = req.getCookies();
    
    if(cookies!=null){
      for(int i=0; i < cookies.length; i++) {
        thisCookie = cookies[i];
        //检查是否存在CookieCount数据
        if (thisCookie.getName().equals("CookieCount")) {
          cookieFound = true;
          break;
        }
      }
    }
    if (cookieFound == false) {
      // 创建新的Cookie并设置它的存活期
      thisCookie = new Cookie("CookieCount", "1");
      thisCookie.setMaxAge(60*1);
      // 在response对象中加入cookie
      res.addCookie(thisCookie);
    }
    //输出页面
    out.println("<html><head>\n" + "<title>Cookie计数器</title></head><body>\n" +
                "<center><h1>Cookie 计数器</h1></center></font>");
    pageCount++;
    out.println("<p>");
    out.println("<font color=blue size=+1>");
    out.println("<p><br><br><br>这个页面您已经拜访了 " + pageCount + 
                " 次.\n");
    
    // 显示客户端详细信息,是否存在计数器cookie
    if (cookieFound) {
      int cookieCount = Integer.parseInt(thisCookie.getValue());
      cookieCount++;
      // 设置cookie的新值, 加到相应对象中
      thisCookie.setValue(String.valueOf(cookieCount));
      thisCookie.setMaxAge(10);
      res.addCookie(thisCookie);
      
      out.println("<p>这是你近10秒内第 " +
                  thisCookie.getValue() +
                  " 次拜访这一页\n");
      
    } else {
      out.println("<p>你在近10秒内没有拜访过此页或者你的浏览器不支持cookie "+
                  "如果你的浏览器支持cookie，请确认是否打开了！\n");
    }
    out.println("</body></html>");
    
  }
}

