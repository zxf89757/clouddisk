<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${sessionScope.existUser == null}">
    	<div id="userline"><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">登录&注册</button></div>
    </c:when>
    <c:otherwise><div>
    	<a href="${pageContext.request.contextPath}/user/${sessionScope.existUser.username}/diskhome">${sessionScope.existUser.name}的网盘</a>
		<a href="${pageContext.request.contextPath}/user/quit">退出</a></div>
    </c:otherwise>
</c:choose>