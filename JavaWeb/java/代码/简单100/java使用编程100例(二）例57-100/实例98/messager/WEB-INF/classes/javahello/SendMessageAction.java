package javahello;
import javax.servlet.http.*;
import org.apache.struts.action.*;
/**
 * 继承Action类
 */
public final class SendMessageAction extends Action {
  public ActionForward perform(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    //定义Bean取得变量类
    SendMessageForm sendMessageForm = (SendMessageForm)form;
    //获取变量参数
    String name = sendMessageForm.getName();
    String email = sendMessageForm.getEmail();
    String message = sendMessageForm.getMessage();
    // 当处理完后，根据"success"标记，跳传到相应的页面。
    // (和struts-config.xml文件中相对应的定义)
    return (mapping.findForward("success"));
  }
}
