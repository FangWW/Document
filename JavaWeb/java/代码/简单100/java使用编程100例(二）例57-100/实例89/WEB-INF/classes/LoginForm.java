
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <p>Title: ʹ��servlet���Htmlҳ��</p>
 * <p>Description: HelloWorld3ʵ����дhtmlҳ�棬��ʼ������������web.xml�ļ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: LoginForm.java</p>
 * @author �Ž�
 * @version 1.0
 */
//����̳�HttpServlet��
public class LoginForm extends HttpServlet {
  
  String defaultGreeting;
  String defaultName;
  String ICO1;
  String ICO2;
  String ICO3;
  String ICO4;
  //��ʼ��
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
    //��ȡ����
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
 *<br>����˵��������HTMLҳ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void service(HttpServletRequest req, HttpServletResponse res)
       throws IOException
  {
    // ���������ĵ�����
    res.setContentType("text/html; charset=GB2312");
    //���ղ���
    String name = req.getParameter("name");
    String pwd = req.getParameter("pass");
    // ��ȡ�����
    PrintWriter out = res.getWriter();
    //��дҳ��
    out.println("<html><head><title>��ӭ����Java����</title></head>");
    out.println("<body>");
    out.println("<h1>");
    out.println(defaultName + " " +  defaultGreeting + "!");
    out.println("</h1>");
    out.println("<img src=\""+ICO1+"\">");
    out.println("<img src=\""+ICO2+"\">");
    out.println("<img src=\""+ICO3+"\">");
    out.println("<img src=\""+ICO4+"\">");
    out.println("<p>��ӭ"+name+"��½��");
    out.println("</body></html>");
  }
}
