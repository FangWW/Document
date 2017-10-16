package cmp;
import javax.ejb.*;
import java.util.*;

/**
 * <p>Title: CMP远程接口</p>
 * <p>Description: 必需继承javax.ejb.EJBLocalObject和表字段相互对应有setXXX()和getXXX()方法</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
public interface TblUserInfo extends javax.ejb.EJBLocalObject {
  //获取用户主键
  public java.lang.Integer getId();
  //设置用户名
  public void setName(java.lang.String name);
  //获取用户名
  public java.lang.String getName();
  //设置电话
  public void setPhone(java.lang.String phone);
  //获取电话
  public java.lang.String getPhone();
  //设置家庭住址
  public void setHome(java.lang.String home);
  //获取家庭住址
  public java.lang.String getHome();
  //设置生日
  public void setBrithday(java.sql.Date brithday);
  //获取生日
  public java.sql.Date getBrithday();
}
