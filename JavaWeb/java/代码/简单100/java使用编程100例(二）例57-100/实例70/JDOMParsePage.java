import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import java.io.*;
import java.util.List;
 
/**
 * <p>Title: 使用JDOM解析XML</p>
 * <p>Description: 通过使用JDOM接口解析book.xml文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDOMParsePage.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDOMParsePage {
/**
 *<br>方法说明：构造器，实现XML文件解析
 *<br>输入参数：
 *<br>返回类型：
 */
  public JDOMParsePage() {
    Document docJDOM;
    //利用SAX建立Document
    SAXBuilder bSAX = new SAXBuilder(false);
    try {
     //生成document对象
      docJDOM = bSAX.build(new File("book.xml"));
    }catch (JDOMException e) {
      e.printStackTrace();
      return;
    }

    //得到Document的根（节点名：book）
      Element root = docJDOM.getRootElement();
      System.out.println("根节点标记名：" + root.getName());

      System.out.println("*****下面遍历XML元素*****");
      //获得page元素集合
      List list = root.getChildren("page");
      //遍历page元素
      for (int i=0; i < list.size() ; i++) {
        //获得page的元素
        Element element = (Element)list.get(i);
        //获得ID属性
        String id = element.getAttributeValue("id");
        //获得title元素集合
        List titleList = element.getChildren("title");
        //获得title第一个元素
        Element titleElement = (Element)titleList.get(0);
        //获得title元素的第一个值
        String title = titleElement.getText();
        //获得file元素集合
        List fileList = element.getChildren("file");
        //获得file第一个元素
        Element fileElement = (Element)fileList.get(0);
        //获得file元素的第一个值
        String file = fileElement.getText();

        System.out.println("ID：" + id + "  " +
                           "标题：" + title + "  " +
                           "文件：" + file);
      }
  }
/**
 *<br>方法说明：主方法，启动解析器
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args) {
    JDOMParsePage myReader = new JDOMParsePage();
  }

}

