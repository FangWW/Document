import java.net.*;
/**
 * <p>Title: 获取本机名称和IP地址</p>
 * <p>Description: 使用InetAddress来获取本机名称和IP地址信息</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: NetInfo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class NetInfo {
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
 public static void main(String[] args) {
    new NetInfo().say();
    }
/**
 *<br>方法说明：查看本机名称和IP地址
 *<br>输入参数：
 *<br>返回类型：
 */
 public void say() {
   try {
   InetAddress i = InetAddress.getLocalHost();
   System.out.println(i);                  //计算机名称和IP
   System.out.println(i.getHostName());    //名称
   System.out.println(i.getHostAddress()); //只获得IP
   }
   catch(Exception e){e.printStackTrace();}
 }
}

