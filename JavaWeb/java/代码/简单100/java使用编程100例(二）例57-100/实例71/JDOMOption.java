import org.jdom.*;
import org.jdom.output.*;

import java.util.List;
/**
 * <p>Title: ʹ��JDOM����XML�ļ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDOMOption.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDOMOption {
/**
 *<br>����˵������������ʵ�ֹ���XML�ļ���ɾ��Ԫ��
 *<br>���������
 *<br>�������ͣ�
 */
  public JDOMOption() {

    String[] strChapter = { "����֪ʶ", "ͼ�ν���", "ͼ�δ���", "I/O����", "������", "���ݿ�" };
    Element elmtRoot = new Element("Article");
    Document docJDOM = new Document(elmtRoot);
    //�½�Ԫ��
    for(int i=0; i<6; i++) {
     //������Ԫ��
      Element elmtChapter = new Element("Chapter");
      //�������
      elmtChapter.addContent(strChapter[i]);
      //�������ԣ�����ӵ�Ԫ����
      Attribute a=new Attribute("sort",new Integer(i).toString()); 
      elmtChapter.addAttribute(a);
      //��Ԫ����ӵ����ڵ���
      elmtRoot.addContent(elmtChapter);
    }
    //�h����4��Ԫ��
    List lstChapter = elmtRoot.getChildren("Chapter");
    lstChapter.remove(4);
    //ݔ��
    OutputXML(docJDOM, "myJDOM.xml");

  }
/**
 *<br>����˵�������XML�ļ�
 *<br>���������Document docXML XML�ļ�����
 *<br>���������String strFilename ����ļ�����
 *<br>�������ͣ�
 */
  private void OutputXML(Document docXML, String strFilename) {
    //ʹ��JDOM��XML���
    XMLOutputter fmt = new XMLOutputter();
    try {
     //����ʹ��GB2312�ַ���
      fmt.setEncoding("GB2312");
      //���Ի���
      fmt.setNewlines(true);
      //����ļ�����
      FileWriter fwXML = new FileWriter(strFilename);

      fmt.output(docXML, fwXML);

      fwXML.close();

    }catch (IOException e) {
      e.printStackTrace();
    }
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args) {
    JDOMOption Jpt = new JDOMOption();
  }

}

