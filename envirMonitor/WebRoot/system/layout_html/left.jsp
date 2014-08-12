<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.monitor.java.ShowUnit,com.monitor.bean.extendBean.UnitHelpExtend,com.monitor.dao.Sensor_functionDAO,com.monitor.factory.Sensor_functionDAOFactory,com.monitor.bean.sensor,com.monitor.bean.extendBean.IntToIP"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="../../libs/js/jquery.js"></script>
<script type="text/javascript" src="../../libs/js/framework.js"></script>
<link href="../../libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="../../" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!-- 树start-->
<script type="text/javascript" src="../../libs/js/tree/ztree/ztree.js"></script>
<link href="../../libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript">
	var setting = {
		callback: {
			onClick: onClick
		}
	};
	var zNodes =[
	<%
	int circleID=501;
	ArrayList list=new ArrayList();
	ArrayList listSensor=new ArrayList();
 	ShowUnit sh=new ShowUnit();
 	Sensor_functionDAO Sensor_functionDAOImplm=Sensor_functionDAOFactory.createSensor_functionDAOImplm();
	list=sh.sortFarther();
	IntToIP ip=new IntToIP();
	for(int i=0;i<list.size();i++){
	   UnitHelpExtend up=(UnitHelpExtend)list.get(i);
	   int level=up.getLevel();//得到级别
	   String unitName=up.getUnitName();
	   int id=up.getId();
	   int parentId=up.getParentId();
	   int UnitID=up.getUnitID();//节点单元对应的的ID号
	   %>
	  
	   { id:<%=id%>, parentId:<%=parentId%>, name:"<%=unitName%>"},
	   <%
	  
	  
	   listSensor=Sensor_functionDAOImplm.findSensorByUnitID(UnitID);//节点对应的传感器链表
	   if(listSensor.size()!=0){//存在该节点下的传感器，则展示
	   for(int j=0;j<listSensor.size();j++){
	   sensor se=(sensor)listSensor.get(j);
	   int id1=se.getSensorID();
	   int parentId1=se.getUnitID();
	   String sensorName="("+ip.IP(se.getEmbededIP())+")"+"("+ip.IP(se.getNodeIP())+"):"+se.getChannelNo();
	   %>
	   {id:<%=circleID%>, parentId:<%=id%>, name:"<%=sensorName%>",url:"../../servlet/SearchByTimeServlet?sensorID=<%=id1%>&first=1", target:"frmright"},
	   <%
	   circleID++;
	   }
	   
	   }
	   }
	  
	   
	   //ArrayList listSensor=
	   

	%>
	
    // { id:004, parentId:3001, name:"传感器1-1-1-1",url:"../../html/layout.html", target:"frmright"}
		
	/*	{ id:3000, parentId:0, name:"一111级节点1"},
		{ id:002, parentId:3000, name:"二级节点1-1"},
    	{ id:003, parentId:002, name:"三级节点1-1-1"},
     	{ id:004, parentId:003, name:"传感器1-1-1-1",url:"../../html/layout.html", target:"frmright"},
	
	
	    { id:3001, parentId:0, name:"一级节点2"},
		{ id:101, parentId:3001, name:"二级节点2-1",url:"../../sample_html/index/css.html", target:"frmright"},
    	{ id:102, parentId:3001, name:"二级节点2-2",url:"../../sample_html/index/icons.html", target:"frmright"},
     	{ id:103, parentId:3001, name:"二级节点2-3",url:"../../sample_html/index/icons.html", target:"frmright"},
	    
	    { id:3002, parentId:0, name:"一级节点3"},
		{ id:201, parentId:3002, name:"二级节点3-1",url:"../../sample_html/index/css.html", target:"frmright"},
    	{ id:202, parentId:3002, name:"二级节点3-2",url:"../../sample_html/index/icons.html", target:"frmright"},
     	{ id:203, parentId:3002, name:"二级节点3-3",url:"../../sample_html/index/icons.html", target:"frmright"}
     	*/
     	
  	];
  	function initComplete(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//每次刷新时保持上次打开的
		showContent();
	}
	function showContent(){
		var treeNodeId=jQuery.jCookie('leftTreeNodeId');
		if(treeNodeId==false||treeNodeId=="false"){}
		else{
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("id", treeNodeId);
			zTree.selectNode(node);
			if(node.url){
				//每次刷新时设置当前位置内容
				if(node.name=="当前位置"){
					top.positionType="normal";
					top.positionContent="当前位置："+node.getParentNode().name+">>"+node.name;
				}
				else{
					top.positionType="none";
				}
				top.frmright.location=node.url;
			}
		}
	}
		
	function onClick(e,treeId, treeNode){
		//单击展开
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		if(treeNode.vision){
			if(treeNode.vision=="person"){
				top.Dialog.alert("抱歉，个人版不包含该功能！",null,null,null,1)
			}
		}
		
		//出现进度条
		if(treeNode.url!=null){
			showProgressBar();
		}
		
		//可以设置某些页面出现或者某些页面不出现当前位置组件
		if(treeNode.name=="当前位置"){
			//每次点击时设置当前位置内容
			top.positionContent="当前位置："+treeNode.getParentNode().name+">>"+treeNode.name;
			top.positionType="normal";
		}
		else{
			top.positionType="none";
		}
		
		//存储点击节点id
		jQuery.jCookie('leftTreeNodeId',treeNode.id.toString());
	}

	function  showAll(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(true);
	}
	function  hideAll(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo")
		treeObj.expandAll(false);
	}
	function customHeightSet(contentHeight){
		var windowWidth=document.documentElement.clientWidth;
		$("#scrollContent").width(windowWidth-4);
		$("#scrollContent").height(contentHeight-25);
	}
</script>

  

<%
 
 /*
    ArrayList list=new ArrayList();
 	ShowUnit sh=new ShowUnit();
	sh.loopCircle(0,0,list);
	
	for(int i=0;i<list.size();i++){
	   UnitHelp up=(UnitHelp)list.get(i);
	   int level=up.getLevel();//得到级别
	   if(level==1){//第一个父节点
	    
	    
	   }else{ //不是父节点
	   
	   
	   }
	 
	 
	
	}
	*/
 /*
 ArrayList list=Unit_functionDAOImplm.findTopUnit(1,0);//得到其对应的顶层单元
 Unit_functionDAO  Unit_functionDAOImplm=Unit_functionDAOFactory.createUnit_functionDAOImplm();
 ArrayList list=Unit_functionDAOImplm.findTopUnit(1,0);//得到其对应的顶层单元
 
 int circleLevel=1;
 int arri=0;
 int total=list.size();
 unit un;
 while(total!=0){
 int unitID;
 for(arri=0;arri<list.size();arri++){
   un=(unit)list.get(arri);
   unitID=un.getUnitID();//得到一级节点的ID号
   //if(circleLevel==1){
    out.print(circleLevel+"级设备为："+un.getUnitName());
   //}
   //list=Unit_functionDAOImplm.findTopUnit(1,unitID);
   
   
  }
   out.print("<br/>");
   list=Unit_functionDAOImplm.findTopUnit(1,unitID);
   total=list.size();
   circleLevel++;
 }
 */
 %>


<!-- 树end -->
<style>
.ztree {
	font-family:sans-serif!important;
}
</style>
</head>

<body leftFrame="true" style="background-color:#f1f8ff">
<div class="padding_top5 ali02" style="height:20px;">
	<a onclick="showAll()">全部展开</a>&nbsp;&nbsp;<a onclick="hideAll()">全部收缩</a>
</div>

<div id="scrollContent" style="overflow-x:hidden;">

	<div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
		
</div>				
</body>
</html>
