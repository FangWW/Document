import java.io.File; 
import java.util.*; 
import jxl.*;
import jxl.write.*; 
/**
 * <p>Title: 操作EXCEL文件</p>
 * <p>Description: 本实例演示使用jxl包实现对excel文件的操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myExcel.java</p>
 * @author 杜江
 * @version 1.0
 */
public class myExcel{
  Workbook workbook;
  Sheet sheet;
/**
 *<br>方法说明：写入文件操作
 *<br>输入参数：
 *<br>返回类型：
 */
  public void write(){
    try{
        //创建一个可写入的excel文件对象
        WritableWorkbook workbook = Workbook.createWorkbook(new File("myfile.xls")); 
        //使用第一张工作表，将其命名为“午餐记录”
        WritableSheet sheet = workbook.createSheet("午餐记录", 0); 
        //表头
        Label label0 = new Label(0, 0, "时间"); 
        sheet.addCell(label0); 
        Label label1 = new Label(1, 0, "姓名"); 
        sheet.addCell(label1); 
        Label label2 = new Label(2, 0, "午餐标准"); 
        sheet.addCell(label2); 
        Label label3 = new Label(3, 0, "实际费用"); 
        sheet.addCell(label3); 
        //格式化日期
        jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-dd-MM  hh:mm:ss"); 
        jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(df); 
        jxl.write.DateTime labelDTF = new jxl.write.DateTime(0, 1, new java.util.Date(), wcfDF); 
        sheet.addCell(labelDTF);
        //普通字符
        Label labelCFC = new Label(1, 1, "riverwind"); 
        sheet.addCell(labelCFC); 
         //格式化数字
        jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##"); 
        WritableCellFormat wcfN = new WritableCellFormat(nf); 
        jxl.write.Number labelNF = new jxl.write.Number(2, 1, 13.1415926, wcfN); 
        sheet.addCell(labelNF); 
        
         
        jxl.write.Number labelNNF = new jxl.write.Number(3, 1, 10.50001, wcfN); 
        sheet.addCell(labelNNF); 
        //关闭对象，释放资源
        workbook.write(); 
        workbook.close(); 

    }catch(Exception e){
      System.out.println(e);
    }
  }
/**
 *<br>方法说明：读取excel文件一行数据
 *<br>输入参数：int row指定的行数
 *<br>返回类型：String〔〕结果数组
 */  
  public String[] readLine(int row){
    try{
      //获取数据表列数
      int colnum = sheet.getColumns();
      String[] rest = new String[colnum];
      for(int i = 0; i < colnum; i++){
        String sTemp = read(i,row);
        if(sTemp!=null)
         rest[i] = sTemp;
      }
      return rest;
    }catch(Exception e){
      System.out.println("readLine err:"+e);
      workbook.close();
      return null;
    }
  }
/**
 *<br>方法说明：读取excel的指定单元数据
 *<br>输入参数：
 *<br>返回类型：
 */
  public String read(int col, int row){
    try{
      //获得单元数据
      Cell a2 = sheet.getCell(col,row); 
      String rest = a2.getContents();
      return rest;
    }catch(Exception e){
      System.out.println("read err:"+e);
      workbook.close();
      return null;
    }
  }
/**
 *<br>方法说明：主方法，演示程序用
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arges){
    try{
      myExcel me = new myExcel();
      //生成一个可读取的excel文件对象
      me.workbook = Workbook.getWorkbook(new File("myfile.xls"));
      //使用第一个工作表
      me.sheet = me.workbook.getSheet(0);
      //读一行记录，并显示出来
      String[] ssTemp = me.readLine(1);
      for(int i=0;i<ssTemp.length;i++)
       System.out.println(ssTemp[i]);
      //写入数据
      me.write();
      
      me.workbook.close();
    }catch(Exception e){
      System.out.println(e);
    }
  }
   
}