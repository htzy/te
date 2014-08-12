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
<title>sensorDataTypeOperation</title>
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
<link href="<%=basePath%>libs/css/showAllsensorDataType.css" rel="stylesheet"
	type="text/css" />
<script src="<%=basePath%>libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=basePath%>libs/js/form/validation.js" type="text/javascript"></script>
<!-- 导入框架必须文件结束 -->
</head>

<body>
    <!--  从servlet接受到的数据，数据结构：{SensorDataTypeID，SensorDataTypeName}交替存储在str字符串数组中-->
	<%!
	   String[] str;%>
	<%! HttpSession session; %>
	<%!    // i用于循环SensorDataTypeID，j用于循环SensorDataTypeName
	   int i = 0, j = 1, end = 20;%>
	<%!    // str的长度
	   int length = 0;%>
	<%!     // pag用于分页的记录当前页面是第几页
	   int pag = 0;%>
	<%
		str = (String[]) request.getAttribute("str");
		if (str == null) {
			str = (String[]) session.getAttribute("str");
			i = Integer.valueOf(request.getParameter("page"));
			pag = i;
			i = i * 20;
			j = i + 1;
			end = i + 20;
		} else {
			session.setAttribute("str", str);
			i = 0;
			pag = i;
			j = 1;
			end = 20;
		}
	%>
	<%
		length = str.length;
	%>
	<!--  <%  System.out.println("结果集长度"+str.length) ;%>  -->
	
	<!-- 显示操作信息开始 -->
	<div class="box2" panelTitle="操作">
		<!-- 居左显示查找条件输入框和查找按钮开始 -->
		<div class="halfleft">
			<div align="left">
				<form action="<%=basePath%>servlet/SearchSensorDataTypeServlet"
					target="frmright" method="post">
					<div class="box2" panelTitle="操作">
						<input type="hidden" id="sensorDataTypeID" name="sensorDataTypeID" value="" />
						<div style="font-size:20px">
								传感器类型 
								<input type="text" id="sensorDataTypeName" name="sensorDataTypeName" value="" /> 
								<input type="submit" id="ch" value="查找" onclick="return searchsensorDataType();" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 居左显示查找条件输入框和查找按钮结束 -->
		
		<!-- 右中显示增加按钮开始 -->
		<div class="halfright hand" onclick="addSensorDataType()">
			<div align="center">
				<div>
					<img src="<%=basePath%>libs/icons/png/add.png" />
				</div>
			</div>
		</div>
		<!-- 右中显示增加按钮结束 -->
		
	</div>
	<!-- 显示操作信息结束 -->
	
	<!-- 显示sensorDataType详细信息开始 -->
	<div id="scrollContent" style="overflow-x:hidden;">
	
		<!-- 显示sensorDataType详细信息，表头开始 -->
		<table class="tableStyle" mode="list">
			<tr>
				<th width="50%">传感器数据类型</th>
				<th width="25%">修改</th>
				<th width="25%">删除</th>
			</tr>
		</table>
		<!-- 显示sensorDataType详细信息，表头结束 -->
		
		<!-- 显示sensorDataType详细信息，循环分条显示详细信息开始 -->
		<%
			for (; j < length && i < end; i += 2, j += 2) {
		%>
		<form action="<%=basePath%>servlet/ChangeSensorDataTypeServlet"
			  id="changeForm<%=i%>" target="frmright" method="post">
			<div>
				 <input type="hidden" id="sensorDataTypeID" name="sensorDataTypeID" value="<%=str[i]%>" />
				 <table class="tableStyle" mode="list">
						<tr>
								<td width="50%">
								    <div>
										<input type="text" style="width:100%;" id="sensorDataTypeName"
											class="validate[required] ,custom[illegalLetter]"
											name="sensorDataTypeName" value="<%=str[j]%>"
											onkeydown="javascript:if(event.keyCode=='13')return false;" />
									</div>
								</td>
								
								<td width="25%">
								  <span id="changesensorDataType<%=i%>"
										class="img_edit hand" title="修改"
										onclick="changesensorDataType(<%=i%>);">
									</span>
								</td>
								
								<td width="25%">
								   <span id="delete<%=i%>"
										class="img_delete hand" title="删除"
										onclick="delsensorDataType(<%=str[i]%>)">
									</span>
								</td>
							
						</tr>
				</table>
			</div>
		</form>
		
		<% }
		%>
	</div>
	<!-- 显示sensorDataType详细信息，循环分条显示详细信息结束 -->
	
	<!-- 显示sensorDataType详细信息结束 -->

	<!-- 分页按钮开始 -->
	<div class="float_right padding5">
		<div class="pageNumber" total="<%=length / 2%>" showInput="true"
			page="<%=pag%>" id="page" pageSize="10"></div>
	</div>
	<!-- 分页按钮结束 -->


	 <script type="text/javascript">
		/**
		 *分页时显示不同页的内容
		 */
		$(function(){
			$("#page").bind("pageChange",function(e,index){
				window.location.href="<%=basePath%>jsp/sensorDataType/showAllSensorDataType.jsp?page="+index;
			});
		});
		/**
		* 验证表单函数
		*/
		//手动触发验证，被验证的表单元素是containerId容器里的。 可以验证整个表单，也可以验证部分表单。
		function validateForm(containerId1){
		    var valid1 = $(containerId1).validationEngine({returnIsValid: true});
			return valid1;
		}	
	
	
		/**
		 * 批量增加sensorDataType时，跳转到addsensorDataType.jsp页面
		 */
		function addSensorDataType(){
			var diag = new top.Dialog();
			diag.Title = "增加";
			diag.URL = "<%=basePath%>jsp/sensorDataType/popup/addSensorDataType.jsp";
			diag.show();
		}
		
		/**
		 * 点击查找时，根据输入的内容sensorDataTypeName进行查找并显示在此页面
		 */
		function searchsensorDataType(){
			if(document.getElementById("sensorDataTypeName").value==""){
				return false;
			}else{
				return true;
			}
		}
		
		/**
		 * 删除时提示是否确认删除
		 */
		function delsensorDataType(sensorDataTypeID){
			if(confirm("真的要删除吗？")){
       			location.href="<%=basePath%>servlet/DelSensorDataTypeServlet?sensorDataTypeID=" + sensorDataTypeID;
			}else{
			}
		}
		
		/**
		 * 修改信息时提示是否确认修改 
		 */
		function changesensorDataType(i){
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
	</script>
</body>
</html>
