<%@ page language="java" import="java.util.*,java.io.File" pageEncoding="UTF-8"%>
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

</head>

<body>
	<%!
	// 从servlet接受到的数据，数据结构：{unitID，unitName,...}交替存储在str字符串数组中
	String[] str={""};
	//从servlet接收到的数据，数据结构：json
	String dataForUnitTypeID =""; 
	//从servlet接收到的数据，数据结构：json
	String dataForFatherUnitID = ""; 
	//从servlet接收到的数据，数据结构：option
	String optionsForUnitType = "";
	//从servlet接收到的数据，数据结构：option
	String optionsForFather = "";
	%>
	<%!HttpSession session;%>
	<%!// i用于循环SensorTypeID，j用于循环SensorTypeName
	int i = 0, j = 1, end = 100;%>
	<%!// pag用于分页的记录当前页面是第几页
	int pag = 0;%>
	<%!// str的长度
	int length = 0;%>
	<%!//文件检查
		File file = null;
		String filePath = null;
	 %>
	<%
		str = (String[]) request.getAttribute("dataForAll");
		if (str == null) {
			str = (String[]) session.getAttribute("str");  
			dataForUnitTypeID = session.getAttribute("dataForUnitTypeID").toString();
			dataForFatherUnitID = session.getAttribute("dataForFatherUnitID").toString();
			optionsForUnitType = session.getAttribute("optionsForUnitType").toString();
			optionsForFather = session.getAttribute("optionsForFather").toString();
			i = Integer.valueOf(request.getParameter("page"));
			pag = i;
			i = i * 100; 
			end = i + 100;
			
			filePath=session.getAttribute("filePath").toString();
			
		} else {
			session.setAttribute("str", str);
			dataForUnitTypeID = (String)request.getAttribute("dataForUnitTypeID");
			dataForFatherUnitID = (String) request.getAttribute("dataForFatherUnitID");
			optionsForUnitType = (String) request.getAttribute("optionsForUnitType");
			optionsForFather = (String) request.getAttribute("optionsForFather");
			session.setAttribute("dataForUnitTypeID",dataForUnitTypeID);
			session.setAttribute("dataForFatherUnitID",dataForFatherUnitID);
			session.setAttribute("optionsForUnitType",optionsForUnitType);
			session.setAttribute("optionsForFather",optionsForFather);
			
			i = 0;
			pag = i;
			end = 100;
			filePath=request.getSession().getServletContext().getRealPath("");
			session.setAttribute("filePath",filePath);		
			
		}
	%>
	<%
		length = str.length;

	%>
	
	<!-- 显示操作信息开始 -->
	<div class="box2" panelTitle="操作">
		<!-- 居左显示查找条件输入框和查找按钮开始 -->
		<div >
			<div >
				<form action="<%=basePath%>servlet/SearchUnitServlet"
					target="frmright" method="post">
					<div class="box2" panelTitle="操作">
						<div>
							<input type="hidden" id="" value=""/>
						</div>
						<table>
						<tr>
						<td>单元名称 <input type="text" style="width:105px;" id="unitName" name="unitName" value="" />单元类型</td>
						<td><select selWidth=105 id="unitTypeID" name="unitTypeID" prompt="空" data='<%=dataForUnitTypeID%>'></select>父节点</td>
						<td><select selWidth=105 id="fatherUnitID" name="fatherUnitID" prompt="空" data='<%=dataForFatherUnitID%>'></select></td>
						<td>坐标X<input type="text" style="width:105px;" id="x" name="x" value="" /></td>
						<td>坐标Y<input type="text" style="width:105px;" id="y" name="y" value="" /></td>
						<td>宽度<input type="text" style="width:105px;" id="width" name="width" value="" /></td>
						<td>高度<input type="text" style="width:105px;" id="height" name="height" value="" /></td>
						<td><input type="submit" id="ch" value="查找" onclick="return searchUnitType();" /></td>
						
						</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
		<!-- 居左显示查找条件输入框和查找按钮结束 -->
		
	</div>
	<!-- 显示操作信息结束 -->
	
	<!-- 显示unitType详细信息开始 -->
	<div id="scrollContent" style="overflow-x:hidden;">
		<!-- 显示unitType详细信息，表头开始 -->
		<table class="tableStyle" mode="list">
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
				<th width="5%">修改</th>
				<th width="5%">删除</th>
			</tr>
		</table>
		<!-- 显示unitType详细信息，表头结束 -->
		
		<!-- 显示unitType详细信息，循环分条显示详细信息开始 -->
		<%
			for (; i < length && i <= end; i += 10) {
				if(str[i+6]==null){
					str[i+6]="";
				}
				if(str[i+7]==null){
					str[i+7]="";
				}
				//文件处理
				file = new File(filePath+"/"+str[i+8]);
				if(!file.exists()){
					str[i+8]=null;
				}
				file = new File(filePath+"/"+str[i+9]);
				if(!file.exists()){
					str[i+9]=null;
				}
		%>
		<form action="<%=basePath%>servlet/ChangeUnitServlet"
			id="changeForm<%=i%>" target="frmright" method="post">
			<div>
				<input type="hidden" id="unitID" name="unitID"
					value="<%=str[i]%>" />
				<table class="tableStyle" mode="list">
					<tr>
						<td width="10%"><div>
								<input type="text" style="width:105px;" id="unitName"
									class="validate[required]"
									name="unitName" value="<%=str[i+1]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%">
									<select selWidth=105 id="unitTypeID" name="unitTypeID" prompt="<%=str[i+2]%>" data='<%=dataForUnitTypeID%>'></select>
						</td>
						<td width="10%">
									<select selWidth=105 id="fatherUnitID" name="fatherUnitID" prompt="<%=str[i+3]%>" data='<%=dataForFatherUnitID%>'></select>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100px;" id="x"
									class="validate[required,custom[onlyNumber]] "
									name="x" value="<%=str[i+4]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100px;" id="y"
									class="validate[required,custom[onlyNumber]] "
									name="y" value="<%=str[i+5]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100px;" id="width"
									class="validate[custom[onlyNumber]] "
									name="width" value="<%=str[i+6]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><div>
								<input type="text" style="width:100px;" id="height"
								class="validate[custom[onlyNumber]] "
									name="height" value="<%=str[i+7]%>"
									onkeydown="javascript:if(event.keyCode=='13')return false;" />
							</div>
						</td>
						<td width="10%"><span id="pictureName"
							class="img_img hand" title="查看"
							onclick="picture('<%=str[i+8]%>');" ></span>
						</td>
						<td width="10%"><span id="thumbnailsName"
							class="img_img hand" title="查看"
							onclick="picture('<%=str[i+9]%>');" ></span>
						</td>
						<td width="5%"><span id="changeUnit<%=i%>"
							class="img_edit hand" title="修改"
							onclick="changeUnit(<%=i%>);" ></span>
						</td>
						<td width="5%"><span id="delete<%=i%>"
							class="img_delete hand" title="删除"
							onclick="delUnit(<%=str[i]%>)" ></span></td>
					</tr>
				</table>
			</div>
		</form>
		<%
		 	}
		%>
	</div>
	<img src="<%=basePath%>libs/icons/png/add.png" onclick="addUnit()" class="hand" />
	
	<!-- 分页按钮开始 -->
	<div class="float_right padding5">
		<div class="pageNumber" total="<%=length / 10%>" showInput="true" page="<%=pag %>"
			id="page" pageSize="10"></div>
	</div>
	<!-- 分页按钮结束 -->

	<script type="text/javascript">
	
		/**
		 *分页时显示不同页的内容
		 */
		$(function(){
			$("#page").bind("pageChange",function(e,index){
				window.location.href="<%=basePath%>jsp/unit/showAllUnit.jsp?page="+index;
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
		* 以弹窗形式查看图片，参数为图片的路径
		*/
		function picture(path){
			//当路径为null时，给出提示
			if(path == null || path == "" || path == "null"){
				alert("对不起，未上传图片！");
				return false;
			}
			var diag = new top.Dialog();
			diag.Title = "图片查看";
			diag.Width=465;
			diag.Height=245;
			diag.ShowMaxButton=true;
			
			diag.URL = "<%=basePath%>" + path;
			diag.show();
		}
		
		/**
		 * 批量增加unit时，跳转到addUnit.jsp页面
		 */
		function addUnit(){
			<%
			//将optionsForUnitType和optionsForFather放入session中，增加中要用到这些数据
			session.setAttribute("optionsForUnitType",optionsForUnitType);
			session.setAttribute("optionsForFather",optionsForFather);
	 		%>
			location.href="<%=basePath%>jsp/unit/addUnit.jsp";
		}
		
		/**
		 * 点击查找时，根据输入的内容进行查找并显示在此页面，当输入全为空时，即全部搜索
		 */
		function searchUnitType(){
			return true;
		}
		
		/**
		 * 删除时提示是否确认删除
		 */
		function delUnit(unitID){
			if(confirm("真的要删除吗？")){
       			location.href="<%=basePath%>servlet/DelUnitServlet?unitID=" + unitID;
			}else{
			}
		}
		
		/**
		 * 修改信息时提示是否确认修改 
		 */
		function changeUnit(i){
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
		/*
		<!-- 左下显示增加按钮开始 -->
	<br/>
	<br/>
		<div class="halfleft" onclick="addUnitType()">
			<div align="center">
				<div>
					<input type="button" value="增加" />
				</div>
			</div>
		</div>
		<!-- 左下显示增加按钮结束 -->
	
		*/
	</script>
	
</body>

</html>
