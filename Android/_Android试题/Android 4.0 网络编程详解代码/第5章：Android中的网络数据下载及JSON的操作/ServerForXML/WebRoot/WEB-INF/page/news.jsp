<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><?xml version="1.0" encoding="UTF-8"?>
<newslist>
	<c:forEach items="${newes}" var="news">
		<news id="${news.id}">
			<title>${news.title}</title>
			<timelength>${news.timelength}</timelength>
		</news>
	</c:forEach>
</newslist>