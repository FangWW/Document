package cmp;
import javax.ejb.*;

/**
 * <p>Title: CMP实现类程序</p>
 * <p>Description: CMP实现类，必需实现EntityBean</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: TblUserInfoBean.java</p>
 * @author 杜江
 * @version 1.0
 */
public abstract class TblUserInfoBean implements EntityBean {
  EntityContext entityContext;
/*功能说明：必需实现的方法。与TblUserInfoHome中Create方法对应。
*这两个方法参数相同。当客户端调用"TblUserInfoHome.create()"方法时，
*EJB找到相应的实例并调用ejbCreate方法。
*对于CMP，ejbCreate将返回null，对于bean自管理EJB来说，则返回主键类型。
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
* @异常：CreateException 创建EJB错误时抛出
*/
  public java.lang.Integer ejbCreate(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException {
    setName(name);
    setPhone(phone);
    setHome(home);
    setBrithday(brithday);
    return null;
  }
//EJB必需实现的方法，本例中没有使用。
  public void ejbPostCreate(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException {  }
//EJB必需实现的方法。实现移除数据。
  public void ejbRemove() throws RemoveException { }
//setXXX方法，和数据库字段对应，提供对数据的更新
  public abstract void setId(java.lang.Integer id);
  public abstract void setName(java.lang.String name);
  public abstract void setPhone(java.lang.String phone);
  public abstract void setHome(java.lang.String home);
  public abstract void setBrithday(java.sql.Date brithday);
//getXXX方法，和数据库字段对应，提供数据的提取
  public abstract java.sql.Date getBrithday();
  public abstract java.lang.String getHome();
  public abstract java.lang.String getPhone();
  public abstract java.lang.String getName();
  public abstract java.lang.Integer getId();
// EJB必需实现的方法。
  public void ejbLoad() { }
// EJB必需实现的方法。
  public void ejbStore() { }
// EJB必需实现的方法。
  public void ejbActivate() { }
// EJB必需实现的方法。
  public void ejbPassivate() { }
// EJB必需实现的方法。清除实体上下文
  public void unsetEntityContext() {
    this.entityContext = null;
  }
// EJB必需实现的方法。设置实体上下文
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }
}
