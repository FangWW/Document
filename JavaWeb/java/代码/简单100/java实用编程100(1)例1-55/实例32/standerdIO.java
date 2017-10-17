/**
 * <p>Title: ��ע�������</p>
 * <p>Description: ���ձ�׼�ļ������룬���������Ļ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: standerdIO.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class standerdIO{
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String[] args){
   Vector vTemp = new Vector();
   boolean flag = true;
   while(flag){
     System.out.print("input>");
     String sTemp ="";  
     //��ȡ����,System.in��ʾ���ռ���������
     BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
     try{
     //��ȡһ������
      sTemp = stdin.readLine();
     }catch(IOException ie){
       System.err.println("IO error!");
     }
     //������������
     String sCMD="";
     String sContext="";
     int point = sTemp.indexOf(":");
     if(point==-1){
         sCMD = sTemp.trim();
     }else{
       sCMD = sTemp.substring(0,point);
       sContext = sTemp.substring(point+1);
     }
     //�������
     if(sCMD.equalsIgnoreCase("in")){
       if(sContext.equals("")){
         System.err.println("this command format is errer!");
       }else{
         vTemp.addElement(sContext);
       }   
     }//�鿴���
     else if(sCMD.equalsIgnoreCase("out")){
       for(int i=0;i<vTemp.size();i++){
         System.out.println(i+":"+vTemp.elementAt(i));
       }
     }//����
     else if(sCMD.equalsIgnoreCase("quit")){
       flag=false;
     }
     else{
       System.err.println("this command don't run!");
       System.out.print("use:   in:command");
       System.out.print("use:   out");
     }
   }
 }
}
