package cmp;
import javax.ejb.*;
import java.util.*;
/**
 * <p>Title: CMP主接口</p>
 * <p>Description: 必需继承javax.ejb.EJBLocalHome </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: TblUserInfoHome.java</p>
 * @author 杜江
 * @version 1.0
 */
public interface TblUserInfoHome extends javax.ejb.EJBLocalHome {
/*功能说明：必需实现的方法。与TblUserInfoBean中ejbCreate方法对应。
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
* @异常：CreateException 创建EJB错误时抛出
*/
  public TblUserInfo create(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException;
/*功能说明：根据主键对象，返回用户信息。
* @参数：java.lang.Integer id 主键id
* @异常：FinderException主键不存在时抛出
*/
  public TblUserInfo findByPrimaryKey(java.lang.Integer id) throws FinderException;
  public TblUserInfo findUserId(String uaername) throws FinderException;
}
