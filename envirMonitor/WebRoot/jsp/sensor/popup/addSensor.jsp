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
		<link rel="stylesheet" type="text/css" id="skin"
			prePath="<%=basePath%>" />
		<link rel="stylesheet" type="text/css" id="customSkin" />
		<link href="<%=basePath%>libs/css/showAllUnitType.css"
			rel="stylesheet" type="text/css" />
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
		<!-- 表单验证start -->
		<script src="<%=basePath%>libs/js/form/validationRule.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>libs/js/form/validation.js"
			type="text/javascript"></script>
		<!-- 表单验证end -->
		<!--自动提示框start-->
		<script type='text/javascript'
			src='<%=basePath%>libs/js/form/suggestion.js'></script>
		<!--自动提示框end-->
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
		<!-- 显示添加输入框和按钮结束 -->
		<%!String[][] strSelect;%>
		<%
			strSelect = (String[][]) request.getAttribute("strSelect");
			session.setAttribute("strSelect", strSelect);
		%>
		<%!int i, j;%>
		<%
			strSelect = (String[][]) session.getAttribute("strSelect");
		%>
		<!-- 显示将要添加进数据库的条目开始 -->


		<!-- 显示将要添加进数据库的条目结束 -->

		<!-- 显示保存取消按钮，点击保存则数据被提交进数据库开始 -->
	
		<div class="box1">
			<form action="<%=basePath%>servlet/AddSensorServlet" id="addForm"
			target="frmright" method="post">
				<div align="center">
					<input type="button" value="添加" onclick="addHandler()"
						id="testBtn2" />
					<input type="button" id="add" name="add" value="保存"
						onclick="addSensor()" />
					<input type="button" value="取消" onclick="closeWin()">
				</div>
			</form>
		</div>
		<!-- 显示保存取消按钮，点击保存则数据被提交进数据库结束 -->
		<form action="<%=basePath%>servlet/AddSensorServlet" id="addForm_1"
			target="frmright" method="post">
			<input type="hidden" id="sensorAll" class="sensorAll" name="sensorAll" value="">
		</form>
		



		<script>
		var tdText0;
		var tdText1;
		var $currentTr;
		var sum = 0;
		var sensorAll="";
		function initComplete() {
			addHandler();
		}
		/**
		 * 验证表单函数
		 */
		//手动触发验证，被验证的表单元素是containerId容器里的。 可以验证整个表单，也可以验证部分表单。
		function validateForm(containerId1) {
			var valid1 = $("#unitTypeNameForm").validationEngine({
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
			addNewTable();
			sum++;
		}


		//关闭页面
		function closeWin() {
    		location.href="<%=basePath%>servlet/ShowAllSensorServlet";
		}

		//执行增加操作
		function addSensor() {
			//检查是否输入非法字符
			var flag = validateForm("#addForm");
			if (flag == false) {
				alert("表单填写不正确，请按要求填写！");
				return;
			}
			
			//检查输入是否为空并拼接结果集
			var unitNames = "";
			var embededIPs  = "";
			var nodeIPs = "";
			var channelNos = "";
			var portTypes = "";
			var sensorTypeName1s = "";
			var sensorDataTypeName1s = "";
			var minValue1s = "";
			var maxValue1s = "";
			var sensorTypeName2s = "";
			var sensorDataTypeName2s = "";
			var minValue2s = "";
			var maxValue2s = "";
			var sensorTypeName3s = "";
			var sensorDataTypeName3s = "";
			var minValue3s = "";
			var maxValue3s = "";
			var xs = "";
			var ys = "";
			var widths = "";
			var heights = "";
			var temp = new Array();
			var flag = false;
			var flag1 = 0, flag2 = 0, flag3 = 0;
			var j = 0;
			for ( var i = 0; i < sum; i++) {
				if(checkExist(i)){
					flag = false;
					flag1 = 0, flag2 = 0, flag3 = 0;
					//共21项
					temp[0] = document.getElementById("unitName" + i);
					temp[1] = document.getElementById("embededIP" + i);
					temp[2] = document.getElementById("nodeIP" + i);
					temp[3] = document.getElementById("channelNo" + i);
					temp[4] = document.getElementById("portType" + i);
					
					temp[5] = document.getElementById("sensorTypeName1" + i);
					temp[6] = document.getElementById("sensorDataTypeName1" + i);
					temp[7] = document.getElementById("minValue1" + i);
					temp[8] = document.getElementById("maxValue1" + i);
					
					temp[9] = document.getElementById("sensorTypeName2" + i);
					temp[10] = document.getElementById("sensorDataTypeName2" + i);
					temp[11] = document.getElementById("minValue2" + i);
					temp[12] = document.getElementById("maxValue2" + i);
					
					temp[13] = document.getElementById("sensorTypeName3" + i);
					temp[14] = document.getElementById("sensorDataTypeName3" + i);
					temp[15] = document.getElementById("minValue3" + i);
					temp[16] = document.getElementById("maxValue3" + i);
					
					temp[17] = document.getElementById("x" + i);
					temp[18] = document.getElementById("y" + i);
					temp[19] = document.getElementById("width" + i);
					temp[20] = document.getElementById("height" + i);
					
					for(j=0;j<5;j++){
						if(temp[j]!=null){
							if(temp[j].value==""){
								alert("还有未填项，请检查重试！");
								return;
							}
						}
					}
					//检查两项的IP时候为正负整数
					if(temp[1].value.match("^[-]?[0-9]+$")!=temp[1].value ||
						temp[2].value.match("^[-]?[0-9]+$")!=temp[2].value){
						alert("IP必须为正负整数，请检查后重试");
						return;
					}
					//检查其他各该填入正整数的数据项是否填入的是正整数
					if(temp[3].value.match("^[0-9]+$")!=temp[3].value ||
						temp[4].value.match("^[0-9]+$")!=temp[4].value ||
						temp[17].value.match("^[0-9]+$")!=temp[17].value ||
						temp[18].value.match("^[0-9]+$")!=temp[18].value ||
						temp[19].value.match("^[0-9]+$")!=temp[19].value ||
						temp[20].value.match("^[0-9]+$")!=temp[20].value){
						alert("数据项填写错误，请检查后重试");
						return;
					}
					//最大值最小值应该为double类型，检查是否为小数，最多8位小数
					//if(temp[7].value.match("^\\d+(\\.\\d{1,8})$")!=temp[7].value ||
						//temp[8].value.match("^\\d+(\\.\\d{1,8})$")!=temp[8].value ||
						//temp[11].value.match("^\\d+(\\.\\d{1,8})$")!=temp[11].value ||
						//temp[12].value.match("^\\d+(\\.\\d{1,8})$")!=temp[12].value ||
						//temp[15].value.match("^\\d+(\\.\\d{1,8})$")!=temp[15].value ||
						//temp[16].value.match("^\\d+(\\.\\d{1,8})$")!=temp[26].value){
						//alert("最大值最小值为小数，请检查后重试");
						//return;
					//}
					
					if((temp[5]!=null&&temp[6]!=null&&temp[7]!=null&&temp[8]!=null&&
						temp[5].value!=""&&temp[6].value!=""&&temp[7].value!=""
							&&temp[8].value!="")){
						if(temp[7].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[7].value ||
						temp[8].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[8].value){
							alert("传感器1最大值最小值为小数，请检查后重试");
							return;
						}
						flag1 = 1;
					}
					else{
						if((temp[5]!=null&&temp[6]!=null&&temp[7]!=null&&temp[8]!=null&&
						temp[5].value==""&&temp[6].value==""&&temp[7].value==""
							&&temp[8].value=="")){
								flag1 = 0;
						}
						else{
							flag1 = -1;
						}
							
					}
					
					if(temp[9]!=null&&temp[10]!=null&&temp[11]!=null&&temp[12]!=null&&
						temp[9].value!=""&&temp[10].value!=""&&temp[11].value!=""
							&&temp[12].value!=""){
						if(temp[11].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[11].value ||
						temp[12].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[12].value){
							alert("传感器2最大值最小值为小数，请检查后重试");
							return;
						}
							flag2 = 1;
						}
						else{
							if(temp[9]!=null&&temp[10]!=null&&temp[11]!=null&&temp[12]!=null&&
							temp[9].value==""&&temp[10].value==""&&temp[11].value==""
							&&temp[12].value==""){
								flag2 = 0;
							}
							else{
								flag2 = -1;
							}
						}
					
					if(temp[13]!=null&&temp[14]!=null&&temp[15]!=null&&temp[16]!=null&&
						temp[13].value!=""&&temp[14].value!=""&&temp[15].value!=""
							&&temp[16].value!=""){
						if(temp[15].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[15].value ||
						temp[16].value.match("^[0-9]+[\\.]?[0-9]+$")!=temp[16].value){
							alert("传感器3最大值最小值为小数，请检查后重试");
							return;
						}
						flag3 = 1;
					}else{
						if(temp[13]!=null&&temp[14]!=null&&temp[15]!=null&&temp[16]!=null&&
						temp[13].value==""&&temp[14].value==""&&temp[15].value==""
							&&temp[16].value==""){
							flag3 = 0;
						}
						else{
							flag3 = -1;
						}
					}
					
					if(flag1 != -1 && flag2 != -1 && flag3 != -1 && (flag1 + flag2 + flag3) >= 1){
						flag = true;
					}
					
					if(flag!=true){
						alert("还有未填项，请检查重试！");
						return;
					}
					
					for(j=17;j<21;j++){
						if(temp[j]!=null){
							if(temp[j].value==""){
								alert("还有未填项，请检查重试！");
								return;
							}
						}
					}
					unitNames = temp[0].value + ",";
					embededIPs = temp[1].value + ",";
					nodeIPs = temp[2].value + ",";
					channelNos = temp[3].value + ",";
					portTypes = temp[4].value + ",";
					sensorTypeName1s = temp[5].value + ",";
					sensorDataTypeName1s = temp[6].value + ",";
					minValue1s = temp[7].value + ",";
					maxValue1s = temp[8].value + ",";
					sensorTypeName2s = temp[9].value + ",";
					sensorDataTypeName2s = temp[10].value + ",";
					minValue2s = temp[11].value + ",";
					maxValue2s = temp[12].value + ",";
					sensorTypeName3s = temp[13].value + ",";
					sensorDataTypeName3s = temp[14].value + ",";
					minValue3s = temp[15].value + ",";
					maxValue3s = temp[16].value + ",";
					xs = temp[17].value + ",";
					ys = temp[18].value + ",";
					widths = temp[19].value + ",";
					heights = temp[20].value + ",";
				
					
					//如果没有添加任何数据项，则不处理
					if(unitNames == "" && embededIPs  == "" && nodeIPs == "" && channelNos == "" &&
					portTypes == "" && sensorTypeName1s == "" && sensorDataTypeName1s == "" && 
					minValue1s == "" && maxValue1s == "" && sensorTypeName2s == "" && sensorDataTypeName2s == "" &&
					minValue2s == "" && maxValue2s == "" && sensorTypeName3s == "" && sensorDataTypeName3s == "" &&
					minValue3s == "" && maxValue3s == "" && xs == "" && ys == "" && widths == "" && heights == ""){
						return;
					}
					sensorAll = sensorAll + unitNames+embededIPs+nodeIPs+channelNos+portTypes+sensorTypeName1s+
										sensorDataTypeName1s+minValue1s+maxValue1s+sensorTypeName2s+
										sensorDataTypeName2s+minValue2s+maxValue2s+sensorTypeName3s+
										sensorDataTypeName3s+minValue3s+maxValue3s+xs+ys+widths+heights;
				}
			}								
			if(sensorAll==""){
				return;
			}
			$("#sensorAll").val(sensorAll);
			document.getElementById("addForm_1").submit();
			return true;
	}
		
	function addNewTable(){
		var $table=$(
		'<div > <div align="right" id="dele'+sum+'"><input type="button" onclick="delTable('+sum+')" value="删除" /></div>' +
	    '<fieldset>'+
		'<legend>传感器基本信息</legend> '+
		'<table class="tableStyle" formMode="transparent" footer="normal">'+
		'	<tr>'+
		'		<td width="10%">传感器ID：</td>'+
		'		<td width="20%"><input type="text" name="sensorID" id="sensorID" disabled="true" watermark="自动生成"/></td>'+
		'		<td width="10%">单元名称：</td>'+
		'		<td width="20%">'    +
		'			<select class="validate[required]" name = "unitName" id = "unitName'+sum+'" value="请选择">'	+
		'           <option value = "" selected = "selected">请选择</option>'+
		'			<%for (i = 1; i < strSelect[0].length; i++) {%>'+
		'			<option value = "<%=strSelect[0][i]%>"><%=strSelect[0][i]%></option>'+
		'			<%}%>'+
		'		</td>			'+
		'		<td width="10%">上位机IP：</td>'+
		'		<td width="20%"><input type="text" name = "embededIP'+sum+'" id = "embededIP'+sum +'" value="" /></td>'+
		'	</tr>'+
		'	<tr>'+
		'		<td width="10%">节点IP：</td>'+
		'		<td width="20%"><input type="text" name = "nodeIP'+sum+'" id = "nodeIP'+sum+'"/></td>'+
		'		<td width="10%">通道号：</td>'+
		'		<td width="20%"><input type="text" name = "channelNo'+sum+'" id = "channelNo'+sum+'"/></td>'+
		'		<td width="10%">端口类型：</td>'+
		'		<td width="20%"><input type="text" name = "portType'+sum+'" id = "portType'+sum+'"/></td>'+
			'</tr>'+
	'	</table>'+
	'</fieldset> '+
	'<div class="height_15"></div>'+
	'<fieldset>' +
		'<legend>传感器详细信息</legend> '+
		'<table class="tableStyle" formMode="transparent" footer="normal">'+
			'<tr>'+
				'<td width="10%">传感器1类型：</td>'+
				'<td width="10%"> '+
				   '<select class="validate[required]" value="请选择" name = "sensorTypeName1'+sum+'" id = "sensorTypeName1'+sum+'" >'+
				   ' <option value = "" selected = "selected">请选择</option>'+
					'<%for (i = 0; i < strSelect[1].length; i++) {%>'+
					'<option value = "<%=strSelect[1][i]%>"><%=strSelect[1][i]%></option>'+
					'<%}%>'+
				'</td>'+
				'<td width="15%">传感器1数据类型：</td>'+
				'<td width="10%">'+
					'<select class="validate[required]" value ="请选择" name = "sensorDataTypeName1'+sum+'" id = "sensorDataTypeName1'+sum+'">'+
					'           <option value = "" selected = "selected">请选择</option>'+
				'	<%for (i = 0; i < strSelect[2].length; i++) {%>'+
					'<option value = "<%=strSelect[2][i]%>"><%=strSelect[2][i]%></option>'+
					'<%}%>'+
				'</td>'+
			  '<td width = "15%">传感器1阀值范围：</td>'+
			  '<td width = "6%">最小值：</td><td wdth="10%"><input name = "minValue1'+sum+'" id = "minValue1'+sum+'" type="text" /></td>'+
			  '<td width = "1%"></td>'+
				'<td width = "6%">最大值：</td><td width="10%"><input name = "maxValue1'+sum+'"  id = "maxValue1'+sum+'" type="text" /></td>'+
		'	</tr>'+
		'</table>'+
		'<div class="height_15"></div> '+
			'<table  class="tableStyle" formMode="transparent" footer="normal">'+
			'<tr>'+
				'<td width="10%">传感器2类型：</td>'+
				'<td width="10%">' +
				   '<select class="validate[required]" value ="请选择" name = "sensorTypeName2'+sum+'" id = "sensorTypeName2'+sum+'" >'+
				   '           <option value = "" selected = "selected">请选择</option>'+
					'<%for (i = 0; i < strSelect[1].length; i++) {%>'+
				'	<option value = "<%=strSelect[1][i]%>"><%=strSelect[1][i]%></option>'+
				'	<%}%>'+
				'</td>'+
				'<td width="15%">传感器2数据类型：</td>'+
				'<td width="10%">'+
'					<select class="validate[required]" value="请选择" name = "sensorDataTypeName2'+sum+'" id = "sensorDataTypeName2'+sum+'">'+
		'           <option value = "" selected = "selected">请选择</option>'+
'					<%for (i = 0; i < strSelect[2].length; i++) {%>'+
					'<option value = "<%=strSelect[2][i]%>"><%=strSelect[2][i]%></option>'+
					'<%}%>'+
				'</td>'+
			  '<td width = "15%">传感器2阀值范围：</td>'+
			  '<td width = "6%">最小值：</td><td width="10%"><input name = "minValue2'+sum+'"  id = "minValue2'+sum+'" type="text" /></td>'+
			  '<td width = "1%"></td>'+
			'	<td width = "6%">最大值：</td><td width="10%"><input name = "maxValue2'+sum+'" id = "maxValue2'+sum+'" type="text" /></td>'+
			'</tr>'+
		'</table>'+
		'<div class="height_15"></div>'+
			'<table class="tableStyle" formMode="transparent" footer="normal">'+
			'<tr>'+
				'<td width="10%">传感器3类型：</td>'+
				'<td width="10%"> '+
'				   <select class="validate[required]" value ="请选择" name  = "sensorTypeName3'+sum+'" id = "sensorTypeName3'+sum+'" >'+
'      			     <option value = "" selected = "selected">请选择</option>'+
					'<%for (i = 0; i < strSelect[1].length; i++) {%>'+
					'<option value = "<%=strSelect[1][i]%>"><%=strSelect[1][i]%></option>'+
				'	<%}%>'+
				'</td>'+
				'<td width="15%">传感器3数据类型：</td>'+
				'<td width="10%">'+
					'<select class="validate[required]" value ="请选择" name = "sensorDataTypeName3'+sum+'" id = "sensorDataTypeName3'+sum+'">'+
					'<option value = "" selected = "selected">请选择</option>'+
					'<%for (i = 0; i < strSelect[2].length; i++) {%>'+
					'<option value = "<%=strSelect[2][i]%>"><%=strSelect[2][i]%></option>'+
					'<%}%>'+
				'</td>'+
			  '<td width = "15%">传感器3阀值范围：</td>'+
			  '<td width = "6%">最小值：</td><td width="10%"><input type="text" name = "minValue3'+sum+'" id = "minValue3'+sum+'" /></td>'+
			  '<td width = "1%"></td>'+
				'<td width = "6%">最大值：</td><td width="10%"><input type="text" name = "maxValue3'+sum+'" id = "maxValue3'+sum+'" /></td>'+
		'	</tr>'+
		'</table>'+
	'</fieldset>'+ 
	'<fieldset> '+
		'<legend>其他信息</legend>'+
		'<table class="tableStyle" formMode="transparent" footer="normal">'+
		'<tr>'+
		 '<td width = "10%">坐标X：</td><td width="15%"><input name = "x'+sum+'" id = "x'+sum+'" type="text" /></td>'+
		 '<td width = "10%">坐标Y：</td><td width="15%"><input name = "y'+sum+'" id = "y'+sum+'" type="text" /></td>'+
		 '<td width = "10%">宽度：</td><td width="15%"><input name = "width'+sum+'"  id = "width'+sum+'" type="text" /></td>'+
		 '<td width = "10%">高度：</td><td width="15%"><input name = "height'+sum+'" id = "height'+sum+'" type="text" /></td>'+
		'</tr>'+
	'</table>'+
	'</fieldset>' + '</div>');
		$("#testBtn2").before($table); 
		$table.render();
	}
	
	//del
	function delTable(s){
		var t = $("#dele"+s).parent();
		t.remove();
	}
	function checkExist(i){
		var dele = document.getElementById("dele"+i);
		if(dele==null){
			return false;
		}else{
			return true;
		}
	}
	</script>

	</body>
</html>
