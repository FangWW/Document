import java.io.*;
import java.net.*;

/**
 * <p>Title: 获取一个URL文本</p>
 * <p>Description: 通过使用URL类，构造一个输入对象，并读取其内容。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: getURL.java</p>
 * @author 杜江
 * @version 1.0
 */
public class getURL{

 public static void main(String[] arg){
  if(arg.length!=1){
    System.out.println("USE java getURL  url");
    return;
  }
  new getURL(arg[0]);
 }
/**
 *<br>方法说明：构造器
 *<br>输入参数：String URL 互联网的网页地址。
 *<br>返回类型：
 */
 public getURL(String URL){
    try {
        //创建一个URL对象
        URL url = new URL(URL);
    
        //读取从服务器返回的所有文本
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            //这里对文本出来
            display(str);
        }
        in.close();
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    }
 }
/**
 *<br>方法说明：显示信息
 *<br>输入参数：
 *<br>返回类型：
 */
 private void display(String s){
   if(s!=null)
     System.out.println(s);
 }
}
