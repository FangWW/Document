//�ļ�����statelessTradeBean.java
//ʵ����
package stateless;
import javax.ejb.*;
public class statelessTradeBean implements SessionBean {
  SessionContext sessionContext;
  double baseFunds; //�˻��ʽ�
/*
*����˵�������������StatelessTradeHome.java�е����ӿ��е�create()�������Ӧ��
*���������Ĳ�����ͬ�����ͻ��˵������ӿڵ�StatelessTradeHome.create()����ʱ��
*����������һ��EJBʵ��������������ejbCreate()����������û��ʹ�����������
* @������
* @���أ�
* @�쳣��CreateException ��ϵͳ����EJB����ʱ�׳�
*/
  public void ejbCreate() throws CreateException {
  }
/*
*����˵��������������ʵ�֣�������û��ʹ�õ���
*/
  public void ejbRemove() {
  }
/*
*����˵��������������ʵ�֣�������û��ʹ�õ���
*/
  public void ejbActivate() {
  }
/*
*����˵��������������ʵ�֣�������û��ʹ�õ���
*/
  public void ejbPassivate() {
  }
/*
*����˵�������ûỰ������
* @������sessionContext
*/
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }
/*
*����˵��������ʽ�
* @������fund �ʽ���
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����ʱ
*/
  public void addFunds(double fund) throws Exception {
    if (fund<0)
        throw new Exception("Invalid fund");
    this.baseFunds+=fund;
  }
/*
*����˵������ȡ�ʽ�
* @������fund �ʽ���
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����������ȡ�ʽ𳬹��˻����ʽ�ʱ
*/
  public void removeFunds(double fund) throws Exception {
    if(fund<0)
        throw new Exception("Invalid fund");
    if(this.baseFunds<fund)
        throw new Exception("the balance less than fund");
   this.baseFunds-=fund;
  }
/*
*����˵������ѯ�˻��ʽ���
* @���أ�double �ʽ���
*/
  public double getBalance() {
    return this.baseFunds;
  }
}
