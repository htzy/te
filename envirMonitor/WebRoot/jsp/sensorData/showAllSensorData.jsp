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

<base href="<%=basePath%>">
<!--框架必需start-->
<script type="text/javascript" src="<%=basePath%>libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>libs/js/framework.js"></script>
<link href="<%=basePath%>libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"
	scrollerY="false" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->

<!-- 日期选择框start -->
<script type="text/javascript"
	src="<%=basePath%>libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->

<!--数字分页start-->
<script type="text/javascript"
	src="<%=basePath%>libs/js/nav/pageNumber.js"></script>

<!--框架必需start-->
<!-- 使用下面表单验证脚本必须引入的JS -->
<script src="<%=basePath%>libs/js/form/validationRule.js"
	type="text/javascript"></script>
<script src="<%=basePath%>libs/js/form/validation.js"
	type="text/javascript"></script>
<!-- 使用下面表单验证脚本必须引入的JS -->
<link href="<%=basePath%>libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>"
	scrollerY="false" />
<link rel="stylesheet" type="text/css" id="customSkin" />
<!--框架必需end-->

<!-- 日期选择框start -->
<script type="text/javascript"
	src="<%=basePath%>libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
<!--数字分页start-->
<script type="text/javascript"
	src="<%=basePath%>libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
<script>

</script>
</head>
<body>

	<!-- 居左显示查找条件输入框和查找按钮开始 -->
	<div class="halfleft">
		<div align="left">
			<form action="<%=basePath%>servlet/SearchByTimeSensorDataServlet"
				target="frmright" method="post">
				<div class="box2" panelTitle="操作">
					<div style="font-size:20px">
						起始时间：<input type="text" class="date" style="width:160px;"
							dateFmt="yyyy-MM-dd HH:mm:ss" name="startTime" id="startTime"
							value="2014-05-20 12:29:40" /> 结束时间：<input type="text"
							class="date" style="width:160px;" dateFmt="yyyy-MM-dd HH:mm:ss"
							name="endTime" id="endTime" value="2014-05-23 12:29:45" />
						<td><button type="submit"
								onclick="return searchByTimeSensorData();">
								<span class="icon_find">检索</span>
							</button></td>

					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 居左显示查找条件输入框和查找按钮结束 -->

	<!-- 显示sensorData详细信息开始 -->

	<!-- 显示sensorData详细信息，表头开始 -->
	<table class="tableStyle" mode="list">
		<tr>
			<th Width="20%">数据记录时间</th>
			<th Width="10%">值 1</th>
			<th Width="10%">值 2</th>
			<th Width="10%">值 3</th>
			<th Width="10%">修改</th>
			<th Width="10%">删除</th>
		</tr>
	</table>
	<!-- 显示sensorData详细信息，表头结束 -->

	<%!// 从servlet接受到的数据，数据结构：{sensorDataID,samplingTime,value1,vslue2,vslue3}交替存储在str字符串数组中
	String[] str;%>
	<%!// i用于循环
	int i = 0, end = 50;%>
	<%!// str的长度
	int length = 0, pag;%>
	<%
		str = (String[]) request.getAttribute("str");
	%>
	<%
		if (str == null) {
			if (session.getAttribute("str") == null) {
				length = 0;
			}else{
				str = (String[]) session.getAttribute("str");
				if (request.getParameter("page") != null) {
					i = Integer.valueOf(request.getParameter("page"));
				}
				pag = i; 
				i = i * 50;
				end = i + 50;
			}
		} else {
			length = str.length;
			session.setAttribute("str", str);
			i=0;
			end = 50;
			pag = 0;
		}
	%>
	<div id="scrollContent" style="overflow-x:hidden;">
		<!-- 显示sensorData详细信息，循环分条显示详细信息开始 -->
		<%
			for (; i < end && i < length; i += 5) {
		%>
		<form action="<%=basePath%>servlet/SearchByTimeSensorDataServlet"
			target="frmright" method="post" id="changeForm<%=i %>" >
			<div>
				<input type="hidden" id="sensorDataID<%=i %>" name="sensorDataID"
					value="<%=str[i]%>" />
				<table class="tableStyle" mode="list">
					<tr>
						<td width="20%"><div>
								<input type="text" style="width:100%;" id="samplingTime<%=i %>"
									 disabled="disabled" name="samplingTime"
									value="<%=str[i + 1]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100%;" id="value1<%=i %>"
									class="validate[required,custom[onlyNumberWide]]" name="value1"
									value="<%=str[i + 2]%>"
									onkeydown="javascript:if(event.keyCode=='13') return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100%;" id="value2<%=i %>"
									class="validate[required,custom[onlyNumberWide]]" name="value2"
									value="<%=str[i + 3]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100%;" id="value3<%=i %>"
									class="validate[required,custom[onlyNumberWide]] " name="value3"
									value="<%=str[i + 4]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><span id="changeSensorData<%=i%>"
							class="img_edit hand" title="修改"
							onclick="changeSensorData(<%=i%>);"></span></td>

						<td width="10%"><span id="delSensorData<%=i%>"
							class="img_delete hand" title="删除"
							onclick="delSensorData(<%=str[i]%>);"></span></td>
					</tr>
				</table>
			</div>
			</form>
			<%
				}
			%>
		
	</div>

	<!-- 显示sensorData详细信息，循环分条显示详细信息结束 -->

	<!-- 分页按钮开始 -->
	<div class="float_right padding5">
		<div class="pageNumber" total="<%=length / 5%>" showInput="true" page=<%=pag %>
			id="page" pageSize="10"></div>
	</div>
	<!-- 分页按钮结束 -->
	<script>
		 
		/**
		* 验证表单函数
		*/
		//手动触发验证，被验证的表单元素是containerId容器里的。 可以验证整个表单，也可以验证部分表单。
		function validateForm(containerId1){
		    var valid1 = $(containerId1).validationEngine({returnIsValid: true});
			return valid1;
		}	
	
	/**
		 *分页时显示不同页的内容
		 */
		$(function(){
			$("#page").bind("pageChange",function(e,index){
				window.location.href="<%=basePath%>jsp/sensorData/showAllSensorData.jsp?page="+index;
			});
		});
	
function customHeightSet(contentHeight){
	$("#scrollContent").height(contentHeight-fixObjHeight);
}
		function searchByTimeSensorData(){
			if(document.getElementById("startTime").value =="" 
				|| document.getElementById("endTime").value ==""){
					alert("时间选择有误！");
					return false;
				}else{
					return true;
				}
		}
		
	
		
		function changeSensorData(i){
				//表单验证是否为空
				var flag = validateForm("#changeForm"+i);
				if(flag == false){
					alert('表单填写不正确，请按要求填写！');
					return;
				}else{
					if(confirm("确认修改吗？")){
						var sensorDataID = null;
						var samplingTime1 = null;
						var value11 = null;
						var value21 = null;
						var value31 = null;
						
						sensorDataID = document.getElementById("sensorDataID"+i).value;
						samplingTime1 = document.getElementById("samplingTime"+i).value;
						value11 = document.getElementById("value1"+i).value;
						value21 = document.getElementById("value2"+i).value;
						value31 = document.getElementById("value3"+i).value;
						location.href="<%=basePath%>servlet/ChangeSensorDataServlet?sensorDataID="+sensorDataID
					  	+"&samplingTime="+samplingTime1+"&value1="+value11+"&value2="+value21
					  	+"&value3="+value31;
					}else{
						return false;
					}
				}
		}

		function delSensorData(sensorDataID){
			if(confirm("真的要删除吗？")){
       				location.href="<%=basePath%>servlet/DelByIDSensorDataServlet?sensorDataID="+sensorDataID;
			}else{
					
				}
		}
		
		var fixObjHeight=150;
		function initComplete(){
	 		$("#searchPanel").bind("stateChange",function(e,state){
				if(state=="hide"){
					fixObjHeight=90;
				}
				else if(state=="show"){
					fixObjHeight=150;
				}
				triggerCustomHeightSet();
			});
		}
		function customHeightSet(contentHeight){
			var windowWidth=document.documentElement.clientWidth;
			$("#scrollContent").height(contentHeight-fixObjHeight);
			$("#scrollContent").width(windowWidth-4);
		}
		</script>
</body>

</html>
