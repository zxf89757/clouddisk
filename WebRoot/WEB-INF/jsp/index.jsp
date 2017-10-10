<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>XXX网盘</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery2.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapvalidator/dist/css/bootstrapValidator.css"/>
    <script src="${pageContext.request.contextPath}/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrapvalidator/dist/js/language/zh_CN.js"></script>
    
    <script src="${pageContext.request.contextPath}/bootstrap-treeview/dist/bootstrap-treeview.min.js"></script>
</head>
<body style="background:rgb(250,250,250)">
<div class="container-fluid">
<div class="navbar navbar-fixed-top">
<div class="navbar-inner" style="background:rgb(234,234,234)">
<div style="margin:0 auto; width:90%;">
	<div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=0" class="navbar-brand">云网盘</a>
    </div>
     <nav id="bs-navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li>
          <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=1">图片</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=2" >文档</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=3" >视频</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=4">音乐</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=5">其他</a>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li>
         <form class="navbar-form navbar-left" role="search" action="#"  method="get">
			<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> 提交</button>
			<div class="form-group">
				<input type="text" class="form-control" name="searchValue" placeholder="Search">
				<input type="hidden" name="page" value=1>
			</div>
		</form>
        </li>
        <li><c:choose>
				<c:when test="${sessionScope.existUser == null}">
			    	<div id="userline"><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">登录&注册</button></div>
			    </c:when>
			    <c:otherwise>
			    	<a href="${pageContext.request.contextPath}/user/${sessionScope.existUser.username}/diskhome">${sessionScope.existUser.name}的网盘</a></li>
			    	<li><a href="${pageContext.request.contextPath}/user/quit">退出</a>
			    </c:otherwise>
			</c:choose>
		</li>
		<li><a href="#top"><span data-toggle="tooltip" data-placement="bottom" title="回顶部" class="glyphicon glyphicon-chevron-up"></span></a></li>
      </ul>
    </nav>
</div>
</div>
</div>
</div>
<div id="top" ></div>
<div class="container" style="margin-top: 55px">
<div class="row">
   		<div class="col-md-12" style="padding-top: 15px;padding-bottom: 15px;">
			<h4 class="text-success">最新上传</h4>
		</div>
	</div>
	<table class="table table-hover">
    	<tbody>
    	<c:forEach items="${resourse}" var="node">
	    	<tr>
	    		<td title="类型">${ node.res.typeid}</td>
				<td title="标题">${ node.res.name}</td>
				<td>${ node.res.describe}</td>
				<td title="大小">${ node.res.size}M</td>
				<td title="时间">${ node.res.createdata}</td>
				<td title="被保存数">${ node.res.savedtime}</td>
				<td title="上传者"><a href="${pageContext.request.contextPath}/user/${ node.user.username}/diskhome">${ node.user.name}</a></td>
				<td><button type="button"  class="btn btn-warning btn-xs" onclick="addToMyDisk(${ node.res.id})">加入网盘</button></td>
	    	</tr>
    	</c:forEach>
    	</tbody>
	</table>
	
	<div style="background: rgb(240,240,240);">
    <ul class="pagination">
    	<c:if test="${pageBean.totalPage>0}">
	    	<li>
	    		<a href="${pageContext.request.contextPath}/home/index?typeid=${typeid}&page=1">首页</a>
			</li>
		</c:if>
		<c:if test="${pageBean.page>1}">
			<li>
			<a aria-label="Previous" href="${pageContext.request.contextPath}/home/index?typeid=${typeid}&page=${pageBean.page-1}"><span aria-hidden="true">&laquo;</span></a>
			</li>
		</c:if>
		<c:choose>
			<c:when test="${pageBean.totalPage <= 10 }">
		    	<c:set var="begin" value="1"/>
		    	<c:set var="end" value="${pageBean.totalPage }"/>
			</c:when>
			<c:otherwise>
		    	<c:set var="begin" value="${pageBean.page-5 }"/>
		    	<c:set var="end" value="${pageBean.page+4 }"/>
		    	<c:if test="${begin<1 }">
		        	<c:set var="begin" value="1"/>
		        	<c:set var="end" value="10"/>
				</c:if>
				<c:if test="${end>pageBean.totalPage }">
		        	<c:set var="begin" value="${pageBean.totalPage-9 }"/>
		        	<c:set var="end" value="${pageBean.totalPage }"/>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:forEach  var="i" begin="${begin }" end="${end }">
			<c:choose>
				<c:when test="${i eq pageBean.page }">
					<li class="active">
						<span>${i }<span class="sr-only">(current)</span></span>
					</li>
				</c:when>
				<c:otherwise>
					<li>
		        	<a href="${pageContext.request.contextPath}/home/index?typeid=${typeid}&page=${i }">${i }</a>
		    		</li>
		    	</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageBean.page<pageBean.totalPage}">
			<li>
			<a aria-label="Next" href="${pageContext.request.contextPath}/home/index?typeid=${typeid}&page=${pageBean.page+1}"><span aria-hidden="true">&raquo;</span></a>
			</li>
		</c:if>
		<c:if test="${pageBean.totalPage>1}">
	    	<li>
				<a href="${pageContext.request.contextPath}/home/index?typeid=${typeid}&page=${pageBean.totalPage}">尾页:${pageBean.totalPage}</a>
			</li>
		</c:if>
	</ul>
	</div>
</div>

<!-- 保存文件到我的网盘模态框 -->
<div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog" role="document">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title">请选择文件夹</h4>
	</div>
	<div class="modal-body" id="savemodalbody">
		
	</div>
</div>
</div>
</div>
<%@ include file="other/loginModel.jsp" %>
<script>
function addToMyDisk(e){
	var res_id=e;
	/*$.post("${pageContext.request.contextPath}/diskownhome/xxx/addToDisk/xxx", function(result) { 
   	 if(result.valid){
   		var datatree=result.data;
   		alert(JSON.stringify(datatree));
   		alert("更改成功");}
   		$('#savemodalbody').treeview({datatree: datatree});
   		$('#saveModal').modal('show');
     }, 'json');*/
	$.post("${pageContext.request.contextPath}/user/addToDisk?id="+res_id, function(result) { 
	   	 if(result.valid){
	   		 alert("加入成功");
	     }
	   	 else{
	   		alert(result.error);
	   	 }
	     }, 'json');
}
</script>
</body>
</html>