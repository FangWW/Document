package  com.bean; 
/**
 * <p>Title: JSP调用的Bean</p>
 * <p>Description: 用户认证校验</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myBean.java</p>
 * @author 杜江
 * @version 1.0
 */
public class myBean  {
//用户名
 private String NAME="";
 public void setNAME(String newFiled) {
    NAME=newFiled;
 }
 public String getNAME() {
    return NAME;
 }
 //密码
 private String PWD="";
 public void setPWD(String newFiled) {
   	PWD=newFiled;
 }
 public String getPWD() {
    return PWD;
 }
 //角色ID
 private int ID=-1;
 public void setID(int newFiled) {
   	ID=newFiled;
 }
 public int getID() {
    return ID;
 }
/**
 *<br>方法说明：检查用户是否合法
 *<br>输入参数：
 *<br>返回类型：
 */ 
 public int check(){
   String[] name = {"tom","river","wind","riverwind"};
   String[] pwd = {"123","1234","12345","123456"};
   int[][] id = {{20010},
                 {20010,10001},
                 {20010,20001},
                 {20010,20001,10001}};
   for(int i=0;i<name.length;i++){

     if(name[i].equals(NAME)){
       if(pwd[i].equals(PWD)){
         int[] iTemp = id[i];
         for(int j=0;j<iTemp.length;j++){
           if(iTemp[j]==ID) return 1;
         }
         return 0;
       }
       return -1;
     }
   }
   return -2;
 }
}//end