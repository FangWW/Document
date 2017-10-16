import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
/**
 * <p>Title: 使用SAX解析XML</p>
 * <p>Description: 使用SAX接口解析book.xml文件</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SaxParsePage.java</p>
 * @author 杜江
 * @version 1.0
 */
public class SaxParsePage extends DefaultHandler {
/**
 *<br>方法说明：主方法，声明解析工厂和加载XML文件
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] argv) {
    try {
      //建立SAX解析工厂
      SAXParserFactory spfactory = SAXParserFactory.newInstance();
      //生成SAX解析对象
      SAXParser parser = spfactory.newSAXParser();
      //指定XML文件，进行XML解析
      parser.parse(new File("book.xml"), new SaxParsePage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
/**
 *<br>方法说明：文件打开时调用
 *<br>输入参数：
 *<br>返回类型：
 */
  public void startDocument() {
    System.out.println("***开始解析***");
  }
/**
 *<br>方法说明：当遇到开始标记时调用
 *<br>输入参数：
 *<br>返回类型：
 */
  public void startElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes) throws SAXException {

    System.out.println("节点开始：" + qName);
    for(int i=0;i<attributes.getLength();i++){
     System.out.println("节点属性名称：" + attributes.getQName(i));
     System.out.println("节点属性值："+attributes.getValue(i));
    }
  }
/**
 *<br>方法说明：当分析器遇到无法识别为标记或者指令类型字符时调用
 *<br>输入参数：
 *<br>返回类型：
 */
  public void characters(char[] ch,
                         int offset,
                         int length) throws SAXException{

    System.out.println("节点数据：" + new String(ch, offset, length));
  }
/**
 *<br>方法说明：当遇到节点结束时调用
 *<br>输入参数：
 *<br>返回类型：
 */
  public void endElement(String uri,
                         String localName,
                         String qName) {

    System.out.println("节点结束：" + qName);
  }
/**
 *<br>方法说明：当到文档的末尾调用
 *<br>输入参数：
 *<br>返回类型：
 */
  public void endDocument() {
    System.out.println("****文件解析完毕****");
  }
}
