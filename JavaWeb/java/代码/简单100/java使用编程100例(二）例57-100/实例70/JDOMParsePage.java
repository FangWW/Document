import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import java.util.List;
 
/**
 * <p>Title: ʹ��JDOM����XML</p>
 * <p>Description: ͨ��ʹ��JDOM�ӿڽ���book.xml�ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDOMParsePage.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDOMParsePage {
/**
 *<br>����˵������������ʵ��XML�ļ�����
 *<br>���������
 *<br>�������ͣ�
 */
  public JDOMParsePage() {
    Document docJDOM;
    //����SAX����Document
    SAXBuilder bSAX = new SAXBuilder(false);
    try {
     //����document����
      docJDOM = bSAX.build(new File("book.xml"));
    }catch (JDOMException e) {
      e.printStackTrace();
      return;
    }

    //�õ�Document�ĸ����ڵ�����book��
      Element root = docJDOM.getRootElement();
      System.out.println("���ڵ�������" + root.getName());

      System.out.println("*****�������XMLԪ��*****");
      //���pageԪ�ؼ���
      List list = root.getChildren("page");
      //����pageԪ��
      for (int i=0; i < list.size() ; i++) {
        //���page��Ԫ��
        Element element = (Element)list.get(i);
        //���ID����
        String id = element.getAttributeValue("id");
        //���titleԪ�ؼ���
        List titleList = element.getChildren("title");
        //���title��һ��Ԫ��
        Element titleElement = (Element)titleList.get(0);
        //���titleԪ�صĵ�һ��ֵ
        String title = titleElement.getText();
        //���fileԪ�ؼ���
        List fileList = element.getChildren("file");
        //���file��һ��Ԫ��
        Element fileElement = (Element)fileList.get(0);
        //���fileԪ�صĵ�һ��ֵ
        String file = fileElement.getText();

        System.out.println("ID��" + id + "  " +
                           "���⣺" + title + "  " +
                           "�ļ���" + file);
      }
  }
/**
 *<br>����˵����������������������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args) {
    JDOMParsePage myReader = new JDOMParsePage();
  }

}

