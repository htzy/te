<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>QUI网页界面集成框架入门版</title>
<link href="skin/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript" src="../../libs/js/jquery.js"></script>
<script type="text/javascript" src="../../libs/js/login.js"></script>

<!--居中显示start-->
<script type="text/javascript" src="../../libs/js/method/center-plugin.js"></script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
</style>
</head>
<body >
	<div class="login_main">
		<div class="login_top">
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form id="loginForm" action="../../UserValidateServlet" class="login_form" method="post" onsubmit="login()">
					<div class="login_user"><input type="text" id="username" name="username" value="zhou"></div>
					<div class="login_pass"><input type="password" id="password" name="password" value="2"></div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="submit" value="" onfocus="this.blur()" /></div>
						<div class="login_button_right"><input type="reset" value="" onfocus="this.blur()" /></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info" style="display:none;"></div>
					<div class="login_info2"></div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">
			<!-- 版权所有：http://www.quickui.net -->
			</div>
		</div>
	</div>
<script>
	$(function(){
		//居中
		 $('.login_main').center();
		 document.getElementById("username").focus(); //聚焦到username 
	})
	//登录
	function login() {
		var loginName = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if(loginName==""||password==""){
		 alert("用户名和密码不可为空！");
		 return false;
		}else{
		 return true;
		}
	}
</script>
</body>
</html>

