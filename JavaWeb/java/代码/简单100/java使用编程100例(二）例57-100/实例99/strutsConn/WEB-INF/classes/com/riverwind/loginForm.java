package com.riverwind;
import org.apache.struts.action.*;

import javax.servlet.http.*;
/**
 * �̳�ActionForm��
 */
public final class loginForm extends ActionForm {
  private String name;
  private String pass;

  /*
  *����˵������������
  */
  public void setName(String name) {
    this.name = name;
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
  public void setPassword(String password) {
    this.pass = password;
  }
  /*
  *����˵������ȡ�ʼ�
  */
  public String getPassword() {
    return pass;
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
    if (pass == null || pass.equals("")) {
      errors.add("password", new ActionError("error.pass.required"));
    }

    return errors;
  }
}
