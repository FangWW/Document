
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: servlet��ȡcookie</p>
 * <p>Description: ���servlet��ʾ���������ͻ�ȡcookie������cookie������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CookieCounter.java</p>
 * @author �Ž�
 * @version 1.0
 */
//����̳�HttpServlet��
public class CookieCounter extends HttpServlet {
  private int pageCount = 0;

/**
 *<br>����˵������ʼ��
 *<br>���������ServletConfig config ���������ö���
 *<br>�������ͣ�
 */

  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
  }
/**
 *<br>����˵����ʵ��service����
 *<br>���������HttpServletRequest req �ͻ��������
 *<br>���������HttpServletResponse res ������Ӧ�����
 *<br>�������ͣ�
 */
  public void service(HttpServletRequest req, HttpServletResponse res)
       throws IOException
  {
    boolean cookieFound = false;
    Cookie thisCookie = null;
    
    // ������������
    res.setContentType("text/html; charset=GB2312");
    // ����getWriter()
    PrintWriter out = res.getWriter();
    
    // �������ȡcoolies
    Cookie[] cookies = req.getCookies();
    
    if(cookies!=null){
      for(int i=0; i < cookies.length; i++) {
        thisCookie = cookies[i];
        //����Ƿ����CookieCount����
        if (thisCookie.getName().equals("CookieCount")) {
          cookieFound = true;
          break;
        }
      }
    }
    if (cookieFound == false) {
      // �����µ�Cookie���������Ĵ����
      thisCookie = new Cookie("CookieCount", "1");
      thisCookie.setMaxAge(60*1);
      // ��response�����м���cookie
      res.addCookie(thisCookie);
    }
    //���ҳ��
    out.println("<html><head>\n" + "<title>Cookie������</title></head><body>\n" +
                "<center><h1>Cookie ������</h1></center></font>");
    pageCount++;
    out.println("<p>");
    out.println("<font color=blue size=+1>");
    out.println("<p><br><br><br>���ҳ�����Ѿ��ݷ��� " + pageCount + 
                " ��.\n");
    
    // ��ʾ�ͻ�����ϸ��Ϣ,�Ƿ���ڼ�����cookie
    if (cookieFound) {
      int cookieCount = Integer.parseInt(thisCookie.getValue());
      cookieCount++;
      // ����cookie����ֵ, �ӵ���Ӧ������
      thisCookie.setValue(String.valueOf(cookieCount));
      thisCookie.setMaxAge(10);
      res.addCookie(thisCookie);
      
      out.println("<p>�������10���ڵ� " +
                  thisCookie.getValue() +
                  " �ΰݷ���һҳ\n");
      
    } else {
      out.println("<p>���ڽ�10����û�аݷù���ҳ��������������֧��cookie "+
                  "�����������֧��cookie����ȷ���Ƿ���ˣ�\n");
    }
    out.println("</body></html>");
    
  }
}

