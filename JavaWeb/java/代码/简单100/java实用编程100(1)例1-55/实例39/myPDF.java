import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.Color;

/**
 * <p>Title: ����PDF�ļ�</p>
 * <p>Description: ��ʵ��ͨ��ʹ��iText������һ������PDF�ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myPDF.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class myPDF{
/**
 *<br>����˵����дPDF�ļ�
 *<br>���������
 *<br>�������ͣ�
 */
  public void write(){
   try{
     Document document=new Document(PageSize.A4, 50, 50, 100, 50);
     Rectangle pageRect=document.getPageSize();
     PdfWriter.getInstance(document, new FileOutputStream("tables.pdf"));
     //������������
     BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
     Font fontSong = new Font(bfSong, 10, Font.NORMAL);
     // ����һ��ˮӡ
     try {
         Watermark watermark = new Watermark(Image.getInstance("test.jpg"), pageRect.left()+50,pageRect.top()-85);
         watermark.scalePercent(50);
         document.add(watermark);
     }catch(Exception e) {
		  System.err.println("��鿴�ļ���test.jpg���Ƿ�����ȷ��λ��?");
     }
     
      // Ϊҳ����ҳͷ��Ϣ
     HeaderFooter header = new HeaderFooter(new Phrase("Javaʵ��һ����",fontSong), false);
     header.setBorder(2);
     header.setAlignment(Element.ALIGN_RIGHT);
     document.setHeader(header);
     
	  // Ϊҳ����ҳ����Ϣ
     HeaderFooter footer = new HeaderFooter(new Phrase("�� ",fontSong),new Phrase(" ҳ",fontSong));
     footer.setAlignment(Element.ALIGN_CENTER);
     footer.setBorder(1);
     document.setFooter(footer);

      // ���ĵ�
     document.open(); 
     //������
     Table table = new Table(4);
     table.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
     table.setBorder(Rectangle.NO_BORDER);
     int hws[] = {10, 20, 10, 20,};
     table.setWidths(hws);
     table.setWidth(100);
     //��ͷ��Ϣ
     Cell cellmain = new Cell(new Phrase("�û���Ϣ",new Font(bfSong, 10, Font.BOLD,new Color(0,0,255))));
     cellmain.setHorizontalAlignment(Element.ALIGN_CENTER);
     cellmain.setColspan(4);
     cellmain.setBorder(Rectangle.NO_BORDER);
     cellmain.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
     table.addCell(cellmain);
      //�ֱ�ͷ��Ϣ
     Cell cellleft= new Cell(new Phrase("�ջ�����Ϣ",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));
     cellleft.setColspan(2);
     cellleft.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(cellleft);
     Cell cellright= new Cell(new Phrase("��������Ϣ",new Font(bfSong, 10, Font.ITALIC,new Color(0,0,255))));
     cellright.setColspan(2);
     cellright.setHorizontalAlignment(Element.ALIGN_CENTER);
     table.addCell(cellright);
     
     //�ջ��Ͷ�������Ϣ����������
     table.addCell(new Phrase("����",fontSong));
     table.addCell(new Phrase("����",fontSong));
     table.addCell(new Phrase("����",fontSong));
     table.addCell(new Phrase("����",fontSong));

     table.addCell(new Phrase("�绰",fontSong));
     table.addCell(new Phrase("23456789",fontSong));
     table.addCell(new Phrase("�绰",fontSong));
     table.addCell(new Phrase("9876543",fontSong));

     table.addCell(new Phrase("�ʱ�",fontSong));
     table.addCell(new Phrase("100002",fontSong));
     table.addCell(new Phrase("�ʱ�",fontSong));
     table.addCell(new Phrase("200001",fontSong));

     table.addCell(new Phrase("��ַ",fontSong));
     table.addCell(new Phrase("����������XX·XX��",fontSong));
     table.addCell(new Phrase("��ַ",fontSong));
     table.addCell(new Phrase("�Ϻ�½������XX·XX��",fontSong));

     table.addCell(new Phrase("�����ʼ�",fontSong));
     table.addCell(new Phrase("zh_san@hotmail.com",fontSong));
     table.addCell(new Phrase("�����ʼ�",fontSong));
     table.addCell(new Phrase("li_si@hotmail.com",fontSong));
     //�������ӵ��ı���
     document.add(table);
     //�ر��ı����ͷ���Դ
     document.close(); 
     
   }catch(Exception e){
     System.out.println(e);   
   }
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    myPDF p = new myPDF();
    p.write();
  }
}