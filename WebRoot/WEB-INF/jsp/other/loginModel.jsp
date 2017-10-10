<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 登录注册模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
	<div class="modal-header">
		<!-- tab页头 -->
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#loginpage" data-toggle="tab">登录</a></li>
			<li><a href="#registpage" data-toggle="tab">注册</a></li>
			<button type="button" class="btn btn-default pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
		</ul>
		<!-- tab页内容 -->
		<div id="myTabContent" class="tab-content" style="margin-top: 15px">
			<!-- 登录form -->
			<div class="tab-pane fade in active" id="loginpage">
				<form id="loginForm" class="form-horizontal">
					<h4 class="text-danger text-center hide" id="loginError"></h4>			
					<div class="form-group">
						<label class="col-sm-2 control-label"> 账号：</label>
						<div class="col-sm-9">
						<input class="form-control" placeholder="输入账号" type="text" name="username"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"> 密码：</label>
						<div class="col-sm-9">
						<input class="form-control" placeholder="输入密码" type="password" name="password"/>
						</div>
					</div>
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label" id="captchaOperation"></label>
						<div class="col-sm-9">
							<input type="text" class="form-control" placeholder="输入答案" name="captcha" />
						</div>
					</div>
					<div class="form-group">
			    		<div class="col-sm-offset-2 col-sm-9">
			      			<button id="submitBtn" class="btn btn-primary">登录</button>
			      			<button id="resetBtn" type="reset" class="btn btn-info">重置</button>
			    		</div>
			  		</div>
				</form>
			</div>
			<!-- 注册form -->
			<div class="tab-pane fade" id="registpage">
				<form id="registForm" class="form-horizontal" enctype="multipart/form-data">
					<h4 class="text-danger text-center hide" id="registError"></h4>
					<div class="form-group">
						<label class="col-sm-2 control-label"> *账号：</label>
			            <div class="col-sm-9">
			            <input class="form-control" id="username" placeholder="输入账号" type="text" name="username"/>
						</div>	
					</div>
			        <div class="form-group">
			            <label  class="col-sm-2 control-label"> *密码：</label>
			            <div class="col-sm-9">
			            <input class="form-control" placeholder="输入密码" type="password" name="password"/>
			            </div>
			        </div>
			      	<div class="form-group">
			            <label class="col-sm-2 control-label"> *确认密码：</label>
			            <div class="col-sm-9">
			            <input class="form-control" placeholder="再次输入密码" type="password" name="repassword"/>
			            </div>
			        </div>
			        <div class="form-group">
			            <label class="col-sm-2 control-label"> 用户名：</label>
			            <div class="col-sm-9">
			            <input class="form-control" placeholder="输入昵称" type="text" name="name"/>
			            </div>
			        </div>
			        <div class="form-group">
			            <label class="col-sm-2 control-label">*性别：</label>
			            <div class="col-sm-9">
						<label class="radio-inline">
							<input type="radio" name="gender" value="男" checked="checked"/>男
						</label>
						<label class="radio-inline">
						  	<input type="radio" name="gender" value="女"/>女
						</label>
						</div>
			        </div>
			        <div class="form-group">
			            <label class="col-sm-2 control-label"> E-mail：</label>
			            <div class="col-sm-9">
			            <input class="form-control" placeholder="输入邮箱" type="text" name="email"/>
			            </div>
			        </div>
			        <div class="form-group">
			        	<label class="col-sm-2 control-label">*年龄：</label>
			        	<div class="col-sm-9">
			            <input class="form-control" placeholder="输入年龄" type="text" name="age"/>
			            </div>
			        </div>
			        <div class="form-group">
			        	<label class="col-sm-2 control-label"> *验证码：</label>
			        	<div class="col-sm-9">
			            <input id="checkcode" type="text" placeholder="输入验证码" name="checkcode" maxlength="4"/> <img id="checkImg" data-toggle="tooltip" data-placement="bottom" class="captchaImage" src="${pageContext.request.contextPath}/home/getImg" onclick="change()" title="点击更换验证码"/>
			            </div>
			        </div>
			        <div class="form-group">
			    		<div class="col-sm-offset-2 col-sm-9">
			      			<button id="submitBtn1" class="btn btn-primary">注册</button>
			      			<button id="resetBtn1" type="reset" class="btn btn-warning">重置</button>
			    		</div>
			  		</div>
			  	</form>	
			</div>
		</div>
	</div>   
	</div>
	</div>
	</div>
<script src="${pageContext.request.contextPath}/ejs/ejs.js"></script>
<script>
function change(){
	var img1 = document.getElementById("checkImg");
	img1.src="${pageContext.request.contextPath}/home/getImg?xxx="+new Date().getTime();
}

$(document).ready(function() {
    //Generate a simple captcha
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
    
    $('#loginForm')
    .bootstrapValidator({
	//live: 'disabled',
      message: 'This value is not valid',
      submitButtons: '#submitBtn',
      verbose: false,//只显示一个错误信息
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
    	  username: {
      		validators: {  
                  notEmpty: {},
                  stringLength: {
                      min: 6,
                      max: 30
                  },
                  regexp: {
                      regexp: /^[a-zA-Z0-9_\.]+$/,
                      message: "用户名只能由字母、数字、点和下划线组成"
                  }  
              }  
      	},
      	password: {
            validators: {
                notEmpty: {},
                stringLength: {
                    min: 6,
                    max: 30
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9_\.]+$/,
                    message: "用户名只能由字母、数字、点和下划线组成"
                }
            }
        },
          captcha: {
              validators: {
                  callback: {
                      message: 'Wrong answer',
                      callback: function(value, validator) {
                          var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                          return value == sum;
                      }
                  }
              }
          }
      },
  })
  .on('success.form.bv', function(e) {
      // Prevent form submission
      e.preventDefault();

      // Get the form instance
      var $form = $(e.target);

      // Get the BootstrapValidator instance
      var bv = $form.data('bootstrapValidator');

      // Use Ajax to submit form data
      $.post("${pageContext.request.contextPath}/user/login", $form.serialize(), function(result) {
    	// The result is a JSON formatted by your back-end  
          // I assume the format is as following:  
          //  {  
          //      valid: true,          // false if the account is not found  
          //      username: 'Username', // null if the account is not found  
          //  } 
    	  //alert(JSON.stringify(result));
          if (result.valid == true || result.valid == 'true') {  
              // You can reload the current location  
              //window.location.reload();
              $('#loginForm').data('bootstrapValidator').resetForm(true);
              
              // Or use Javascript to update your page, such as showing the account name  
              $('#myModal').modal('hide');
              // $('#welcome').html('Hello ' + result.username);  
              
				//用户信息显示		
				/*$.ajax({url:"${pageContext.request.contextPath}/ejs/user.ejs",success:function(template){
						var html=ejs.render(template, {user:result.data});//json转html
						document.getElementById('userline').innerHTML = html;//重写
					}
				});*/
				window.location.reload();
          } else {  
              // The account is not found  
              // Show the errors  
              $('#loginError').html('The account is not found').removeClass('hide');  
              // Enable the submit buttons  
              $('#loginForm').bootstrapValidator('disableSubmitButtons', false);
              $('#loginForm').data('bootstrapValidator').resetField('captcha',true);
              $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
          }	
      }, 'json');
  });
    $('#resetBtn').click(function() {
        $('#loginForm').data('bootstrapValidator').resetForm(true);
    });
    
    $('#registForm')
    .bootstrapValidator({
	//live: 'disabled',
      message: 'This value is not valid',
      submitButtons: '#submitBtn1',
      verbose: false,
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
		username: {
      		validators: {  
                  notEmpty: {  },
                  stringLength: {
                      min: 6,
                      max: 30
                  },
                  regexp: {
                      regexp: /^[a-zA-Z0-9_\.]+$/,
                      message: "用户名只能由字母、数字、点和下划线组成"
                  },
                  remote: {  
                      url : '${pageContext.request.contextPath}/user/checkname',  
                      message : "该用户名已经存在",  
                      delay : 2000,  
                      type : 'post',
                      data:{
                          username:function(){ return $("#username").val(); }
                     }
                  },
                  different: {  
                      field: 'password',  
                      message: "用户名和密码不能相同"
                  }  
              }
      	},
		password: {
              validators: {
                  notEmpty: {},
                  stringLength: {
                      min: 6,
                      max: 30
                  },
                  different: {
                      field: 'username',
                      message: "用户名和密码不能相同"
                  },
                  regexp: {
                      regexp: /^[a-zA-Z0-9_\.]+$/,
                      message: "用户名只能由字母、数字、点和下划线组成"
                  }
              }
          },
          repassword: {
              validators: {
              	notEmpty: {},
                  stringLength: {
                      min: 6,
                      max: 30
                  },
              	identical: {
                      field: 'password',
                      message: "两次输入密码不一致"
                  },
                  different: {
                      field: 'username',
                      message: "用户名和密码不能相同"
                  },
                  regexp: {
                      regexp: /^[a-zA-Z0-9_\.]+$/,
                      message: "用户名只能由字母、数字、点和下划线组成"
                  }
              }
          },
          name: {
              validators: {
              	stringLength: {
                      max: 10
                }
              }
          },
      	email: {
              validators: {
                  emailAddress: {}
              }
          },
        age: {
              validators: {
            	  notEmpty: {},
            	  stringLength: {
                      max: 3
                  }
              }
          },
      	checkcode:{
      		validators: { 
      			notEmpty: {},
      			remote: {  
                      url : '${pageContext.request.contextPath}/home/checkCode',  
                      message : "验证码输入错误",  
                      delay : 500,  
                      type : 'post',
                      data:{
                      	checkcode:function(){ return $("#checkcode").val(); }
                      }
                  }			        		
      		}
      	}
      },
  })
  .on('success.form.bv', function(e) {
      e.preventDefault();
      var $form = $(e.target);
      var bv = $form.data('bootstrapValidator');
      $.post("${pageContext.request.contextPath}/user/regist", $form.serialize(), function(result) {
          if (result.valid == true || result.valid == 'true') {
              $('#registForm').data('bootstrapValidator').resetForm(true);
              $('#myModal').modal('hide');
              
				//用户信息显示		
				/*$.ajax({url:"${pageContext.request.contextPath}/ejs/user.ejs",success:function(template){
						var html=ejs.render(template, {user:result.data});//json转html
						document.getElementById('userline').innerHTML = html;//重写
					}
				});*/
				window.location.reload();
          } else {    
              $('#registError').html("未知的错误！").removeClass('hide');
              $('#registForm').data('bootstrapValidator').resetForm(true);
          }	
      }, 'json');
  });
    $('#resetBtn1').click(function() {
        $('#registForm').data('bootstrapValidator').resetForm(true);
    });
});
</script>