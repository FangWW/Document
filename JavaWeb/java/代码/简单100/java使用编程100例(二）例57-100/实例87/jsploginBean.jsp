<%@ page contentType="text/html; charset=GB2312"%>
<jsp:useBean id="myBean" scope="page" class="com.bean.myBean" />
<jsp:setProperty name="myBean" property="*" />
<%
int iResult = myBean.check();
if(iResult==1){ 
%>
登录成功！ 欢迎<%=myBean.getNAME()%>进入Java世界。
<%
}
if(iResult==0){
%>
登录失败！<%=myBean.getNAME()%>没有这个角色。 点击<a href="javascript:history.back(-1);">这里</a>重新登录！ 
<%
}
if(iResult==-1){
%>
登录失败！<%=myBean.getNAME()%>的密码不正确。点击<a href="javascript:history.back(-1);">这里</a>重新登录！ 
<%
}
if(iResult==-2){
%>
登录失败！没有<%=myBean.getNAME()%>用户。点击<a href="javascript:history.back(-1);">这里</a>重新登录！ 
<%
}
%>