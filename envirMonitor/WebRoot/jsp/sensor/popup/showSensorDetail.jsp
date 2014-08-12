<%@page import="com.monitor.factory.Sensor_functionDAOFactory"%>
<%@ page language="java" import="java.util.*,com.monitor.dao.*,com.monitor.factory.*" pageEncoding="UTF-8"%>
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
<script src="<%=basePath%>libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=basePath%>libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->

</head>
<body class="detail">
<%!	String[] str; 
	int i;
	int sel; 
	String[][] strSelect = null;%>
<% 	str = (String[])session.getAttribute("str"); 
	//strSelect = (String[][])session.getAttribute("strSelect");
   	i = Integer.valueOf(request.getParameter("tag").toString());
   	//处理null
   	if(str[i+6]==null){
   		str[i+6]="";
   	}
   	if(str[i+7]==null){
   		str[i+7]="";
   	}
   	if(str[i+10]==null){
   		str[i+10]="";
   	}
   	if(str[i+11]==null){
   		str[i+11]="";
   	}
   	if(str[i+14]==null){
   		str[i+14]="";
   	}
   	if(str[i+15]==null){
   		str[i+15]="";
   	}
   	
   	
   	//取得下拉框的值
   	Sensor_functionDAO Sensor_functionDAOImpl = Sensor_functionDAOFactory.createSensor_functionDAOImplm();
   	strSelect = Sensor_functionDAOImpl.dataForOption();
   	%>
  <div class = "box1" panelWidth="1000">
  <form action="<%=basePath%>servlet/ChangeSensorServlet"
			id="detailform" target="frmright" method="post">
	<fieldset> 
		<legend>传感器基本信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="10%">传感器ID：</td>
				<td width="20%"><input type="text" disabled="true" value="<%=str[i] %>"/>
								<input type="hidden" id="sensorID"  name = "sensorID"  value="<%=str[i] %>"/></td>
				<td width="10%">单元名称：</td>
				<td width="20%"><select id="unitName" name = "unitName" value="">
				<option value = "<%=str[i + 1] %>" selected = "selected" ><%=str[i + 1] %></option>
				<% for(sel = 0 ; sel < strSelect[0].length; ++sel) {%>
				<option value = "<%=strSelect[0][sel] %>"><%=strSelect[0][sel]%></option>
				<%} %>
				</select></td>			
				<td width="10%">上位机IP：</td>
				<td width="20%"><input type="text" id="embededIP" class="validate[required,custom[onlyNumberWide]]" name = "embededIP" value="<%=str[i + 2] %>"/></td>
			</tr>
			<tr>
				<td width="10%">节点IP：</td>
				<td width="20%"><input type="text" id="nodeIP" class="validate[required,custom[onlyNumberWide]]" name = "nodeIP" value="<%=str[i + 3] %>"/></td>
				<td width="10%">通道号：</td>
				<td width="20%"><input type="text" id="channelNo" class="validate[required,custom[onlyNumber]]" name = "channelNo" value="<%=str[i + 4] %>"/></td>
				<td width="10%">端口类型：</td>
				<td width="20%"><input type="text" id="portType" class="validate[required,custom[onlyNumber]]" name = "portType" value="<%=str[i + 5] %>"/></td> 
			</tr>
		</table>
	</fieldset> 
	<div class="height_15"></div>
	<fieldset> 
		<legend>传感器详细信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">传感器1类型：</td>
				<td width="15%">    
					<select id="sensorTypeName1" name = "sensorTypeName1"  >
					<option id="sensorTypeName11"  selected = "selected"><%=str[i + 6] %></option>
					<% for(sel = 0 ; sel < strSelect[1].length; ++sel) {%>
					<option value = "<%=strSelect[1][sel] %>"><%=strSelect[1][sel]%></option>
					<%} %>
					</select>
				</td>
				<td width="10%">传感器1数据类型：</td>
				<td width="15%">
				<select id="sensorDataTypeName1" name = "sensorDataTypeName1" >
				<option value ="<%=str[i + 7] %>" id="sensorTypeName12" selected = "selected"><%=str[i + 7] %></option>
				<% for(sel = 0 ; sel < strSelect[2].length; ++sel) {%>
				<option value = "<%=strSelect[2][sel] %>"><%=strSelect[2][sel]%></option>
				<%} %>
				</select></td>
			  <td width = "10%">传感器1阀值范围：</td>
			  <td width = "5%">最小值：</td><td width="12%"><input id = "minValue1" name = "minValue1" class="validate[custom[onlyNumberWide]]" type="text" value="<%=str[i + 8] %>"/></td>
			  <td width = "1%"></td>
				<td width = "5%">最大值：</td><td width="12%"><input id = "maxValue1" name = "maxValue1" class="validate[custom[onlyNumberWide]]" type="text" value="<%=str[i + 9] %>"/></td>
			</tr>
		</table>
		<div class="height_15"></div> 
				<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">传感器2类型：</td>
				<td width="15%">    
					<select id="sensorTypeName2" name = "sensorTypeName2"  >
					<option value = "<%=str[i + 10] %>" selected = "selected"><%=str[i + 10] %></option>
					<% for(sel = 0 ; sel < strSelect[1].length; ++sel) {%>
					<option value = "<%=strSelect[1][sel] %>"><%=strSelect[1][sel]%></option>
					<%} %>
				</select></td>
				<td width="10%">传感器2数据类型：</td>
				<td width="15%">
				<select id="sensorDataTypeName2" name = "sensorDataTypeName2"  >
				<option value = "<%=str[i + 11] %>" selected = "selected"><%=str[i + 11] %></option>
				<% for(sel = 0 ; sel < strSelect[2].length; ++sel) {%>
				<option value = "<%=strSelect[2][sel] %>"><%=strSelect[2][sel]%></option>
				<%} %>
				</select></td>
			  <td width = "10%">传感器2阀值范围：</td>
			  <td width = "5%">最小值：</td><td width="12%"><input type="text" id="minValue2" name = "minValue2" class="validate[custom[onlyNumberWide]]" value="<%=str[i + 12] %>"/></td>
			  <td width = "1%"></td>
				<td width = "5%">最大值：</td><td width="12%"><input type="text" id="maxValue2" name = "maxValue2" class="validate[custom[onlyNumberWide]]" value="<%=str[i + 13] %>"/></td>
			</tr>
		</table>
		<div class="height_15"></div>
				<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="15%">传感器3类型：</td >
				<td width="15%">
				<select id="sensorTypeName3" name = "sensorTypeName3" >
					<option value = "<%=str[i + 14] %>" selected = "selected"><%=str[i + 14] %></option>
					<% for(sel = 0 ; sel < strSelect[1].length; ++sel) {%>
					<option value = "<%=strSelect[1][sel] %>"><%=strSelect[1][sel]%></option>
					<%} %>
				</select></td>
				<td width="10%">传感器3数据类型：</td>
				<td width="15%">
				<select id="sensorDataTypeName3" name = "sensorDataTypeName3" >
				<option value = "<%=str[i + 15] %>" selected = "selected"><%=str[i + 15] %></option>
				<% for(sel = 0 ; sel < strSelect[2].length; ++sel) {%>
				<option value = "<%=strSelect[2][sel] %>"><%=strSelect[2][sel]%></option>
				<%} %>
				</select></td>
			  <td width = "10%">传感器3阀值范围：</td>
			  <td width = "5%">最小值：</td><td width="12%"><input type="text" id="minValue3" name = "minValue3" class="validate[custom[onlyNumberWide]]" value="<%=str[i + 16] %>"/></td>
			  <td width = "1%"></td>
				<td width = "5%">最大值：</td><td width="12%"><input type="text" id="maxValue3" name = "maxValue3" class="validate[custom[onlyNumberWide]]" value="<%=str[i + 17] %>"/></td>
			</tr>
		</table>
	</fieldset> 
	<fieldset> 
		<legend>其他信息</legend>
		<table class="tableStyle" formMode="transparent" footer="normal">
		<tr>
		 <td width = "10%">坐标X：</td><td width="15%"><input type="text" id="x" name = "x" class="validate[required,custom[onlyNumber]]" value="<%=str[i + 18] %>"/></td>
		 <td width = "10%">坐标Y：</td><td width="15%"><input type="text" id="y" name = "y" class="validate[required,custom[onlyNumber]]" value="<%=str[i + 19] %>"/></td>
		 <td width = "10%">宽度：</td><td width="15%"><input type="text" id="width" name= "width" class="validate[required,custom[onlyNumber]]"  value="<%=str[i + 20] %>"/></td>
		 <td width = "10%">高度：</td><td width="15%"><input type="text" id="height" name = "height" class="validate[required,custom[onlyNumber]]" value="<%=str[i + 21] %>"/></td>
		</tr>
	</table>
	</fieldset>
  </form>
</div>

	<div align = "center">
	<input type = "button" value = "修 改" onclick = "changeSensor();"/>
	<input type = "button" value = "取 消" onclick = "closeWin();"/>
	</div>	
	<script type="text/javascript">
	function initComplete(){
		
		if($("#minValue1").val()=="null"){
			$("#minValue1").val("");
		}
		if($("#maxValue1").val()=="null"){
			$("#maxValue1").val("");
		}
		
		if($("#minValue2").val()=="null"){
			$("#minValue2").val("");
		}
		if($("#maxValue2").val()=="null"){
			$("#maxValue2").val("");
		}
		
		if($("#minValue3").val()=="null"){
			$("#minValue3").val("");
		}
		if($("#maxValue3").val()=="null"){
			$("#maxValue3").val("");
		}
	}
 
	function closeWin() {
			top.Dialog.close();
	}	
	
	function changeSensor(){
			//表单验证是否为空
			/*var flag = validateForm("#detailform");
			if(flag == false){
				alert('表单填写不正确，请按要求填写！');
				return;
			}*/
			var temp = new Array();
			var flag = false;
			var flag1 = 0, flag2 = 0, flag3 = 0;
			var j = 0;
			flag = false;
			flag1 = 0, flag2 = 0, flag3 = 0;
			//共21项
			temp[0] = document.getElementById("unitName");
			temp[1] = document.getElementById("embededIP");
			temp[2] = document.getElementById("nodeIP");
			temp[3] = document.getElementById("channelNo");
			temp[4] = document.getElementById("portType" );
			
			temp[5] = document.getElementById("sensorTypeName1" );
			temp[6] = document.getElementById("sensorDataTypeName1" );
			temp[7] = document.getElementById("minValue1" );
			temp[8] = document.getElementById("maxValue1" );
			
			temp[9] = document.getElementById("sensorTypeName2" );
			temp[10] = document.getElementById("sensorDataTypeName2" );
			temp[11] = document.getElementById("minValue2" );
			temp[12] = document.getElementById("maxValue2" );
			
			temp[13] = document.getElementById("sensorTypeName3" );
			temp[14] = document.getElementById("sensorDataTypeName3" );
			temp[15] = document.getElementById("minValue3" );
			temp[16] = document.getElementById("maxValue3" );
			
			temp[17] = document.getElementById("x" );
			temp[18] = document.getElementById("y" );
			temp[19] = document.getElementById("width" );
			temp[20] = document.getElementById("height" );
				
			for(j=0;j<5;j++){
				if(temp[j]!=null){
					if(temp[j].value==""){
						alert("还有未填项，请检查重试！");
						return;
					}
				}
			}
			if(temp[1].value.match("\\.")=="." || temp[2].value.match("\\.")=="."){
				alert("IP不能为小数，请检查重试！");
				return;
			}	
			if((temp[5]!=null&&temp[6]!=null&&temp[7]!=null&&temp[8]!=null&&
				temp[5].value!=""&&temp[6].value!=""&&temp[7].value!=""
					&&temp[8].value!="")){
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
					flag3 = 1;
				}
				else{
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
			if(confirm("真的要确认修改吗？")){
				//下拉框
				if(temp[5].value==""){
					temp[5].value="null";
				}
				if(temp[6].value==""){
					temp[6].value="null";
				}
				if(temp[9].value==""){
					temp[9].value="null";
				}
				if(temp[10].value==""){
					temp[10].value="null";
				}
				if(temp[13].value==""){
					temp[13].value="null";
				}
				if(temp[14].value==""){
					temp[14].value="null";
				}
			
				//最大值最小值
				if(temp[7].value==""){
					temp[7].value="null";
				}
				if(temp[8].value==""){
					temp[8].value="null";
				}
				if(temp[11].value==""){
					temp[11].value="null";
				}
				if(temp[12].value==""){
					temp[12].value="null";
				}
				if(temp[15].value==""){
					temp[15].value="null";
				}
				if(temp[16].value==""){
					temp[16].value="null";
				}
				//alert("1"+temp[16].value+"2");
				document.getElementById("detailform").submit();
				setTimeout("closeWin()", 100);
				return true;
			}else{
				return false;
			};
		}
	</script>
</body>
</html>
