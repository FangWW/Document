/**
 * <p>Title: ��ϣ�����</p>
 * <p>Description: ����һ��Ȩ����֤�����ӣ�ʹ���˹�ϣ����Ϊ���ݵĴ洢</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: RoleRight.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class RoleRight
 {
 private static Hashtable rightList = new Hashtable();
/**
 *<br>����˵������ʼ������
 *<br>���������
 *<br>�������ͣ�
 */
 public void init()
 {
    String[] accRoleList = {"admin","satrap","manager","user","guest"};
    String[] rightCodeList = {"10001","10011","10021","20011","24011"};
    for(int i=0;i<accRoleList.length;i++)
    {
      rightList.put(accRoleList[i],rightCodeList[i]);
    }
 }
/**
 *<br>����˵������ȡ��ɫȨ�޴���
 *<br>���������String accRole ��ɫ����
 *<br>�������ͣ�String Ȩ�޴���
 */
 public String getRight(String accRole)
 {
    if(rightList.containsKey(accRole))
      return (String)rightList.get(accRole);
    else
      return null;
 }
/**
 *<br>����˵������ӽ�ɫ�ʹ�����Ϣ
 *<br>���������String accRole ��ɫ����
 *<br>���������String rightCode ��ɫȨ�޴��� 
 *<br>�������ͣ�void ���ޣ�
 */
 public void insert(String accRole,String rightCode)
 {
   rightList.put(accRole,rightCode);
 }
/**
 *<br>����˵����ɾ����ɫȨ��
 *<br>���������String accRole ��ɫ����
 *<br>�������ͣ�void���ޣ�
 */
 public void delete(String accRole)
 {
   if(rightList.containsKey(accRole))
     rightList.remove(accRole);
 }
/**
 *<br>����˵�����޸Ľ�ɫȨ�޴���
 *<br>���������String accRole ��ɫ����
 *<br>���������String rightCode ��ɫȨ�޴��� 
 *<br>�������ͣ�void���ޣ�
 */
 public void update(String accRole,String rightCode)
 {
   //this.delete(accRole);
   this.insert(accRole,rightCode);
 }
/**
 *<br>����˵������ӡ��ϣ���н�ɫ�ʹ����Ӧ��
 *<br>�����������
 *<br>�������ͣ���
 */
 public void print()
 {
 	Enumeration RLKey = rightList.keys();
 	while(RLKey.hasMoreElements())
 	{
 		String accRole = RLKey.nextElement().toString();
 		print(accRole+"="+this.getRight(accRole));
 	}
 }
/**
 *<br>����˵������ӡ��Ϣ�����أ�
 *<br>���������Object oPara ��ӡ����Ϣ����
 *<br>�������ͣ���
 */
 public void print(Object oPara)
 {
 	System.out.println(oPara);
 }
/**
 *<br>����˵������������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String[] args)
 {
 	RoleRight RR = new RoleRight();
 	RR.init();
 	RR.print();
 	RR.print("___________________________");
 	RR.insert("presider","10110");
 	RR.print();
 	RR.print("___________________________");
 	RR.update("presider","10100");
 	RR.print();
 	RR.print("___________________________");
 	RR.delete("presider");
 	RR.print();
 } 
 }//end:)~