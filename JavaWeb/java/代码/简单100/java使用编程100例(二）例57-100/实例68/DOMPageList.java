import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class DOMPageList {
  public static void main(String[] args) {
    try {
      //创建解析工厂
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      //指定DocumentBuilder
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      //从文件构造一个Document，因为XML文件中已经指定了编码，所以这里不必了
      Document doc = builder.parse(new File("book.xml"));
      //得到Document的根（节点名：book）
      Element root = doc.getDocumentElement();
      System.out.println("根节点标记名：" + root.getTagName());

      System.out.println("*****下面遍历XML元素*****");
      //获得page元素
      NodeList list = root.getElementsByTagName("page");
      //遍历page元素
      for (int i=0; i < list.getLength() ; i++) {
        //获得page的元素
        Element element = (Element)list.item(i);
        //获得ID属性
        String id = element.getAttribute("id");
        //获得title属性
        NodeList titleList = element.getElementsByTagName("title");
        //获得title元素
        Element titleElement = (Element)titleList.item(0);
        //获得title元素的第一个值
        String title = titleElement.getFirstChild().getNodeValue();
        //获得file元素名称
        NodeList fileList = element.getElementsByTagName("file");
        //获得file元素
        Element fileElement = (Element)fileList.item(0);
        //获得file元素的第一个值
        String file = fileElement.getFirstChild().getNodeValue();

        System.out.println("ID：" + id + "  " +
                           "标题：" + title + "  " +
                           "文件：" + file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
