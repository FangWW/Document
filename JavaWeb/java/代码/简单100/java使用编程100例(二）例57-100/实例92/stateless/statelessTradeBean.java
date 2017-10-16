//文件名：statelessTradeBean.java
//实现类
package stateless;
import javax.ejb.*;
import java.lang.*;
public class statelessTradeBean implements SessionBean {
  SessionContext sessionContext;
  double baseFunds; //账户资金
/*
*方法说明：这个方法与StatelessTradeHome.java中的主接口中的create()方法相对应，
*两个方法的参数相同。当客户端调用主接口的StatelessTradeHome.create()方法时，
*容器将分配一个EJB实例，并调用它的ejbCreate()方法。本例没有使用这个方法。
* @参数：
* @返回：
* @异常：CreateException 当系统创建EJB出错时抛出
*/
  public void ejbCreate() throws CreateException {
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
*方法说明：添加资金
* @参数：fund 资金数
* @返回：
* @异常：Exception 当增加资金为负数时
*/
  public void addFunds(double fund) throws Exception {
    if (fund<0)
        throw new Exception("Invalid fund");
    this.baseFunds+=fund;
  }
/*
*方法说明：提取资金
* @参数：fund 资金数
* @返回：
* @异常：Exception 当增加资金为负数和所提取资金超过账户上资金时
*/
  public void removeFunds(double fund) throws Exception {
    if(fund<0)
        throw new Exception("Invalid fund");
    if(this.baseFunds<fund)
        throw new Exception("the balance less than fund");
   this.baseFunds-=fund;
  }
/*
*方法说明：查询账户资金数
* @返回：double 资金数
*/
  public double getBalance() {
    return this.baseFunds;
  }
}
