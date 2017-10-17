package javahello;
import org.apache.struts.action.*;

import javax.servlet.http.*;
/**
 * �̳�Action��
 */
public final class SendMessageAction extends Action {
  public ActionForward perform(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    //����Beanȡ�ñ�����
    SendMessageForm sendMessageForm = (SendMessageForm)form;
    //��ȡ��������
    String name = sendMessageForm.getName();
    String email = sendMessageForm.getEmail();
    String message = sendMessageForm.getMessage();
    // ��������󣬸���"success"��ǣ���������Ӧ��ҳ�档
    // (��struts-config.xml�ļ������Ӧ�Ķ���)
    return (mapping.findForward("success"));
  }
}
