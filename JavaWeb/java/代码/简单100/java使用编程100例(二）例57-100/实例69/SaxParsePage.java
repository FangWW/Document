/**
 * <p>Title: ʹ��SAX����XML</p>
 * <p>Description: ʹ��SAX�ӿڽ���book.xml�ļ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SaxParsePage.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class SaxParsePage extends DefaultHandler {
/**
 *<br>����˵�������������������������ͼ���XML�ļ�
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] argv) {
    try {
      //����SAX��������
      SAXParserFactory spfactory = SAXParserFactory.newInstance();
      //����SAX��������
      SAXParser parser = spfactory.newSAXParser();
      //ָ��XML�ļ�������XML����
      parser.parse(new File("book.xml"), new SaxParsePage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
/**
 *<br>����˵�����ļ���ʱ����
 *<br>���������
 *<br>�������ͣ�
 */
  public void startDocument() {
    System.out.println("***��ʼ����***");
  }
/**
 *<br>����˵������������ʼ���ʱ����
 *<br>���������
 *<br>�������ͣ�
 */
  public void startElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes) throws SAXException {

    System.out.println("�ڵ㿪ʼ��" + qName);
    for(int i=0;i<attributes.getLength();i++){
     System.out.println("�ڵ��������ƣ�" + attributes.getQName(i));
     System.out.println("�ڵ�����ֵ��"+attributes.getValue(i));
    }
  }
/**
 *<br>����˵�����������������޷�ʶ��Ϊ��ǻ���ָ�������ַ�ʱ����
 *<br>���������
 *<br>�������ͣ�
 */
  public void characters(char[] ch,
                         int offset,
                         int length) throws SAXException{

    System.out.println("�ڵ����ݣ�" + new String(ch, offset, length));
  }
/**
 *<br>����˵�����������ڵ����ʱ����
 *<br>���������
 *<br>�������ͣ�
 */
  public void endElement(String uri,
                         String localName,
                         String qName) {

    System.out.println("�ڵ������" + qName);
  }
/**
 *<br>����˵���������ĵ���ĩβ����
 *<br>���������
 *<br>�������ͣ�
 */
  public void endDocument() {
    System.out.println("****�ļ��������****");
  }
}
