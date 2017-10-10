<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>云网盘</title>
	
	<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
	<script src="${ctx}/bootstrap/js/jquery2.min.js"></script>
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="${ctx}/bootstrapvalidator/dist/css/bootstrapValidator.css"/>
    <script src="${ctx}/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
    <script src="${ctx}/bootstrapvalidator/dist/js/language/zh_CN.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/bootstrap-fileinput-master/css/fileinput.min.css" />
    <script type="text/javascript" src="${ctx}/bootstrap-fileinput-master/js/fileinput.min.js"></script>
    <script type="text/javascript" src="${ctx}/bootstrap-fileinput-master/js/locales/zh.js"></script>
    
	<link rel="stylesheet" href="${ctx}/css/style.css">

<input type="hidden" id="ctxValue" value="${ctx}"/>
<script>
var ctx=$('#ctxValue').val();
var parentid=${userroot.folderid};
</script>
</head>
<body onload="getContents(parentid)">
<div class="navbar-fixed-top1">
<div class="container">
	<div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="${pageContext.request.contextPath}/home/index?page=1&typeid=0" class="navbar-brand">云网盘</a>
    </div>
	<nav>

	<ul class="nav navbar-nav navbar-right">
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
</div>>
<div class="navbar-fixed-left">
	<nav>  
		<ul class="nav nav-pills nav-stacked" style="padding-top: 50px">  
			<li><a href="${pageContext.request.contextPath}/user/${sessionScope.existUser.username}/diskhome"><span class="glyphicon glyphicon-home" style="padding-left: 5px"> 全部<span></a></li>
			<li><a href="#section2"><span class="glyphicon glyphicon-picture" style="padding-left: 5px"> 图片<span></a></li>
			<li><a href="#section3"><span class="glyphicon glyphicon-book" style="padding-left: 5px"> 文档<span></a></li>
			<li><a href="#section1"><span class="glyphicon glyphicon-film" style="padding-left: 5px"> 视频<span></a></li>
			<li><a href="#section2"><span class="glyphicon glyphicon-headphones" style="padding-left: 5px"> 音乐<span></a></li>
			<li><a href="#section3"><span class="glyphicon glyphicon-file" style="padding-left: 5px"> 其他<span></a></li>
		</ul>  
	</nav>
</div>
<div class="content">
	<div class="container-fluid" style="padding-top : 10px">
		<div>
			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal"><span class="glyphicon glyphicon-open"></span> 上传</button>
			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#newFolderModal"><span class="glyphicon glyphicon-plus"></span> 新建文件夹</button>
		</div>
		<ol class="breadcrumb" id="breadcrumb" style="padding-top: 20px">
			<li><a onclick="getContents(${userroot.folderid})">Home</a></li>
		</ol>
		<table class="table table-striped table-hover">
			<tbody id="diskbody">
			</tbody>
		</table>
	</div>
</div>

<!-- 上传文件模态框 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog modal-lg" role="document">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title">请选择文件<small><label class="radio-inline"><input type="radio" name="shareradio" value="0" checked="checked"/>个人</label><label class="radio-inline"><input type="radio" name="shareradio" value="1"/>共享</label></small></h4>
	</div>
	<div class="modal-body">
		<div class="file-loading">
			<input id="input-id" name="fileupload" type="file" multiple>
		</div>
		<div id="kv-error-2" style="margin-top:10px;display:none"></div>
	</div>
</div>
</div>
</div>
<!--新建文件夹模态框 -->
<div class="modal fade" id="newFolderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog" role="document">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title">输入文件名</h4>
	</div>
	<div class="modal-body">
		<form id="saveFolder" class="form-horizontal">
			<div class="form-group">
				<div class="col-sm-12">
					<input class="form-control" placeholder="输入文件夹名" type="text" name="foldername" value="新文件夹"/>
				</div>
			</div>
			<button type="button" class="btn btn-info btn-sm" onclick="save()">确认</button>
		</form>
	</div>
</div>
</div>
</div>
<%@ include file="other/loginModel.jsp" %>
<div id="footer">
</div>
<script>
	function getContents(e){
		$.post(ctx+"/diskhome/${userview.username}/detail/"+e, function(result) { 
	    	  $.ajax({url:ctx+"/ejs/diskbody.ejs",success:function(template){
					var diskbody=ejs.render(template, {data:result});//json转html
					document.getElementById('diskbody').innerHTML = diskbody;//重写
					setparentid(e);
					/*var html="<li><a onclick=\"getContents("+e+")\">"+b+"</a></li>";
					document.getElementById("breadcrumb").innerHTML+=html;*/
				}
			});
	      }, 'json');

	};
	
	function setparentid(e){
		parentid=e;
	}
	
	function deletefolder(e){
		$.post(ctx+"/diskownhome/${userview.username}/deleteFolder/"+e, function(result) { 
	    	 if(result.valid){
	    		 alert("删除成功");
	    		 window.location.reload();}
	      }, 'json');
	}
	
	function updatefolder(e,islock) {
		$.post(ctx+"/diskownhome/${userview.username}/updateFolder/"+e+"?islock="+islock, function(result) { 
	    	 if(result.valid){
	    		 alert("更改成功");
	    		 window.location.reload();}
	      }, 'json');
	}
	
	function deleteuserres(parentid,id){
		$.post(ctx+"/userresown/"+parentid+"/deleteUserres/"+id, function(result) { 
	    	 if(result.valid){
	    		 alert("删除成功");
	    		 window.location.reload();}
	      }, 'json');
	}
	
	function updateuserres(parentid,id,islock) {
		$.post(ctx+"/userresown/"+parentid+"/updateUserres/"+id+"?islock="+islock, function(result) { 
	    	 if(result.valid){
	    		 alert("更改成功");
	    		 window.location.reload();}
	      }, 'json');
	}
	
	function save(){
    	$.ajax({
    		type: "POST",
    		url:  ctx+'/diskownhome/${userview.username}/insertFolder/'+parentid,
    		data: $('#saveFolder').serialize(),// 你的formid
    		async: false,
    		error: function(request) {
    			alert("Connection error");
    		},
    		success: function(data) {
    			alert("dasd");
    			if(data.valid){
    			$('#newFolderModal').modal('hide');
    			$.ajax({url:ctx+"/ejs/folder.ejs",success:function(template){
					var newfolder=ejs.render(template, {folder:data.data});//json转html
					alert("新建文件夹成功");
					document.getElementById('diskbody').innerHTML = newfolder+document.getElementById('diskbody').innerHTML;
				}
			});}
    		}
    	});
	}
	
	$(document).ready(function() {
			$("#input-id").fileinput({
	    	language: 'zh',
	        //showCaption: true,
	        //showBrowse: true,
	        //showPreview: true,
	        //showRemove: true,
	        //showUpload: true,
	        //showCancel: true,
	        showClose: false,
	        //deleteUrl: '',
	        //deleteExtraData: {},
	        //allowedFileTypes: null,
	        uploadUrl: "${pageContext.request.contextPath}/diskownhome/${userview.username}/upload/"+parentid,
	        //uploadAsync: true,
	        uploadExtraData:  function(previewId, index) {   //额外参数的关键点
	            //var obj = {};
	            //obj.fodder = fodderType();
	            //console.log(obj);
	            //var obj=parentid;
	            var radio=document.getElementsByName("shareradio");
				var selectvalue=null;   //  selectvalue为radio中选中的值
				for(var i=0;i<radio.length;i++){
					if(radio[i].checked==true) {
						selectvalue=radio[i].value;
						break;
                   }
				}
	            var obj={"parentid":parentid,"share":selectvalue}
	            return obj;
	        },
	        //minImageWidth: null,
	        //minImageHeight: null,
	        //maxImageWidth: null,
	        //maxImageHeight: null,
	        //minFileSize: 0,
	        maxFileSize: 40960,
	        //minFileCount: 0,
	        maxFileCount: 3,
	        elErrorContainer: '#kv-error-2',
	        dropZoneEnabled: true,
	    }).on('fileuploaded', function(event, data) {
	        $('#input-id').fileinput('refresh');
	        $('#exampleModal').modal('hide');
	        //上传
		        $.ajax({url:ctx+"/ejs/resourse.ejs",success:function(template){
					var newRes=ejs.render(template, {resourse:data.jqXHR.responseJSON.data});//json转html
					alert("上传成功");
					document.getElementById('diskbody').innerHTML +=newRes;
				}
			});
	    });
	});
</script>
</body>
</html>
