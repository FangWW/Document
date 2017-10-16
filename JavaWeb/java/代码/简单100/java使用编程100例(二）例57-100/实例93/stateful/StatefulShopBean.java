
package stateful;
import javax.ejb.*;
import java.lang.*;
import java.util.*;
/*
*本类是一个无状态会话EJB。
*必需实现SessionBean。
*/
public class StatefulShopBean implements SessionBean {
  SessionContext sessionContext;
  Vector  goods;
/*
*方法说明：这个方法与StatefulShopHome.java中的主接口中的create()方法相对应，
*两个方法的参数相同。当客户端调用主接口的StatefulShopHome。create()方法是，
*容器将分配一个EJB实例，并调用它的ejbCreate()方法。
* @参数：
* @返回：
* @异常：CreateException 当系统创建EJB出错时抛出
*/
  public void ejbCreate() throws CreateException {
    this.goods=new Vector();
  }
/*
*方法说明：本方法必需实现，本例中没有使用到。
*/
  public void ejbRemove() {
  }
/*
*方法说明：本方法必需实现，本例中没有使用到。
*/
  public void ejbActivate() {
  }
/*
*方法说明：本方法必需实现，本例中没有使用到。
*/
  public void ejbPassivate() {
  }
/*
*方法说明：设置会话上下文
* @参数：sessionContext
*/
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }
/*
*方法说明：添加商品
* @参数：id 商品id
* @参数：goods 商品名称
* @参数：value 商品价格
* @返回：
* @异常：Exception 当增加资金为负数时
*/
  public void addGoods(int id,String gds,double value) throws Exception {
    if (value<0)
        throw new Exception("Invalid value");
    Vector gdsInfo = new Vector();
    gdsInfo.addElement(new Integer(id));
    gdsInfo.addElement(gds);
    gdsInfo.addElement(new Double(value));
    goods.addElement(gdsInfo);
  }
/*
*方法说明：移除商品
* @参数：id 商品id
* @返回：
* @异常：Exception 当增加资金为负数和所提取资金超过账户上资金时
*/
  public void removeGoods(int id) throws Exception {
 
   try{
   for(int i=0;i<goods.size();i++){
     Vector vTemp = (Vector)goods.elementAt(i);
     Object sTemp = vTemp.elementAt(0);
     if(sTemp==null) throw new Exception("String is null");
     int iTemp = Integer.parseInt(String.valueOf(sTemp));
     if(iTemp==id){
       goods.remove(i);
     }
   }
  }catch(Exception e){
    throw e;
  }
  }
/*
*方法说明：查询商品
* @返回：Hashtable 商品信息
*/
  public Vector  seeGoods() {
    return this.goods;
  }
}
