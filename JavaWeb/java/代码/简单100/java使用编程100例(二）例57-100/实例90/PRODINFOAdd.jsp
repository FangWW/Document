<%@ page contentType="text/html; charset=GB2312"%>
<jsp:useBean id="fileBean" scope="page" class="com.bean.MoqUploadBean" />
<html> 
<head>
<title></title>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<div align="center">添加商品浏览</div><br><br>
<%
//加载上传路径
fileBean.getUpLoadPath();
//设置上传尺寸
fileBean.setSize(10000*1024);
//设置上传文件类型
fileBean.setSuffix(".gif.jpg.html.htm.txt.swf");
//获取输入
fileBean.setSourceFile(request);
String [] saSourceFile = fileBean.getSourceFile();
String [] saObjectFile = fileBean.getObjectFileName();
String [] saDescription = fileBean.getDescription();
int iCount = fileBean.getCount();
String sObjectPath = fileBean.getObjectPath();
/**
for(int i=0;i<iCount;i++) {
  out.println("<br>源始文件:");
  out.println(saSourceFile[i]);
  out.println("<br>目标文件:");
  out.println(sObjectPath+saObjectFile[i]);
  out.println("<br>上传描述:");
  out.println(saDescription[i]);
  out.println("<br>");
}
**/
%>
<table width="100%" border="0">
<%
String CLASSID = fileBean.getFieldValue("CLASSID");
String PNAME = fileBean.getFieldValue("PNAME");
String PDESC = fileBean.getFieldValue("PDESC");
String PUSE = fileBean.getFieldValue("PUSE");
String PREPRICE = fileBean.getFieldValue("PREPRICE");
String CURRPRICE = fileBean.getFieldValue("CURRPRICE");
String AMOUNT = fileBean.getFieldValue("AMOUNT");
String STATUS = fileBean.getFieldValue("STATUS");
String MODEL = fileBean.getFieldValue("MODEL");
String BRANDNAME = fileBean.getFieldValue("BRANDNAME");

String POSTFEE = fileBean.getFieldValue("POSTFEE");
String UNIT = fileBean.getFieldValue("UNIT");
String ATTACHMENT = fileBean.getFieldValue("ATTACHMENT");
String ONLINEDATE = fileBean.getFieldValue("ONLINEDATE");

%>

<tr>
  <td width="23%" align="right"><img src="upload/<%=saObjectFile[1]%>"></td>
  <td width="77%">
  <img src="upload/<%=saObjectFile[0]%>" width="200" higth="200">
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品名称:</td>
  <td width="77%">
  <%=PNAME%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品描述:</td>
  <td width="77%">
  <%=PDESC%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品用途:</td>
  <td width="77%">
  <%=PUSE%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品原价格:</td>
  <td width="77%">
  <%=PREPRICE%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品现在价格:</td>
  <td width="77%">
  <%=CURRPRICE%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">库存数:</td>
  <td width="77%">
  <%=AMOUNT%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">是否上架:</td>
  <td width="77%">
  <%if(STATUS==null) out.print("不上架"); else out.print("上架");%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商品型号:</td>
  <td width="77%">
  <%=MODEL%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">商标:</td>
  <td width="77%">
  <%=BRANDNAME%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">单位:</td>
  <td width="77%">
  <%=UNIT%>
  </td>
</tr>
<tr>
  <td width="23%" align="right">附件说明:</td>
  <td width="77%">
  <%=ATTACHMENT%>
  </td>
</tr>

</table>

</body>
</html>