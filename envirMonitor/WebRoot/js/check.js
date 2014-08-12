function checkUser(){//检查用户名和密码不为空
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(username==""||password==""){
		alert("用户名和密码不可为空");
		return false;
	}else{
		return true;
	}
}
function findPassword(){
var username=document.getElementById("username").value;
var flag=document.getElementById("flag").value;
var uid="0";
if(flag=="2"){
	uid=document.getElementById("uid").value;
}
if(username==""){//没有填写用户名，则返回
	alert("请您填写您的用户名，谢谢！");
}else{
	window.location.href="../../jsp/findPassword.jsp?username="+username+"&flag="+flag+"&uid="+uid;
	
}
}