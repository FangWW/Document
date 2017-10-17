import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <p>Title: Servlet��ȡ�Ự</p>
 * <p>Description: ����򵥵�ʵ��ʹ��HttpSession����ٿͻ��˷��ʵĴ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SessionServlet.java</p>
 * @author �Ž�
 * @version 1.0
 */
//����̳�HttpServlet��
public class SessionServlet extends HttpServlet { 
  //doGet����
  public void doGet (HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
  {
    //��ȡ�Ự����true��ʾ���������󲻴��ڣ��򴴽��µ�
    HttpSession session = req.getSession(true);
    
    // ������������
    res.setContentType("text/html; charset=GB2312");
    
    // ��ȡPrintWriter����
    PrintWriter out = res.getWriter();
    
    out.println("<HEAD><TITLE> " + "��Servlet��ʹ��Session " +
                "</TITLE></HEAD><BODY>");
    out.println("<p>");
    out.println("<h1> Servlet��ʹ��Sessionʵ�� </h1>");
    
    // ��ȡSession����
    Integer ival = (Integer) session.getAttribute("counter");
    
    if (ival==null) 
      ival = new Integer(1);
    else 
      ival = new Integer(ival.intValue() + 1);
    //����Session����
    session.setAttribute("counter", ival);
    
    out.println("��ˢ�»�����һҳ <b>" + ival + "</b> ��.<p>");
    out.println("��� <a href=" + res.encodeURL("SessionServlet") +
                ">����</a>");
    out.println(" ʹ�ĸ������Session��Ϣ " );

    out.println("<p>");
    
    out.println("<h3>����ͻỰ����:</h3>");
    //���Session��id��
    out.println("Session ID in Request: " +
                req.getRequestedSessionId());
    //�ж��Ƿ�id��ʹ����cookie
    out.println("<br>Session ID in Request from Cookie: " +
                req.isRequestedSessionIdFromCookie());
    //Session�Ƿ�ӱ��ύ��
    out.println("<br>Session ID in Request from URL: " +
                req.isRequestedSessionIdFromURL());
    //��ǰSession�Ƿ񼤻�
    out.println("<br>Valid Session ID: " +
                req.isRequestedSessionIdValid());
    out.println("<h3>Session��Ϣ:</h3>");
    //��ǰ��Session�Ƿ��״ν���
    out.println("New Session: " + session.isNew());
    //������Session��ID��
    out.println("<br>Session ID: " + session.getId());
    //��������Ự��ʱ��
    out.println("<br>Creation Time: " + session.getCreationTime());
    //�ͻ����һ�η��ʵ�ʱ��
    out.println("<br>Last Accessed Time: " +
                session.getLastAccessedTime());
    
    out.println("</BODY></html>");
  }
  
}
