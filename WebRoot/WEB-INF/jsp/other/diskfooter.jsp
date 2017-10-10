<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script>
	function getContents(){
		$.post(ctx+"/diskhome/${userview.username}/detail/${userroot.folderid}", function(result) { 
			alert("dsadd24245f+"+window.location.host);
	    	  $.ajax({url:window.location.host+"/ejs/disk.ejs",success:function(template){
					var html=ejs.render(template, {data:result});//json转html
					document.getElementById('diskbody').innerHTML = html;//重写
				}
			});
	      }, 'json');
	}
</script>