<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 导入框架必须文件开始 -->
<base href="<%=basePath%>">
<title>UnitTypeOperation</title>
<script type="text/javascript" src="<%=basePath%>libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>libs/js/framework.js"></script>
<script type="text/javascript"
	src="<%=basePath%>libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>libs/js/nav/pageNumber.js"></script>
<script type="text/javascript"
	src="<%=basePath%>libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="<%=basePath%>libs/js/popup/drag.js"></script>
<script type="text/javascript"
	src="<%=basePath%>libs/js/popup/dialog.js"></script>

<link href="<%=basePath%>libs/js/tree/ztree/ztree.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<link href="<%=basePath%>libs/skins/blue/style.css" rel="stylesheet"
	type="text/css" id="theme" themeColor="blue" />
<link href="<%=basePath%>libs/css/showAllSensor.css" rel="stylesheet"
	type="text/css" />
<script src="<%=basePath%>libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=basePath%>libs/js/form/validation.js" type="text/javascript"></script>
<!-- 导入框架必须文件结束 -->
</head>

<body>
<%!// 从servlet接受到的数据，数据结构：{unitName, portType, channelNo}交替存储在str字符串数组中
	String[] str;%>
	<%!// i用于循环unitName，j用于循环portType,k用于循环channelNo
	int i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, end;%>
	<%!// str的长度
	int length = 0;%>
	<%!// pag用于分页的记录当前页面是第几页
	int pag = 0;%>
	<%
		str = (String[]) request.getAttribute("str");
		if (str == null) {
			str = (String[]) session.getAttribute("str");
			i = Integer.valueOf(request.getParameter("page"));
			pag = i;
			i = i * 10 * 22;
			j = i + 1;
			k = i + 2;
			l = i + 3;
			m = i + 4;
			n = i + 5;
			end = i + 10 * 22;
		} else {
			session.setAttribute("str", str);
			i = 0;
			pag = i;
			j = 1;
			k = 2;
			l = 3;
			m = 4;
			n = 5;
			end = 10 * 22;
		}
	%>
	<%
		length = str.length;
	%>
	<!-- String unitName = null, nodeIP = null, portType = null, channelNo = null, 
	sensorTypeID1 = null, sensorDataTypeID1 = null, minValue1 = null, maxValue1 = null,
	sensorTypeID2 = null, sensorDataTypeID2 = null, minValue2 = null, maxValue2 = null,
	sensorTypeID3 = null, sensorDataTypeID3 = null, minValue3 = null, maxValue3 = null, 
	x = null, y = null, width = null, height = null -->
	
	<!-- 显示操作信息开始 -->
	<div class="box2" panelTitle="操作">
		<!-- 居左显示查找条件输入框和查找按钮开始 -->
		<div class="halfleft">
			<form action="<%=basePath%>servlet/SearchSensorServlet" 
				target="frmright" method="post">
				<div style="font-size:18px">
					上位机IP：<input type="text" id="embededIP"
						name="embededIP" value="" /> 
					节点IP： <input type="text" id="nodeIP"
						name="nodeIP" value="" /> 
					通道号： <input type="text" id="channelNo"
						name="channelNo" value="" /> 
					<input type="submit" id="ch"
						value="查找" onclick="return searchSensor();" />
				</div>
			</form>
		</div>
		<!-- 居左显示查找条件输入框和查找按钮结束 -->
		<!-- 右中显示增加按钮开始 -->
		<div class="halfright hand" onclick="addSensor()">
			<div align="center">
				<div>
					<img src="<%=basePath%>libs/icons/png/add.png" />
				</div>
			</div>
		</div>
		<!-- 右中显示增加按钮结束 -->
	</div>
	<!-- 显示操作信息结束 -->
	<!-- 显示unitType详细信息开始 -->
	<div id="scrollContent" style="overflow-x:hidden;">
		<!-- 显示unitType详细信息，表头开始 -->
		<table class="tableStyle" mode="list">
			<tr>
				<th width="15%">单元名称</th>
				<th width="15%">上位机IP</th>
				<th width="15%">节点IP</th>
				<th width="15%">通道号</th>
				<th width="15%">端口类型</th>
				<th width="12%">查看详细</th>
				<th width="12%">删除</th>
			</tr>
		</table>
		<!-- 显示sensor粗略信息，表头结束 -->
		
		<!-- 显示sensor粗略信息，循环分条显示详细信息开始 -->
		<%
			for (; i < length && i < end; i += 22, j += 22, k += 22, l += 22, m += 22, n += 22) {
		%>
		<form action="<%=basePath%>servlet/ChangeSensorServlet"
			id="changeForm<%=i%>" target="frmright" method="post">
			<div>
				<input type="hidden" id="sensorID" name="sensorID"
					value="<%=str[i]%>" />
				<table class="tableStyle " mode="list">
					<tr>
						<td width="15%"><div> 
							<%=str[j]%>
							</div></td>
					    <td width="15%"><div>
							<%=str[k]%>
						</div></td>
						<td width="15%"><div>
							<%=str[l]%>         
						</div></td>
						<td width="15%"><div>
							<%=str[m]%>         
						</div></td>
						<td width="15%"><div>
							<%=str[n]%>         
						</div></td>
						<td width="12%"><span id="changeUnitType<%=i%>"
							class="img_view hand" title="查看详细"
							onclick="detailPage(<%=i%>);"></span>
						</td>
						<td width="12%"><span id="delete<%=i%>"
							class="img_delete hand" title="删除"
							onclick="delSensor(<%=str[i]%>)"></span></td>
					</tr>
				</table>
			</div>
		</form>
		<%
			}
		%>
		<!-- 显示unitType详细信息，循环分条显示详细信息结束 -->
	</div>
	<!-- 显示unitType详细信息结束 -->

	<!-- 分页按钮开始 -->
	<div class="float_right padding5">
		<div class="pageNumber" total="<%=length / 22%>" showInput="true" page= "<%=pag%>"
			id="page" pageSize="10"></div>
	</div>
	<!-- 分页按钮结束 -->

	<script type="text/javascript">
		/**
		* 验证表单函数
		*/
		//手动触发验证，被验证的表单元素是containerId容器里的。 可以验证整个表单，也可以验证部分表单。
		function validateForm(containerId1){
		    var valid1 = $(containerId1).validationEngine({returnIsValid: true});
			return valid1;
		}	
	
		/**
		 * 批量增加unitType时，跳转到addUnitType.jsp页面
		 */
		function addSensor(){ 
	        /*diag = new top.Dialog();
			diag.Title = "增加";
			diag.URL =*/
			location.href = "<%=basePath%>servlet/LoadAboutSensorServlet";
			/*diag.show();*/
		}
		/**
		
			显示具体的数据项
		*/
		
		function detailPage(i){
			var diag = new top.Dialog();
			diag.Title = "查看详细";
			diag.Width = 1000;
		    diag.Height = 500;
			diag.URL = "<%=basePath%>jsp/sensor/popup/showSensorDetail.jsp?tag="+i;
			diag.show();
		} 		
		
		/**
		 * 点击查找时，根据输入的内容unitTypeName进行查找并显示在此页面
		 */
		function searchUnitType(){
			if(document.getElementById("unitTypeName").value==""){
				return false;
			}else{
				return true;
			}
		}
		
		/**
		 * 删除时提示是否确认删除
		 */
		function delSensor(sensorID){
			if(confirm("真的要删除吗？")){
       			location.href="<%=basePath%>servlet/DelSensorServlet?sensorID=" + sensorID;
			}else{
			}
		}
		
		/**
		 * 修改信息时提示是否确认修改 
		 */
		function changeSensor(i){
			//表单验证是否为空
			var flag = validateForm("#changeForm"+i);
			if(flag == false){
				alert('表单填写不正确，请按要求填写！');
				return;
			}
			if(confirm("真的要确认修改吗？")){
				document.getElementById("changeForm"+i).submit();
			}else{
				return false;
			}
		}
		
		/**
		 *分页时显示不同页的内容
		 */
		$(function(){
			$("#page").bind("pageChange",function(e,index){
				window.location.href="<%=basePath%>jsp/sensor/showAllSensor.jsp?page="+index;
			});
		});
	</script>
</body>
</html>
