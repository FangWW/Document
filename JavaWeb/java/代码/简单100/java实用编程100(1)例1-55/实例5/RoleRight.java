import java.util.*;
/**
 * <p>Title: 哈希表操作</p>
 * <p>Description: 这是一个权限认证的例子，使用了哈希表作为数据的存储</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: RoleRight.java</p>
 * @author 杜江
 * @version 1.0
 */
 public class RoleRight
 {
 private static Hashtable rightList = new Hashtable();
/**
 *<br>方法说明：初始化数据
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：获取角色权限代码
 *<br>输入参数：String accRole 角色名称
 *<br>返回类型：String 权限代码
 */
 public String getRight(String accRole)
 {
    if(rightList.containsKey(accRole))
      return (String)rightList.get(accRole);
    else
      return null;
 }
/**
 *<br>方法说明：添加角色和代码信息
 *<br>输入参数：String accRole 角色名称
 *<br>输入参数：String rightCode 角色权限代码 
 *<br>返回类型：void （无）
 */
 public void insert(String accRole,String rightCode)
 {
   rightList.put(accRole,rightCode);
 }
/**
 *<br>方法说明：删除角色权限
 *<br>输入参数：String accRole 角色名称
 *<br>返回类型：void（无）
 */
 public void delete(String accRole)
 {
   if(rightList.containsKey(accRole))
     rightList.remove(accRole);
 }
/**
 *<br>方法说明：修改角色权限代码
 *<br>输入参数：String accRole 角色名称
 *<br>输入参数：String rightCode 角色权限代码 
 *<br>返回类型：void（无）
 */
 public void update(String accRole,String rightCode)
 {
   //this.delete(accRole);
   this.insert(accRole,rightCode);
 }
/**
 *<br>方法说明：打印哈希表中角色和代码对应表
 *<br>输入参数：无
 *<br>返回类型：无
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
 *<br>方法说明：打印信息（过载）
 *<br>输入参数：Object oPara 打印的信息内容
 *<br>返回类型：无
 */
 public void print(Object oPara)
 {
 	System.out.println(oPara);
 }
/**
 *<br>方法说明：主方法，
 *<br>输入参数：
 *<br>返回类型：
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