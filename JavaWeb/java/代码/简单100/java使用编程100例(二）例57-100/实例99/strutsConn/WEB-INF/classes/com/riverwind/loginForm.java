package com.riverwind;
import javax.servlet.http.*;
import org.apache.struts.action.*;
/**
 * 继承ActionForm类
 */
public final class loginForm extends ActionForm {
  private String name;
  private String pass;

  /*
  *方法说明：设置名称
  */
  public void setName(String name) {
    this.name = name;
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
  public void setPassword(String password) {
    this.pass = password;
  }
  /*
  *方法说明：获取邮件
  */
  public String getPassword() {
    return pass;
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
    if (pass == null || pass.equals("")) {
      errors.add("password", new ActionError("error.pass.required"));
    }

    return errors;
  }
}
