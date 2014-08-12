<%@ page language="java" import="java.util.*,com.monitor.java.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<!-- 框架必需start -->
<script type="text/javascript" src="<%=basePath%>libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>libs/js/framework.js"></script>
<link href="<%=basePath%>libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/fontShow.css"/>
<!-- 框架必需end -->

<!--数字分页start-->
<script type="text/javascript" src="<%=basePath%>libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->

<!-- 表单验证start -->
<script src="<%=basePath%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=basePath%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->


<script>
	//初始化界面
	var fixObjHeight = 110;
	//sum全局变量，用于记录最大的行数
	var sum = 0;
	function initComplete() {
		$("#searchPanel").bind("stateChange", function(e, state) {
			if (state == "hide") {
				fixObjHeight = 95;
			} else if (state == "show") {
				fixObjHeight = 130;
			}
			triggerCustomHeightSet();
		});
	}
	function customHeightSet(contentHeight) {
		$("#scrollContent").height(contentHeight - fixObjHeight);
	}
</script>
</head>
<body>

<%
	//获取session中optionsForUnitType，optionsForFather数据，并清空session
	String optionsForUnitType = (String)session.getAttribute("optionsForUnitType");
	String optionsForFather = (String)session.getAttribute("optionsForFather");
	
	session.setAttribute("optionsForUnitType",null);
	session.setAttribute("optionsForFather",null);
	
 %>
	<!-- 显示将要添加进数据库的条目开始 -->
	<div id="scrollContent" style="overflow-x:hidden;">
		<form action="<%=basePath%>servlet/AddUnitServlet" id="unitForm"
		target="frmright" method="post" enctype="multipart/form-data">
			<table class="tableStyle" useClick="false" mode="list" id="list">
				<tr>
				<th width="10%">单元名称</th>
				<th width="10%">单元类型</th>
				<th width="10%">父节点</th>
				<th width="10%">坐标X</th>
				<th width="10%">坐标Y</th>
				<th width="10%">宽度</th>
				<th width="10%">高度</th>
				<th width="10%">背景图片</th>
				<th width="10%">缩略图</th>
				<th width="10%">删除</th>
			</tr>
			</table>
		</form>
	</div>
	
	<!-- 显示将要添加进数据库的条目结束 -->
	
	<!-- 显示保存取消按钮，点击保存则数据被提交进数据库开始 -->
	<form action="<%=basePath%>servlet/AddUnitServlet" id="addForm"
		target="frmright" method="post" >
		<input type="hidden" id="units" name="units" value="" />
		
		<div class="box1" whiteBg="true">
			<div align="center">
				<input type="button" value="添加" onclick="addHandler()" />
				<input type="button" id="add" name="add" value="保存" onclick="addUnit()" />
				<input type="button" value="取消" onclick="cancel()"/>
			</div>
		</div>
	</form>
	<!-- 显示保存取消按钮，点击保存则数据被提交进数据库结束 -->

	<script>
		var tdText0;
		var tdText1;
		var $currentTr;
		
		function initComplete() {
			addHandler();
		}
		/**
		 * 验证表单函数
		 */
		//手动触发验证，被验证的表单元素是containerId容器里的。 可以验证整个表单，也可以验证部分表单。
		function validateForm(containerId1) {
			var valid1 = $("#unitForm").validationEngine({
				returnIsValid : true
			});
			return valid1;
		}

		//删除一行信息
		function delHandler($obj){
			$currentTr = $obj.parents("tr");

			$currentTr.remove();
		}

		//添加一行信息
		function addHandler() {
			var $tr = addNewRowHandler();
			sum++;
			$("#list").append($tr);
			//重新渲染表格
			$("#list").render();

		}

		//添加行处理，显示在界面上
		function addNewRowHandler(){
			var $tr = $("<tr>"
					+ "<td width='10%'><div><input style='width:105px;' id='unitName"+sum+"' class='validate[required,custom[illegalLetter]]' name='unitName' "+"value='' /><div></td>"
					+ "<td width='10%'><div><select id='unitTypeName"+sum+"' name='unitTypeName' ><%=optionsForUnitType%></select></div></td>"
					+ "<td width='10%'><div><select id='fatherUnitName"+sum+"' name='fatherUnitName' ><%=optionsForFather%></select></div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='x"+sum+"' class='validate[required,custom[onlyNumber]]' name='x' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='y"+sum+"' class='validate[required,custom[onlyNumber]]' name='y' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='width"+sum+"' class='validate[required,custom[onlyNumber]]' name='width' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='height"+sum+"' class='validate[required,custom[onlyNumber]]' name='height' "+"value='' /><div></td>"
					+ "<input type='hidden' id='pictureName"+sum+"' name='pictureName' value='' />"
					+ "<td width='10%'><div><input style='width:105px;' id='picture"+sum+"' type='file' name='picture' /></div></td>"
					+ "<input type='hidden' id='thumbnailsName"+sum+"' name='thumbnailsName' value='' />"
					+ "<td width='10%'><div><input style='width:105px;' id='thumbnails"+sum+"' type='file' name='thumbnails' /></div></td>"
					+ "<td width='10%'><span class='img_delete hand' title='删除'></span></td></tr>");
			var $modiBtn = $tr.find(".img_edit");
			var $delBtn = $tr.find(".img_delete");
			var $upBtn = $tr.find(".img_btn_up");
			$delBtn.click(function() { 
				delHandler($(this));
			});
			$modiBtn.click(function() {
				modiHandler($(this));
			});
			enableTooltips();
			return $tr;
		}

		//取消
		function cancel() {
			window.location.href="../../servlet/ShowAllUnitServlet";
		}

		//执行增加操作
		function addUnit(){
			//检查是否输入非法字符
			var flag = validateForm("#unitForm");
			if (flag == false){
				alert("表单填写不正确，请按要求填写！");
				return;
			}

			//检查输入是否为空并拼接结果集
			var units = "";
			var temp ;
			for ( var i = 0; i < sum; i++){
				//如果该条记录存在，则往下进行
				if(checkIsExist(i)){
					temp = checkNotNull(i);
					if(temp == false){
						alert("还有未填项或图片格式不为jpg,png或gif，请检查重试！");
						return;
					}else{
						units += getUnit(i);
					}
				}
			}
			
			//如果没有添加任何数据项，则不处理
			if(units==""){
				return;
			}
			
			
			//$("#units").val(units);
			//$("#sum").val(sum);
			document.getElementById("unitForm").submit();
			setTimeout("closeWin()", 100);
			return true;
		}
		
		/**
		 * 检查是否存在该记录,可能该条记录被删除，故不存在
		 */
		function checkIsExist(i){
			var res = document.getElementById("unitName"+i);
			if(res == null){
				return false;
			}
			return true;
		}
		
		/**
		 * 检查一条记录中的各项是否为空
		 */
		function checkNotNull(i){
			if(document.getElementById("unitName"+i).value == ""){
				return false;
			}
			if(document.getElementById("unitTypeName"+i).value == ""){
				return false;
			}
			if(document.getElementById("fatherUnitName"+i).value == ""){
				return false;
			}
			if(document.getElementById("x"+i).value == ""){
				return false;
			}
			if(document.getElementById("y"+i).value == ""){
				return false;
			}
			if(document.getElementById("width"+i).value == ""){
				//width可为空，当为空时，向后台传"null"
				$("#width"+i).val("null");
			}
			if(document.getElementById("height"+i).value == ""){
				//height可为空，当为空时，向后台传"null"
				$("#height"+i).val("null");
			}
			//图片检查后缀，必须为jpg,png,gif格式的照片
		 	var name = "";
		 	//检查picture
		 	name = document.getElementById("picture"+i).value;
		 	//获取文件后缀
		 	name = name.substring(name.indexOf(".", 0) + 1, name.length);
			if(name == "jpg" || name == "png" || name == "gif" || name == ""){
				$("#pictureName"+i).val(name);
			}else{
				
				return false;
			}
			//检查thumbnails
			name = document.getElementById("thumbnails"+i).value;
		 	//获取文件后缀
		 	name = name.substring(name.indexOf(".", 0) + 1, name.length);
			if(name == "jpg" || name == "png" || name == "gif" || name == ""){
				$("#thumbnailsName"+i).val(name);
			}else{
				return false;
			}

			
			//全不为空时，返回true
			return true;
		}
		/**
		 * 取得一条记录中各个属性的值
		 */
		 function getUnit(i){
		 	var result = "";
		 	result = document.getElementById("unitName"+i).value;
		 	//unitName可能为中文，需要转码
		 	$("#unitName"+i).val(escape(result));
		 	
		 	
		 	/*
		 	result += document.getElementById("unitTypeName"+i).value + ",";
		 	result += document.getElementById("fatherUnitName"+i).value + ",";
		 	result += document.getElementById("x"+i).value + ",";
		 	result += document.getElementById("y"+i).value + ",";
		 	result += document.getElementById("width"+i).value + ",";
		 	result += document.getElementById("height"+i).value + ",";
		 	*/
		 	return result;
		 }
		
	</script>
	
	
</body>
</html>
