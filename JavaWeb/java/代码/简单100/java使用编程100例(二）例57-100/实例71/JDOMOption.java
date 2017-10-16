import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
import java.util.List;
/**
 * <p>Title: 使用JDOM操作XML文件。</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDOMOption.java</p>
 * @author 杜江
 * @version 1.0
 */
public class JDOMOption {
/**
 *<br>方法说明：构造器，实现构造XML文件，删除元素
 *<br>输入参数：
 *<br>返回类型：
 */
  public JDOMOption() {

    String[] strChapter = { "基础知识", "图形界面", "图形处理", "I/O操作", "网络编程", "数据库" };
    Element elmtRoot = new Element("Article");
    Document docJDOM = new Document(elmtRoot);
    //新建元素
    for(int i=0; i<6; i++) {
     //声明新元素
      Element elmtChapter = new Element("Chapter");
      //添加内容
      elmtChapter.addContent(strChapter[i]);
      //构造属性，并添加到元素中
      Attribute a=new Attribute("sort",new Integer(i).toString()); 
      elmtChapter.addAttribute(a);
      //将元素添加到根节点下
      elmtRoot.addContent(elmtChapter);
    }
    //刪除第4个元素
    List lstChapter = elmtRoot.getChildren("Chapter");
    lstChapter.remove(4);
    //輸出
    OutputXML(docJDOM, "myJDOM.xml");

  }
/**
 *<br>方法说明：输出XML文件
 *<br>输入参数：Document docXML XML文件内容
 *<br>输入参数：String strFilename 输出文件名称
 *<br>返回类型：
 */
  private void OutputXML(Document docXML, String strFilename) {
    //使用JDOM的XML输出
    XMLOutputter fmt = new XMLOutputter();
    try {
     //声明使用GB2312字符集
      fmt.setEncoding("GB2312");
      //可以换行
      fmt.setNewlines(true);
      //输出文件对象
      FileWriter fwXML = new FileWriter(strFilename);

      fmt.output(docXML, fwXML);

      fwXML.close();

    }catch (IOException e) {
      e.printStackTrace();
    }
  }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args) {
    JDOMOption Jpt = new JDOMOption();
  }

}

