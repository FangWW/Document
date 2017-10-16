import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * <p>Title: 生成PDF文件</p>
 * <p>Description: 本实例通过使用iText包生成一个表格的PDF文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myPDF.java</p>
 * @author 杜江
 * @version 1.0
 */
public class myPDF{
/**
 *<br>方法说明：写PDF文件
 *<br>输入参数：
 *<br>返回类型：
 */
  public void write(){
   try{
     Document document=new Document(PageSize.A4, 50, 50, 100, 50);
     Rectangle pageRect=document.getPageSize();
     PdfWriter.getInstance(document, new FileOutputStream("tables.pdf"));
     //创建汉字字体
     BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
     Font fontSong = new Font(bfSong, 10, Font.NORMAL);
     // 增加一个水印
     try {
         Watermark watermark = new Watermark(Image.getInstance("test.jpg"), pageRect.left()+50,pageRect.top()-85);
         watermark.scalePercent(50);
         document.add(watermark);
     }catch(Exception e) {
		  System.err.println("请查看文件“test.jpg”是否在正确的位置?");
     }
     
      // 为页增加页头信息
     HeaderFooter header = new HeaderFooter(new Phrase("Java实例一百例",fontSong), false);
     header.setBorder(2);
     header.setAlignment(Element.ALIGN_RIGHT);
     document.setHeader(header);
     
	  // 为页增加页脚信息
     HeaderFooter footer = new HeaderFooter(new Phrase("第 ",fontSong),new Phrase(" 页",fontSong));
     footer.setAlignment(Element.ALIGN_CENTER);
     footer.setBorder(1);
     document.setFooter(footer);

      // 打开文档
     document.open(); 
     //构造表格
     Table table = new Table(4);
     table.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
     table.setBorder(Rectangle.NO_BORDER);
     int hws[] = {10, 20, 10, 20,};
     table.setWidths(hws);
     table.setWidth(100);
     //表头信息
     Cell cellmain = new Cell(new Phrase("用户信息",new Font(bfSong, 10, Font.BOLD,new Color(0,0,255))));
     cellmain.setHorizontalAlignment(Element.ALIGN_CENTER);
     cellmain.setColspan(4);
     cellmain.setBorder(Rectangle.NO_BORDER);
     cellmain.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
     table.addCell(cellmain);
      //分表头信息
     Cell cellleft= new Cell(new Phrase("收货人信息",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));
     cellleft.setColspan(2);
     cellleft.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(cellleft);
     Cell cellright= new Cell(new Phrase("订货人信息",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));
     cellright.setColspan(2);
     cellright.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(cellright);
     
     //收货和订货人信息，表体内容
     table.addCell(new Phrase("姓名",fontSong));
     table.addCell(new Phrase("张三",fontSong));
     table.addCell(new Phrase("姓名",fontSong));
     table.addCell(new Phrase("李四",fontSong));

     table.addCell(new Phrase("电话",fontSong));
     table.addCell(new Phrase("23456789",fontSong));
     table.addCell(new Phrase("电话",fontSong));
     table.addCell(new Phrase("9876543",fontSong));

     table.addCell(new Phrase("邮编",fontSong));
     table.addCell(new Phrase("100002",fontSong));
     table.addCell(new Phrase("邮编",fontSong));
     table.addCell(new Phrase("200001",fontSong));

     table.addCell(new Phrase("地址",fontSong));
     table.addCell(new Phrase("北京西城区XX路XX号",fontSong));
     table.addCell(new Phrase("地址",fontSong));
     table.addCell(new Phrase("上海陆家嘴区XX路XX号",fontSong));

     table.addCell(new Phrase("电子邮件",fontSong));
     table.addCell(new Phrase("zh_san@hotmail.com",fontSong));
     table.addCell(new Phrase("电子邮件",fontSong));
     table.addCell(new Phrase("li_si@hotmail.com",fontSong));
     //将表格添加到文本中
     document.add(table);
     //关闭文本，释放资源
     document.close(); 
     
   }catch(Exception e){
     System.out.println(e);   
   }
  }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arg){
    myPDF p = new myPDF();
    p.write();
  }
}