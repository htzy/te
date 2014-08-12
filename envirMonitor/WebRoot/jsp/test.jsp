<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<form id="unitForm">
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
		target="frmright" enctype="multipart/form-data" method="post">
		<input type="hidden" id="units" name="units" value="" />
		<div class="box1" whiteBg="true">
			<div align="center">
				<input type="button" value="添加" onclick="addHandler()" />
				<input type="button" id="add" name="add" value="保存" onclick="save()" />
				<input type="button" value="取消" onclick="closeWin()"/>
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
					+ "<td width='10%'><div><input style='width:105px;' id='unitName"+sum+"' class='validate[required,custom[illegalLetter]]' name='unitName"+sum+"' "+"value='' /><div></td>"
					+ "<td width='10%'><div><select id='unitTypeName"+sum+"' name='unitTypeName"+sum+"' ><%=optionsForUnitType%></select></div></td>"
					+ "<td width='10%'><div><select id='fatherUnitName"+sum+"' name='fatherUnitName"+sum+"' ><%=optionsForFather%></select></div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='x"+sum+"' class='validate[required,custom[onlyNumber]]' name='x"+sum+"' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='y"+sum+"' class='validate[required,custom[onlyNumber]]' name='y"+sum+"' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='width"+sum+"' class='validate[required,custom[onlyNumber]]' name='width"+sum+"' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='height"+sum+"' class='validate[required,custom[onlyNumber]]' name='height"+sum+"' "+"value='' /><div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='pictureName"+sum+"' type='file' name='pictureName"+sum+"' /></div></td>"
					+ "<td width='10%'><div><input style='width:105px;' id='thumbnailsName"+sum+"' type='file' name='thumbnailsName"+sum+"' /></div></td>"
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

		//关闭窗口
		function closeWin() {
			top.Dialog.close();
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
						alert("还有未填项，请检查重试！");
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
			$("#units").val(units);
			document.getElementById("addForm").submit();
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
			alert("检查");
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
			//图片暂时没有检查
			//全不为空时，返回true
			return true;
		}
		/**
		 * 取得一条记录中各个属性的值
		 */
		 function getUnit(i){
		 	alert("get");
		 	var result = "";
		 	result += document.getElementById("unitName"+i).value + ",";
		 	result += document.getElementById("unitTypeName"+i).value + ",";
		 	result += document.getElementById("fatherUnitName"+i).value + ",";
		 	result += document.getElementById("x"+i).value + ",";
		 	result += document.getElementById("y"+i).value + ",";
		 	result += document.getElementById("width"+i).value + ",";
		 	result += document.getElementById("height"+i).value + ",";
		 	
		 	return result;
		 }
		
	
	//--------------------------------------------分割线--------------------------------

var isIE = (document.all) ? true : false;

var $ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
};

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}

var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
}

var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	}
}

var Each = function(list, fun){
	for (var i = 0, len = list.length; i < len; i++) { fun(list[i], i); }
};

//文件上传类
var FileUpload = Class.create();
FileUpload.prototype = {
  //表单对象，文件控件存放空间
  initialize: function(form, folder, options) {
	
	this.Form = $(form);//表单
	this.Folder = $(folder);//文件控件存放空间
	this.Files = [];//文件集合
	
	//下面的逐个执行！
	
	this.SetOptions(options);
	
	this.FileName = this.options.FileName;
	this._FrameName = this.options.FrameName;
	this.Limit = this.options.Limit;
	this.Distinct = !!this.options.Distinct;
	this.ExtIn = this.options.ExtIn;
	this.ExtOut = this.options.ExtOut;
	
	this.onIniFile = this.options.onIniFile;
	this.onEmpty = this.options.onEmpty;
	this.onNotExtIn = this.options.onNotExtIn;
	this.onExtOut = this.options.onExtOut;
	this.onLimite = this.options.onLimite;
	this.onSame = this.options.onSame;
	this.onFail = this.options.onFail;
	this.onIni = this.options.onIni;
	
	if(!this._FrameName){
		//为每个实例创建不同的iframe
		this._FrameName = "uploadFrame_" + Math.floor(Math.random() * 1000);
		//ie不能修改iframe的name
		var oFrame = isIE ? document.createElement("<iframe name=\"" + this._FrameName + "\">") : document.createElement("iframe");
		//为ff设置name
		oFrame.name = this._FrameName;
		oFrame.style.display = "none";
		//在ie文档未加载完用appendChild会报错
		document.body.insertBefore(oFrame, document.body.childNodes[0]);
	}
	
	//设置form属性，关键是target要指向iframe
	this.Form.target = this._FrameName;
	this.Form.method = "post";
	//注意ie的form没有enctype属性，要用encoding
	this.Form.encoding = "multipart/form-data";

	//整理一次
	this.Ini();
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		FileName:	"filename",//文件上传控件的name，配合后台使用
		FrameName:	"",//iframe的name，要自定义iframe的话这里设置name
		onIniFile:	function(){},//整理文件时执行(其中参数是file对象)
		onEmpty:	function(){},//文件空值时执行
		//Limit:		10,//文件数限制，0为不限制
		onLimite:	function(){},//超过文件数限制时执行
		Distinct:	true,//是否不允许相同文件
		onSame:		function(){},//有相同文件时执行
		ExtIn:		["gif","jpg","png"],//允许后缀名,只允许上传图片！！
		onNotExtIn:	function(){},//不是允许后缀名时执行
		ExtOut:		[],//禁止后缀名，当设置了ExtIn则ExtOut无效
		onExtOut:	function(){},//是禁止后缀名时执行
		onFail:		function(){},//文件不通过检测时执行(其中参数是file对象)
		onIni:		function(){}//重置时执行
    };
    Extend(this.options, options || {});
  },
  //整理空间
  Ini: function() {
	//整理文件集合
	this.Files = [];
	//整理文件空间，把有值的file放入文件集合
	Each(this.Folder.getElementsByTagName("input"), Bind(this, function(o){
		if(o.type == "file"){ o.value && this.Files.push(o); this.onIniFile(o); }
	}))
	//插入一个新的file
	var file = document.createElement("input");
	file.name = this.FileName; file.type = "file"; file.onchange = Bind(this, function(){ this.Check(file); this.Ini(); });
	this.Folder.appendChild(file);
	//执行附加程序
	this.onIni();
  },
  //检测file对象
  Check: function(file) {
	//检测变量
	var bCheck = true;
	//空值、文件数限制、后缀名、相同文件检测
	if(!file.value){
		bCheck = false; this.onEmpty();
	} else if(this.Limit && this.Files.length >= this.Limit){
		bCheck = false; this.onLimite();
	} else if(!!this.ExtIn.length && !RegExp("\.(" + this.ExtIn.join("|") + ")$", "i").test(file.value)){
		//检测是否允许后缀名
		bCheck = false; this.onNotExtIn();
	} else if(!!this.ExtOut.length && RegExp("\.(" + this.ExtOut.join("|") + ")$", "i").test(file.value)) {
		//检测是否禁止后缀名
		bCheck = false; this.onExtOut();
	} else if(!!this.Distinct) {
		Each(this.Files, function(o){ if(o.value == file.value){ bCheck = false; } })
		if(!bCheck){ this.onSame(); }
	}
	//没有通过检测
	!bCheck && this.onFail(file);
  },
  //删除指定file
  Delete: function(file) {
	//移除指定file
	this.Folder.removeChild(file); this.Ini();
  },
  //删除全部file
  Clear: function() {
	//清空文件空间
	Each(this.Files, Bind(this, function(o){ this.Folder.removeChild(o); })); this.Ini();
  }
}

var fu = new FileUpload("uploadForm", "idFile", { 
	onIniFile: function(file){ file.value ? file.style.display = "none" : this.Folder.removeChild(file); },
	//onEmpty: function(){ alert("请选择一个文件"); },
	//onLimite: function(){ alert("超过上传限制"); },
	//onSame: function(){ alert("已经有相同文件"); },
	onNotExtIn:	function(){ alert("只允许上传" + this.ExtIn.join("，") + "文件"); },
	onFail: function(file){ this.Folder.removeChild(file); },
	onIni: function(){
		//显示文件列表
		var arrRows = [];
		if(this.Files.length){
			var oThis = this;
			Each(this.Files, function(o){
				var a = document.createElement("a"); a.innerHTML = "取消"; a.href = "javascript:void(0);";
				a.onclick = function(){ oThis.Delete(o); return false; };
				arrRows.push([o.value, a]);
			});
		} else { arrRows.push(["<font color='gray'>没有添加文件</font>", "&nbsp;"]); }
		AddList(arrRows);
		//设置按钮
		$("idBtnupload").disabled = $("idBtndel").disabled = this.Files.length <= 0;
	}
});

$("idBtnupload").onclick = function(){
	//显示文件列表
	var arrRows = [];
	Each(fu.Files, function(o){ arrRows.push([o.value, "&nbsp;"]); });
	AddList(arrRows);
	
	fu.Folder.style.display = "none";
	$("idProcess").style.display = "";
	$("idMsg").innerHTML = "正在添加文件到您的网盘中，请稍候……<br />有可能因为网络问题，出现程序长时间无响应，请点击“<a href='?'><font color='red'>取消</font></a>”重新上传文件";
	
	fu.Form.submit();
}

//用来添加文件列表的函数
function AddList(rows){
	//根据数组来添加列表
	var FileList = $("idFileList"), oFragment = document.createDocumentFragment();
	//用文档碎片保存列表
	Each(rows, function(cells){
		var row = document.createElement("tr");
		Each(cells, function(o){
			var cell = document.createElement("td");
			if(typeof o == "string"){ cell.innerHTML = o; }else{ cell.appendChild(o); }
			row.appendChild(cell);
		});
		oFragment.appendChild(row);
	})
	//ie的table不支持innerHTML所以这样清空table
	while(FileList.hasChildNodes()){ FileList.removeChild(FileList.firstChild); }
	FileList.appendChild(oFragment);
}


$("idLimit").innerHTML = fu.Limit;

$("idExt").innerHTML = fu.ExtIn.join("，");

$("idBtndel").onclick = function(){ fu.Clear(); }

//在后台通过window.parent来访问主页面的函数
function Finish(msg){ 
msg=msg.replace(/<br[^>]*>/ig,   "\n").replace(/&nbsp;/ig,   "   ");
alert(msg); location.href = location.href; }

//----------------------------------------------------分割线--------------------------------



	</script>
</body>
</html>
