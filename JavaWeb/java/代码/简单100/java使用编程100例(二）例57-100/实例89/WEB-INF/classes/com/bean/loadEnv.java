package com.bean;

import java.io.*;
import java.util.Properties;
/**
 * <p>Title: 读取属性文件</p>
 * <p>Description: 获取属性文件，加载属性变量；显示属性变量；提供获取属性变量</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: loadEnv.java</p>
 * @author 杜江
 * @version 1.0
 */
public class loadEnv {
  public static boolean hasInit = false;  //初始化标志
  private static Properties prop = null; //属性集
  public static String propertyFile = "system.properties"; //配置文件
/**
 *<br>方法说明：实例化属性类
 *<br>输入参数：
 *<br>返回类型：Properties 属性对象
 */
  private static Properties getProp()
  {
        if(prop != null)
            return prop;
        else
            return prop = new Properties();
  }
/**
 *<br>方法说明：初始化环境变量
 *<br>输入参数：
 *<br>返回类型：
 */
  public void init(){
    try{
      if(hasInit)
	   return;
      InputStream is = getClass().getResourceAsStream(propertyFile);
      getProp().load(is);
	  getProp().list(System.out);
	  hasInit = true;
	}catch(Exception e){
	  System.out.println(e);
	}
  }
/**
 *<br>方法说明：获取属性变量
 *<br>输入参数：String name 获取属性变量名
 *<br>返回类型：String 属性变量值
 */
  public static String getProperty(String name)
  {
    loadEnv envBean=new loadEnv();
    envBean.init();
    return getProp().getProperty(name);
  }
/**
 *<br>方法说明：获取属性变量
 *<br>输入参数：String name 获取的属性变量名
 *<br>输入参数：String defaultValue 当属性变量不存在的情况下默认的属性值
 *<br>返回类型：String 属性变量值
 */    
  public static String getProperty(String name, String defaultValue)
  {
     loadEnv envBean=new loadEnv();
     envBean.init();
     return getProp().getProperty(name, defaultValue);
  }
/**
 *<br>方法说明：主方法，调试用
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] arg){
    loadEnv env = new loadEnv();
    env.init();
  }
}
