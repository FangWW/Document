package javahello;
import javax.servlet.http.*;
import org.apache.struts.action.*;
/**
 * 继承ActionForm类
 */
public final class SendMessageForm extends ActionForm {
  private String name;
  private String email;
  private String message;
  /*
  *方法说明：设置名称
  */
  public void setName(String name) {
    this.name = toSJIS(name);
  }
  /*
  *方法说明：获取名称
  */
  public String getName() {
    return name;
  }
  /*
  *方法说明：设置邮件
  */
  public void setEmail(String email) {
    this.email = toSJIS(email);
  }
  /*
  *方法说明：获取邮件
  */
  public String getEmail() {
    return email;
  }
  /*
  *方法说明：设置信息
  */
  public void setMessage(String message) {
    this.message = toSJIS(message);
  }
  /*
  *方法说明：获取信息
  */
  public String getMessage() {
    return message;
  }
  /*
  *方法说明：提交数据前校验
  */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    //实例化一个ActionErrors，用于储存错误
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
   *方法说明： iso-8859-1转换成GB2312
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
