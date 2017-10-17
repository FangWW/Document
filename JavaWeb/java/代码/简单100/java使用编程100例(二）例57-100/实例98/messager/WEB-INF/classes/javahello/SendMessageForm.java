package javahello;
import org.apache.struts.action.*;

import javax.servlet.http.*;
/**
 * �̳�ActionForm��
 */
public final class SendMessageForm extends ActionForm {
  private String name;
  private String email;
  private String message;
  /*
  *����˵������������
  */
  public void setName(String name) {
    this.name = toSJIS(name);
  }
  /*
  *����˵������ȡ����
  */
  public String getName() {
    return name;
  }
  /*
  *����˵���������ʼ�
  */
  public void setEmail(String email) {
    this.email = toSJIS(email);
  }
  /*
  *����˵������ȡ�ʼ�
  */
  public String getEmail() {
    return email;
  }
  /*
  *����˵����������Ϣ
  */
  public void setMessage(String message) {
    this.message = toSJIS(message);
  }
  /*
  *����˵������ȡ��Ϣ
  */
  public String getMessage() {
    return message;
  }
  /*
  *����˵�����ύ����ǰУ��
  */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    //ʵ����һ��ActionErrors�����ڴ������
    ActionErrors errors = new ActionErrors();
    if (name == null || name.equals("")) {
      errors.add("name" , new ActionError("error.name.required"));
    }
    if (email == null || email.equals("")) {
      errors.add("email", new ActionError("error.email.required"));
    }
    if (message == null || message.equals("")) {
      errors.add("message" , new ActionError("error.message.required"));
    }
    return errors;
  }

  /**
   *����˵���� iso-8859-1ת����GB2312
   */
  private String toSJIS(String str) {
    try {
      str = new String(str.getBytes("iso-8859-1"), "GB2312");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }
}
