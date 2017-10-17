
package stateful;
import javax.ejb.*;
/*
*������һ����״̬�ỰEJB��
*����ʵ��SessionBean��
*/
public class StatefulShopBean implements SessionBean {
  SessionContext sessionContext;
  Vector  goods;
/*
*����˵�������������StatefulShopHome.java�е����ӿ��е�create()�������Ӧ��
*���������Ĳ�����ͬ�����ͻ��˵������ӿڵ�StatefulShopHome��create()�����ǣ�
*����������һ��EJBʵ��������������ejbCreate()������
* @������
* @���أ�
* @�쳣��CreateException ��ϵͳ����EJB����ʱ�׳�
*/
  public void ejbCreate() throws CreateException {
    this.goods=new Vector();
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
*����˵���������Ʒ
* @������id ��Ʒid
* @������goods ��Ʒ����
* @������value ��Ʒ�۸�
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����ʱ
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
*����˵�����Ƴ���Ʒ
* @������id ��Ʒid
* @���أ�
* @�쳣��Exception �������ʽ�Ϊ����������ȡ�ʽ𳬹��˻����ʽ�ʱ
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
*����˵������ѯ��Ʒ
* @���أ�Hashtable ��Ʒ��Ϣ
*/
  public Vector  seeGoods() {
    return this.goods;
  }
}
