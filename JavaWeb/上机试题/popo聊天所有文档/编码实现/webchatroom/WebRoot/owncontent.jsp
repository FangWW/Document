<%@page contentType="text/html" language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%
  	
    String note=null;
	List list=(List)session.getAttribute("ownMessage");
	if(list==null)
	{
	%>
		<h4><label>还没有人跟你私聊</label></h4>
	<% 
	}
	else{
		Iterator all=list.iterator();
		while(all.hasNext())
		{
			note=(String)all.next();
	%>
			<%=note %>
	<% 
		}
	}
	%>

