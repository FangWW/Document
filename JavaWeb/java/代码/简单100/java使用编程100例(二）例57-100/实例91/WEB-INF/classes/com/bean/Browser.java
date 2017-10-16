package com.bean;


import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: 获取客户信息</p>
 * <p>Description: 通过解析客户发送的文件头，获取客户信息</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Browser.java</p>
 * @author 杜江
 * @version 1.0
 */
public class Browser extends HttpServlet
{
protected HttpServletRequest request;
protected HttpSession session;
protected String userAgent;
protected String company; // 浏览器出品公司
protected String name; // 浏览器名称
protected String version; // 浏览器版本
protected String mainVersion; // 浏览器主版本号
protected String minorVersion; // 浏览器小版本号
protected String os; // 客户使用的操作系统
protected String language = "cn"; // 浏览器使用的语言
protected Locale locale; // 客户所在时区

private Hashtable supportedLanguages; // 客户所支持的语言

/**
 *<br>方法说明：构造器，获取所有信息
 *<br>输入参数：
 *<br>返回类型：
 */
public Browser(HttpServletRequest request, HttpSession session)
{
 this.initialize();
 this.request = request;
 this.session = session;
 //主要解析对象：User-Agent
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
 *<br>方法说明：初始化支持的语言
 *<br>输入参数：
 *<br>返回类型：
 */
public void initialize()
{
 this.supportedLanguages = new Hashtable(2);
 this.supportedLanguages.put("en", "");
 this.supportedLanguages.put("gb", "");
}
/**
 *<br>方法说明：获取浏览器信息
 *<br>输入参数：
 *<br>返回类型：
 */

public void setUserAgent(String httpUserAgent)
{
 this.userAgent = httpUserAgent.toLowerCase();
}
/**
 *<br>方法说明：解析浏览器出品公司
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：获取公司名称
 *<br>输入参数：
 *<br>返回类型：
 */
public String getCompany()
{
 return this.company;
}
/**
 *<br>方法说明：解析浏览器名称
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：返回浏览器名称
 *<br>输入参数：
 *<br>返回类型：
 */
public String getName()
{
 return this.name;
}
/**
 *<br>方法说明：获得浏览器版本
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：返回浏览器版本
 *<br>输入参数：
 *<br>返回类型：
 */
public String getVersion()
{
 return this.version;
}
/**
 *<br>方法说明：获得主版本号
 *<br>输入参数：
 *<br>返回类型：
 */
private void setMainVersion()
{
 this.mainVersion = this.version.substring(0, this.version.indexOf("."));
}
/**
 *<br>方法说明：返回主版本号
 *<br>输入参数：
 *<br>返回类型：
 */
public String getMainVersion()
{
 return this.mainVersion;
}
/**
 *<br>方法说明：获得小版本号
 *<br>输入参数：
 *<br>返回类型：
 */
private void setMinorVersion()
{
 this.minorVersion = this.version.substring(this.version.indexOf(".") + 1).trim();
}
/**
 *<br>方法说明：返回小版本号
 *<br>输入参数：
 *<br>返回类型：
 */
public String getMinorVersion()
{
 return this.minorVersion;
}
/**
 *<br>方法说明：获得操作系统名称
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：返回操作系统名称
 *<br>输入参数：
 *<br>返回类型：
 */
public String getOs()
{
  return this.os;
}
/**
 *<br>方法说明：获得浏览器接受语言
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：获得语言语言时区
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：返回语言
 *<br>输入参数：
 *<br>返回类型：
 */
public String getLanguage()
{
  return this.language;
}
/**
 *<br>方法说明：获得客户时区
 *<br>输入参数：
 *<br>返回类型：
 */
private void setLocale()
{
  this.locale = new Locale(this.language, "");
}

/**
 *<br>方法说明：返回时区
 *<br>输入参数：
 *<br>返回类型：
 */
public Locale getLocale()
{
  return this.locale;
}
}
