<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<!-- 导入必要文件开始 -->
<script type="text/javascript" src="<%=basePath%>libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>libs/js/framework.js"></script>
<link href="<%=basePath%>libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<link href="<%=basePath%>libs/css/showAllsensorDataType.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>libs/js/nav/pageNumber.js"></script>
<script type="text/javascript"
	src="<%=basePath%>libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>libs/js/tree/ztree/ztree.css"></link>
<script type="text/javascript"
	src="<%=basePath%>libs/js/form/selectTree.js"></script>
<script src="<%=basePath%>libs/js/form/validationRule.js"
	type="text/javascript"></script>
<script src="<%=basePath%>libs/js/form/validation.js"
	type="text/javascript"></script>
<!-- 导入必要文件结束 -->
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
	<!-- 显示添加输入框和按钮开始 -->
	<br>
	<!-- 显示添加输入框和按钮结束 -->

	<!-- 显示将要添加进数据库的条目开始 -->
	<div id="scrollContent" style="overflow-x:hidden;">
		<form id="sensorDataTypeNameForm">
			<table class="tableStyle" useClick="false" mode="list" id="list">
				<tr>
					<th width="50%">传感器数据类型</th>
					<th width="50%">删除</th>
				</tr>
			</table>
		</form>
	</div>
	<!-- 显示将要添加进数据库的条目结束 -->

	<!-- 显示保存取消按钮，点击保存则数据被提交进数据库开始 -->
	<form action="<%=basePath%>servlet/AddSensorDataTypeServlet" id="addForm"
		target="frmright" method="post">
		<input type="hidden" id="sensorDataTypeNames" name="sensorDataTypeNames" value="" />
		<div class="box1" whiteBg="true">
			<div align="center">
				<input type="button" value="添加" onclick="addHandler()" />
				<input type="button" id="add" name="add" value="保存" onclick="addsensorDataType()" />
				<input type="button" value="取消" onclick="closeWin()">
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
			var valid1 = $("#sensorDataTypeNameForm").validationEngine({
				returnIsValid : true
			});
			return valid1;
		}

		//删除一行信息
		function delHandler($obj) {
			$currentTr = $obj.parents("tr");
			/*top.Dialog.confirm("确定要删除吗？", function() {
				
			});*/
			$currentTr.remove();
		}

		//添加一行信息
		function addHandler() {
			var $tr = addNewRowHandler();
			sum++;
			$("#list").append($tr);
			//重新渲染表格
			$("#list").render();

			// 清空输入框
			clear();
		}

		//重置输入框
		function clear() {
			$("#input1").val("");
		}

		//添加行处理，显示在界面上
		function addNewRowHandler() {
			var $tr = $("<tr><td>"
					+ "<input style='width:100%;' id='sensorDatatypename"
					+ sum
					+ "' class='validate[required,custom[illegalLetter]]' name='sensorDatatypename"
					+ sum
					+ "' "
					+ "value='' />"
					+ "</td><td><span class='img_delete hand' title='删除'></span></td></tr>");
			var $modiBtn = $tr.find(".img_edit");
			var $delBtn = $tr.find(".img_delete");
			var $upBtn = $tr.find(".img_btn_up");
			$delBtn.click(function() {
				delHandler($(this))
			})
			$modiBtn.click(function() {
				modiHandler($(this))
			})
			enableTooltips()
			return $tr;
		}
		
		

		//关闭窗口
		function closeWin() {
			top.Dialog.close();
		}

		//执行增加操作
		function addsensorDataType() {
			//检查是否输入非法字符
			var flag = validateForm("#sensorDataTypeNameForm");
			if (flag == false) {
				alert("表单填写不正确，请按要求填写！");
				return;
			}

			//检查输入是否为空并拼接结果集
			var sensorDataTypeNames = "";
			var temp = "";
			for ( var i = 0; i < sum; i++) {
				temp = document.getElementById("sensorDatatypename" + i);
				if (temp != null) {
					if (temp.value == "") {
						alert("还有未填项，请检查重试！");
						return;
					}
					sensorDataTypeNames += temp.value + ",";
				}
			}
			
			//如果没有添加任何数据项，则不处理
			if(sensorDataTypeNames==""){
				return;
			}
			$("#sensorDataTypeNames").val(sensorDataTypeNames);
			document.getElementById("addForm").submit();
			setTimeout("closeWin()", 100);
			return true;
		}
	</script>

</body>
</html>
