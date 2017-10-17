public class DOMPageList {
  public static void main(String[] args) {
    try {
      //������������
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      //ָ��DocumentBuilder
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      //���ļ�����һ��Document����ΪXML�ļ����Ѿ�ָ���˱��룬�������ﲻ����
      Document doc = builder.parse(new File("book.xml"));
      //�õ�Document�ĸ����ڵ�����book��
      Element root = doc.getDocumentElement();
      System.out.println("���ڵ�������" + root.getTagName());

      System.out.println("*****�������XMLԪ��*****");
      //���pageԪ��
      NodeList list = root.getElementsByTagName("page");
      //����pageԪ��
      for (int i=0; i < list.getLength() ; i++) {
        //���page��Ԫ��
        Element element = (Element)list.item(i);
        //���ID����
        String id = element.getAttribute("id");
        //���title����
        NodeList titleList = element.getElementsByTagName("title");
        //���titleԪ��
        Element titleElement = (Element)titleList.item(0);
        //���titleԪ�صĵ�һ��ֵ
        String title = titleElement.getFirstChild().getNodeValue();
        //���fileԪ������
        NodeList fileList = element.getElementsByTagName("file");
        //���fileԪ��
        Element fileElement = (Element)fileList.item(0);
        //���fileԪ�صĵ�һ��ֵ
        String file = fileElement.getFirstChild().getNodeValue();

        System.out.println("ID��" + id + "  " +
                           "���⣺" + title + "  " +
                           "�ļ���" + file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
