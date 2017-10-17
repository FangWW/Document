package com.bean;

import java.util.Properties;
/**
 * <p>Title: ��ȡ�����ļ�</p>
 * <p>Description: ��ȡ�����ļ����������Ա�������ʾ���Ա������ṩ��ȡ���Ա���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: loadEnv.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class loadEnv {
  public static boolean hasInit = false;  //��ʼ����־
  private static Properties prop = null; //���Լ�
  public static String propertyFile = "system.properties"; //�����ļ�
/**
 *<br>����˵����ʵ����������
 *<br>���������
 *<br>�������ͣ�Properties ���Զ���
 */
  private static Properties getProp()
  {
        if(prop != null)
            return prop;
        else
            return prop = new Properties();
  }
/**
 *<br>����˵������ʼ����������
 *<br>���������
 *<br>�������ͣ�
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
 *<br>����˵������ȡ���Ա���
 *<br>���������String name ��ȡ���Ա�����
 *<br>�������ͣ�String ���Ա���ֵ
 */
  public static String getProperty(String name)
  {
    loadEnv envBean=new loadEnv();
    envBean.init();
    return getProp().getProperty(name);
  }
/**
 *<br>����˵������ȡ���Ա���
 *<br>���������String name ��ȡ�����Ա�����
 *<br>���������String defaultValue �����Ա��������ڵ������Ĭ�ϵ�����ֵ
 *<br>�������ͣ�String ���Ա���ֵ
 */    
  public static String getProperty(String name, String defaultValue)
  {
     loadEnv envBean=new loadEnv();
     envBean.init();
     return getProp().getProperty(name, defaultValue);
  }
/**
 *<br>����˵������������������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    loadEnv env = new loadEnv();
    env.init();
  }
}
