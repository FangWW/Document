package com.bean;


import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: ��ȡ�ͻ���Ϣ</p>
 * <p>Description: ͨ�������ͻ����͵��ļ�ͷ����ȡ�ͻ���Ϣ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Browser.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Browser extends HttpServlet
{
protected HttpServletRequest request;
protected HttpSession session;
protected String userAgent;
protected String company; // �������Ʒ��˾
protected String name; // ���������
protected String version; // ������汾
protected String mainVersion; // ��������汾��
protected String minorVersion; // �����С�汾��
protected String os; // �ͻ�ʹ�õĲ���ϵͳ
protected String language = "cn"; // �����ʹ�õ�����
protected Locale locale; // �ͻ�����ʱ��

private Hashtable supportedLanguages; // �ͻ���֧�ֵ�����

/**
 *<br>����˵��������������ȡ������Ϣ
 *<br>���������
 *<br>�������ͣ�
 */
public Browser(HttpServletRequest request, HttpSession session)
{
 this.initialize();
 this.request = request;
 this.session = session;
 //��Ҫ��������User-Agent
 this.setUserAgent(this.request.getHeader("User-Agent"));

 this.setCompany();
 this.setName();
 this.setVersion();
 this.setMainVersion();
 this.setMinorVersion();
 this.setOs();
 this.setLanguage();
 this.setLocale();
}
/**
 *<br>����˵������ʼ��֧�ֵ�����
 *<br>���������
 *<br>�������ͣ�
 */
public void initialize()
{
 this.supportedLanguages = new Hashtable(2);
 this.supportedLanguages.put("en", "");
 this.supportedLanguages.put("gb", "");
}
/**
 *<br>����˵������ȡ�������Ϣ
 *<br>���������
 *<br>�������ͣ�
 */

public void setUserAgent(String httpUserAgent)
{
 this.userAgent = httpUserAgent.toLowerCase();
}
/**
 *<br>����˵���������������Ʒ��˾
 *<br>���������
 *<br>�������ͣ�
 */
private void setCompany()
{
 if (this.userAgent.indexOf("msie") > -1)
 {
  this.company = "Microsoft";
 }
 else if (this.userAgent.indexOf("opera") > -1)
 {
  this.company = "Opera Software";
 }
  else if (this.userAgent.indexOf("mozilla") > -1)
 {
  this.company = "Netscape Communications";
 }else{
  this.company = "unknown";
 }
}
/**
 *<br>����˵������ȡ��˾����
 *<br>���������
 *<br>�������ͣ�
 */
public String getCompany()
{
 return this.company;
}
/**
 *<br>����˵�����������������
 *<br>���������
 *<br>�������ͣ�
 */
private void setName()
{
 if (this.company == "Microsoft")
 {
  this.name = "Microsoft Internet Explorer";
 }
 else if (this.company == "Netscape Communications")
 {
  this.name = "Netscape Navigator";
 }
 else if (this.company == "Operasoftware")
 {
  this.name = "Operasoftware Opera";
 }
 else
 {
  this.name = "unknown";
 }
}

/**
 *<br>����˵�����������������
 *<br>���������
 *<br>�������ͣ�
 */
public String getName()
{
 return this.name;
}
/**
 *<br>����˵�������������汾
 *<br>���������
 *<br>�������ͣ�
 */
private void setVersion()
{
  int tmpPos;
  String tmpString;

 if (this.company == "Microsoft")
 {
 String str = this.userAgent.substring(this.userAgent.indexOf("msie") + 5);
 this.version = str.substring(0, str.indexOf(";"));
 }
 else
 {
  tmpString = (this.userAgent.substring(tmpPos = (this.userAgent.indexOf("/")) + 1, tmpPos + this.userAgent.indexOf(" "))).trim();
  this.version = tmpString.substring(0, tmpString.indexOf(" "));
 }
}
/**
 *<br>����˵��������������汾
 *<br>���������
 *<br>�������ͣ�
 */
public String getVersion()
{
 return this.version;
}
/**
 *<br>����˵����������汾��
 *<br>���������
 *<br>�������ͣ�
 */
private void setMainVersion()
{
 this.mainVersion = this.version.substring(0, this.version.indexOf("."));
}
/**
 *<br>����˵�����������汾��
 *<br>���������
 *<br>�������ͣ�
 */
public String getMainVersion()
{
 return this.mainVersion;
}
/**
 *<br>����˵�������С�汾��
 *<br>���������
 *<br>�������ͣ�
 */
private void setMinorVersion()
{
 this.minorVersion = this.version.substring(this.version.indexOf(".") + 1).trim();
}
/**
 *<br>����˵��������С�汾��
 *<br>���������
 *<br>�������ͣ�
 */
public String getMinorVersion()
{
 return this.minorVersion;
}
/**
 *<br>����˵������ò���ϵͳ����
 *<br>���������
 *<br>�������ͣ�
 */
private void setOs()
{

 if (this.userAgent.indexOf("win") > -1)
 {
  if (this.userAgent.indexOf("windows 95") > -1 || this.userAgent.indexOf("win95") > -1)
  {
   this.os = "Windows 95";
  }
  if (this.userAgent.indexOf("windows 98") > -1 || this.userAgent.indexOf("win98") > -1)
  {
  this.os = "Windows 98";
  }
  if (this.userAgent.indexOf("windows nt") > -1 || this.userAgent.indexOf("winnt") > -1)
  {
   this.os = "Windows NT";
  }
  if (this.userAgent.indexOf("windows nt 5.0") > -1 )
  {
   this.os = "Windows 2000";
  }
  if (this.userAgent.indexOf("win16") > -1 || this.userAgent.indexOf("windows 3.") > -1)
  {
  this.os = "Windows 3.x";
  }
 }
}
/**
 *<br>����˵�������ز���ϵͳ����
 *<br>���������
 *<br>�������ͣ�
 */
public String getOs()
{
  return this.os;
}
/**
 *<br>����˵��������������������
 *<br>���������
 *<br>�������ͣ�
 */
private void setLanguage()
{
  String prefLanguage = this.request.getHeader("Accept-Language");

  if (prefLanguage != null)
  {
  String language = null;
  StringTokenizer st = new StringTokenizer(prefLanguage, ",");

  int elements = st.countTokens();

  for (int idx = 0; idx<elements; idx++)
  {
  if (this.supportedLanguages.containsKey((language = st.nextToken())))
  {
    this.language = this.parseLocale(language);
  }
  }
 }
}
/**
 *<br>����˵���������������ʱ��
 *<br>���������
 *<br>�������ͣ�
 */
private String parseLocale(String language)
{
  StringTokenizer st = new StringTokenizer(language, "-");



 if (st.countTokens() == 2)
 {
   return st.nextToken();
 }
 else
 {
  return language;
 }
}
/**
 *<br>����˵������������
 *<br>���������
 *<br>�������ͣ�
 */
public String getLanguage()
{
  return this.language;
}
/**
 *<br>����˵������ÿͻ�ʱ��
 *<br>���������
 *<br>�������ͣ�
 */
private void setLocale()
{
  this.locale = new Locale(this.language, "");
}

/**
 *<br>����˵��������ʱ��
 *<br>���������
 *<br>�������ͣ�
 */
public Locale getLocale()
{
  return this.locale;
}
}
