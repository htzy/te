var parentTopHeight;
var parentBottomHeight;
var parentTopHeight_left;
var parentBottomHeight_left;
var parentTopHeight_middle;
var parentBottomHeight_middle;
var fixHeight;
var skinName;
var themeColor="blue";
var broswerFlag;
var fontSize=12;
var prePath="../../";
var exitVtab=0;
var vtabIdx=0;
var hasIframe=0;
var parentScrollHeight;

var boxWhiteBg=false;
var splitMode=false;
var positionTarget="";
var box4Custom=false;
var scrollerX=false;
var autoGetSkin=true;
var autoFormat=true;
var autoRender=true;

var boxIe6Flag = 0;
var boxIe7Flag = 0;
var isHeadFixMode = 0;
var headFixExcude=0;
var headFixExcude2=0;

var cResizeTimer=null;
//定义下拉列表的初始层级，select,selectTree,autoComplete都公用
var depth=500;

$(function(){
	//禁掉jquery的ajax请求缓存
	$.ajaxSetup({ cache: false });
	
	//让主框架可以获取iframe的点击事件
	$("body").bind("click",function(){
		try{
			top.iframeClickHandler();
		}
		catch(e){}
	})
	//判断浏览器
	 if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            var bb = window.navigator.userAgent.substring(30, 33);
            if(bb=="6.0"){
				broswerFlag="IE6";
			}
			else if(bb=="7.0"){
				broswerFlag="IE7";
			}
			else if(bb=="8.0"){
				broswerFlag="IE8";
			}
			else if(bb=="9.0"){
				broswerFlag="IE9";
			}
			else if(bb=="10."){
				broswerFlag="IE10";
			}
        }
        else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                broswerFlag="Firefox";
            }
        else if (window.navigator.userAgent.indexOf("Opera") >= 0) {
            broswerFlag="Opera";
        }
        else if (window.navigator.userAgent.indexOf("Safari") >= 1) {
           broswerFlag="Safari";
        }
        else {
            broswerFlag="Other";
        }
	//得到子页面相对于skins路径的前缀，默认为../../		
	var parentSkinUrl;
	if($("#skin").attr("prePath")!=null){
		prePath=$("#skin").attr("prePath");
		
	}
	
	
	if($("#skin").attr("splitMode")==true||$("#skin").attr("splitMode")=="true"){
		splitMode=true;
	}
	else{
		try {
			var o=top.document.getElementById("theme");
		}
		catch(e){
			if($("body").attr("leftFrame")!="true"){
				alert("非IE浏览器本地打开时会产生跨域问题，要把此框架发布到web服务目录下访问。如果要跨域访问，请使用框架的分离模式。");
			}
			return;
		}
		
		var $parentThemeDom=$(window.top.document.getElementById("theme"));
		var $parentSkinDom=$(window.top.document.getElementById("skin"));
		
		if($parentThemeDom.attr("autoGetSkin")==false||$parentThemeDom.attr("autoGetSkin")=="false"){
			autoGetSkin=false;
		}
		if($parentThemeDom.attr("autoFormat")==false||$parentThemeDom.attr("autoFormat")=="false"){
			autoFormat=false;
		}
		if($parentThemeDom.attr("autoRender")==false||$parentThemeDom.attr("autoRender")=="false"){
			autoRender=false;
		}
		if($("#skin").attr("autoRender")==false||$("#skin").attr("autoRender")=="false"){
			autoRender=false;
		}
		if($parentThemeDom.attr("box1WhiteBg")==true||$parentThemeDom.attr("box1WhiteBg")=="true"){
			boxWhiteBg=true;
		}
		if($parentThemeDom.attr("box4Custom")==true||$parentThemeDom.attr("box4Custom")=="true"){
			box4Custom=true;
		}
		if($parentThemeDom.attr("scrollerX")==true||$parentThemeDom.attr("scrollerX")=="true"){
			scrollerX=true;
		}
		if($parentThemeDom.attr("positionTarget")!=null){
			positionTarget=$parentThemeDom.attr("positionTarget");
		}
		
		if($parentThemeDom.attr("href")==null){//当子页面单独打开时
			skinName="system/layout/skin/";
			themeColor="blue";
		}
		else{
			skinName=$parentSkinDom.attr("skinPath");
			themeColor=$parentThemeDom.attr("themeColor");
		}
	}
		
		/*只有当autoGetSkin为true并且splitMode为false才自动获取皮肤路径*/
		if(autoGetSkin==true&&splitMode==false){
			if($parentThemeDom.attr("debug")=="true"||$parentThemeDom.attr("debug")==true){
				if(broswerFlag=="IE6"||broswerFlag=="IE7"){
					if($parentThemeDom.attr("href")==""){//当子页面单独打开时
					}
					else{
						//检测是否正确设置了prePath
						$.ajax({
							url: prePath+"libs/skins/"+themeColor+"/style.css",
							error:function(){
								if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
									alert("导航页面无法通过路径："+prePath+"libs/skins/"+themeColor+"/style.css加载CSS，请检查prePath和主页的themeColor设置的是否正确。FireFox浏览器请把此框架发布到web服务目录下访问。");
								}
								else{
									alert("内容页面无法通过路径："+prePath+"libs/skins/"+themeColor+"/style.css加载CSS，请检查prePath和主页的themeColor设置的是否正确。FireFox浏览器请把此框架发布到web服务目录下访问。");
								}
							},
							success:function(){
								if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
									alert("导航页面的prePath配置成功，组件主题风格已成功加载！");
								}
								else{
									alert("内容页面的prePath配置成功，组件主题风格已成功加载！");
								};
								$.ajax({
									url: prePath+skinName+"style.css",
									error:function(){
										if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
											alert("导航页面无法通过路径："+prePath+skinName+"style.css加载CSS，请检查主页的skinPath设置的是否正确。");
										}
										else{
											alert("内容页面无法通过路径："+prePath+skinName+"style.css加载CSS，请检查主页的skinPath设置的是否正确。");
										}
									},
									success:function(){
										if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
											alert("主页的skinPath配置成功！");
										}
										else{
											alert("主页的skinPath配置成功！");
										}
									}
								})
							}
						})
					}
				}
				else{
					if($parentThemeDom.attr("href")==null){//当子页面单独打开时
					}
					else{
						//检测是否正确设置了prePath
						$.ajax({
							url: prePath+"libs/skins/"+themeColor+"/style.css",
							error:function(){
								if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
									alert("导航页面无法通过路径："+prePath+"libs/skins/"+themeColor+"/style.css加载CSS，请检查prePath和主页的themeColor设置的是否正确。FireFox浏览器请把此框架发布到web服务目录下访问。");
								}
								else{
									alert("内容页面无法通过路径："+prePath+"libs/skins/"+themeColor+"/style.css加载CSS，请检查prePath和主页的themeColor设置的是否正确。FireFox浏览器请把此框架发布到web服务目录下访问。");
								}
							},
							success:function(){
								if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
									alert("导航页面的prePath配置成功，组件主题风格已成功加载！");
								}
								else{
									alert("内容页面的prePath配置成功，组件主题风格已成功加载！");
								};
								$.ajax({
									url: prePath+skinName+"style.css",
									error:function(){
										if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
											alert("导航页面无法通过路径："+prePath+skinName+"style.css加载CSS，请检查主页的skinPath设置的是否正确。");
										}
										else{
											alert("内容页面无法通过路径："+prePath+skinName+"style.css加载CSS，请检查主页的skinPath设置的是否正确。");
										}
									},
									success:function(){
										if($("body").attr("leftFrame")=="true"||$("body").attr("leftFrame")==true){
											alert("主页的skinPath配置成功！");
										}
										else{
											alert("主页的skinPath配置成功！");
										}
									}
								})
							}
						})
					}
				}
			}
			
			$("#skin").attr("href",prePath+"libs/skins/"+themeColor+"/style.css");
			$("#customSkin").attr("href",prePath+skinName+"style.css");
		}
		/*只有当autoGetSkin为true并且splitMode为false才自动获取皮肤路径*/
	
		
	
	
	//初始化信息提示特效
	enableTooltips();
	
	
	//设置初始时字体大小
	try {
		var font=jQuery.jCookie('fontSize');
		if(font!=false){
			fontSize=parseInt(font);
		}
	}
	catch(e){}
	if(fontSize!=12){
		$("body").css({
			"fontSize":fontSize+"px"
		});
		if ($("table:[class=tableStyle]").length > 0) {
			$("table:[class=tableStyle]").css({
				"fontSize":fontSize+"px"
			});
		}
		if ($("pre").length > 0) {
			$("pre").css({
				"fontSize":fontSize+"px"
			});
		}
	}
	
	/*启用自动渲染 渲染box模型*/
	if(autoRender==true){
		$("div").each(function(){
			//盒子模型
			if($(this).hasClass("box1")||$(this).attr("boxType")=="box1"){
				$(this).box1Render();
			}
			else if($(this).hasClass("box2")||$(this).attr("boxType")=="box2"){
				$(this).box2Render();
			}
			else if($(this).hasClass("box4")){
				$(this).box4Render();
			}
		})
	}
	/*启用自动渲染 渲染box模型*/
	
	if ($("body").attr("leftFrame") == "true") {
		$("body").addClass("contentStyleLeft");
	}
	else {
		$("body").addClass("contentStyle");
	}

	//默认禁用横向滚动条
	if(scrollerX==true){
		if($("#skin").attr("scrollerX")=="false"||$("#skin").attr("scrollerX")==false){
			scrollerX=false;
		}
	}
	else{
		if($("#skin").attr("scrollerX")=="true"||$("#skin").attr("scrollerX")==true){
			scrollerX=true;
		}
	}
	if(broswerFlag=="IE6"){
		$("html").css({
			"width":"100%"
		})
	}
	if(scrollerX==false){
		if(broswerFlag!="IE7"){
			$("html").css({
				"overflowX":"hidden"
			})
		}
		else{
			$("body").css({
				"overflowX":"hidden"
			})
		}
	}
	if($("#skin").attr("scrollerY")=="false"||$("#skin").attr("scrollerY")==false){
		/*
if(broswerFlag=="IE6"||broswerFlag=="IE7"){
			$("html").css({
				"overflowY":"hidden"
			})
		}
		else{
			$("body").css({
				"overflowY":"hidden"
			})
		}
*/
		$("html").css({
				"overflowY":"hidden"
			})
	}
	//触发高度处理函数
	triggerCustomHeightSet();
	if ( cResizeTimer ) clearTimeout (cResizeTimer);
   	cResizeTimer = setTimeout ("triggerCustomHeightSet()", 500);
	
	window.onresize = function(){
		if ( cResizeTimer ) clearTimeout (cResizeTimer);
   		cResizeTimer = setTimeout ("triggerCustomHeightSet()", 100);
	}
	
	
	if (autoRender == true) {
		$("div,input,textarea,button,select,form,table,a,img,span").each(function(){
			if ($(this).hasClass("box1") || $(this).hasClass("box2") || $(this).hasClass("box3") || $(this).hasClass("box4")||$(this).attr("boxType")=="box1"||$(this).attr("boxType")=="box2"|| $(this).attr("keepDefaultStyle")=="true"|| $(this).attr("keepDefaultStyle")==true || $(this).attr("fillType")) {
				if($(this).hasClass("imgPreview")){
					$(this).render();
				}
			}
			else {
				$(this).render();
				if($(this).attr("title")){
					if($(this).parents(".selectbox-tree").length>0||$(this).parents(".dbSelectionMode").length>0){}
					else{
						//启用提示特效
						addTooltip($(this)[0]);
					}
				}
				
			}
		})
		$(".spliter").each(function(){
			try{
				if($(this).is("td")){
					$(this).spliterRender();
				}
			}
			catch(e){
				alert("分隔条出错，注意脚本的引入：spliter.js")
			}
		})
	}
	
	//关闭进度条
	closeProgress();
	
	//初始化所在位置
	if(!splitMode){
		if(parent.positionType){
			if(parent.positionType!="none"&&parent.positionContent!=""){
				if(positionTarget==""){
					if(parent.positionType=="normal"){
						createPosition(parent.positionContent,"normal");
					}
					else{
						createPosition(parent.positionContent,"simple");
					}
				}
				else{
					top.createPosition(positionTarget,parent.positionContent);
				}
			}
		}
	}
	
	
	_initComplete();
});


function cusTreeTableLoadLater(obj,value){
	$.ajax({
		url: value,
		error: function(){
			try {
				top.Dialog.alert("数据加载失败，请检查dataPath是否正确");
			}
			catch(e){
				alert("数据加载失败，请检查dataPath是否正确");
			}
		},
		success: function(xml){
			var $tableContent=obj.parents("tr").next().find("table").parents("td");
			$tableContent.html("");
			var $newTable=$(xml);
			$newTable.appendTo($tableContent);
			$newTable.tableRender();
			obj.removeClass("img_loading");
			obj.addClass("img_remove2");
			obj.attr("title","点击收缩");
			obj.parents("tr").next().show();
		}
	})
}


function triggerCustomHeightSet(){
	var windowHeight=document.documentElement.clientHeight;
	_customHeightSet(windowHeight);
}

function _customHeightSet(windowHeight){
	try {
			customHeightSet(windowHeight);
	}
	catch(e){}
	
}
function changeFont(num){//更改字体大小
	$("body").css({
		"fontSize":num+"px"
	});
	if ($("table:[class=tableStyle]").length > 0) {
		$("table:[class=tableStyle]").css({
			"fontSize":num+"px"
		});
	}
	if ($("pre").length > 0) {
		$("pre").css({
			"fontSize":num+"px"
		});
	}
	if($("iframe").length>0){
		for(var i=0;i<$("iframe").length;i++){
			document.getElementsByTagName("iframe")[i].contentWindow.changeFont(num);
		}
	}
}

(function($) {
	$.fn.render = function(){
		//分隔条
		if($(this).hasClass("spliter")){
			try{
				$(this).spliterRender();
			}
			catch(e){
				alert("分隔条出错，注意脚本的引入：spliter.js")
			}
		}
		
		if($(this).is("input")){
			if($(this).attr("type")=="text"){
				//自动完成框
				if($(this).hasClass("autoComplete")){
					$(this).textInputStyleRender();
					try{
						$(this).attr("trueType","autoComplete");
						$(this).autoCompleteRender();
					}
					catch(e){
						alert("自动完成框出错，注意脚本的引入：autoComplete.js")
					}
				}
				if($(this).hasClass("autoCompleteIcon")){
					$(this).attr("trueType","autoComplete");
					$(this).textInputStyleRender();
				}
				//颜色选择器
				else if($(this).hasClass("color")){
					$(this).textInputStyleRender();
					try{
						$(this).attr("trueType","color");
						$(this).colorRender();
					}
					catch(e){
						alert("颜色选择器出错，注意脚本的引入：color.js")
					}
				}
				//日期选择框
				else if($(this).hasClass("date")){
					$(this).attr("trueType","date");
					$(this).dateRender();
				}
				else if($(this).hasClass("dateIcon")){
					$(this).attr("trueType","date");
					$(this).textInputStyleRender();
				}
				//软键盘控件
				else if($(this).hasClass("keypad")){
					$(this).textInputStyleRender();
					try{
						$(this).attr("trueType","keypad");
						$(this).keypadRender()
					}
					catch(e){
						alert("软键盘控件出错，注意脚本的引入：keypad.js")
					}
				}
				//数字步进器
				else if($(this).hasClass("stepper")){
					$(this).textInputStyleRender();
					try{
						$(this).attr("trueType","stepper");
						$(this).stepperRender()
					}
					catch(e){
						alert("数字步进器出错，注意脚本的引入：stepper.js")
					}
				}
				//文本框
				else{
					$(this).attr("trueType","textinput");
					$(this).textinputRender();
				}
			}
			//input按钮渲染
			else if($(this).attr("type")=="button"||$(this).attr("type")=="submit"||$(this).attr("type")=="reset"){
				$(this).buttonInputRender();
			}
			//基本上传控件
			else if($(this).attr("type")=="file"){
				$(this).attr("trueType","file");
				$(this).fileRender();
			}
			//密码框
			else if($(this).attr("type")=="password"){
				$(this).attr("trueType","password");
				$(this).passInputRender();
				
				//软键盘控件
				if($(this).hasClass("keypad")){
					$(this).textInputStyleRender();
					try{
						$(this).attr("trueType","keypad");
						$(this).keypadRender()
					}
					catch(e){
						alert("软键盘控件出错，注意脚本的引入：keypad.js")
					}
				}
			}
			else if($(this).attr("type")=="radio"){
				$(this).attr("trueType","radio");
			}
			else if($(this).attr("type")=="checkbox"){
				$(this).attr("trueType","checkbox");
			}
			else if($(this).attr("type")=="hidden"){
				$(this).attr("trueType","hidden");
			}
		}
		//button模式按钮
		else if($(this).is("button")){
			$(this).buttonRender();
		}
		//文本域
		else if($(this).is("textarea")){
			$(this).attr("trueType","textarea");
			$(this).textareaRender();
		}
		//select下拉框
		else if($(this).is("select")){
			$(this).attr("trueType","select");
			$(this).prev(".mainCon").attr("trueType","q_select");
			$(this).selectRender();
		}
		//table
		else if($(this).is("table")){
			if($(this).hasClass("tableStyle")){
				$(this).tableRender();
			}
			else if($(this).hasClass("treeTable")){
				try{
					$(this).treeTableRender();
				}
				catch(e){
					alert("table树形表格出错，注意脚本的引入：treeTable.js")
				}
			}
			else if($(this).hasClass("detailTable")){
				try{
					$(this).addClass("tableStyle");
					$(this).tableRender();
					$(this).detailTableRender();
				}
				catch(e){
					alert("table父子表格出错，注意脚本的引入：detailTable.js")
				}
			}
		}
		//a
		else if($(this).is("a")){
			if($(this).hasClass("imgPreview")){
				try{
					$(this).imagePreviewRender();
				}
				catch(e){
					alert("图片预览出错，注意脚本的引入：imgPreview.js")
				}
			}
			else if($(this).hasClass("imgZoom")){
				try{
					$(this).imgZoomRender();
				}
				catch(e){
					alert("图片区域放大出错，注意脚本的引入：imgZoom.js")
				}
			}
		}
		//img
		else if($(this).is("img")){
			if($(this).hasClass("imgFrame")){
				try{
					$(this).imgFrameRender();
				}
				catch(e){
					alert("图片边框渲染出错，注意脚本的引入：imgFrame.js")
				}
			}
			else if($(this).hasClass("imgFade")){
				try{
					$(this).imgFadeRender();
				}
				catch(e){
					alert("图片渐显出错，注意脚本的引入：imgFade.js")
				}
			}
		}
		else if($(this).is("div")){
			//盒子模型
			if($(this).hasClass("box1")||$(this).attr("boxType")=="box1"){
				$(this).box1Render();
			}
			else if($(this).hasClass("box2")||$(this).attr("boxType")=="box2"){
				$(this).box2Render();
			}
			else if($(this).hasClass("box4")){
				$(this).box4Render();
			}
			//浮动面板
			else if($(this).hasClass("floatPanel")){
				try{
					$(this).floatPanelRender();
				}
				catch(e){
					alert("浮动面板出错，注意脚本的引入：floatPanel.js")
				}
			}
			//树形下拉框
			else if($(this).hasClass("selectTree")){
				//try{
					$(this).attr("trueType","selectTree");
					$(this).selectTreeRender();
				//}
				//catch(e){
				//	alert("树形下拉框出错，注意脚本的引入：selectTree.js，ztree.js和ztree.css")
				//}
			}
			//自定义下拉框
			else if($(this).hasClass("selectCustom")){
				try{
					$(this).attr("trueType","selectCustom");
					$(this).selectCustomRender();
				}
				catch(e){
					alert("自定义下拉框出错，注意脚本的引入：selectCustom.js")
				}
			}
			//自动提示框
			else if($(this).hasClass("suggestion")){
				try{
					$(this).attr("trueType","suggestion");
					$(this).suggestionRender();
				}
				catch(e){
					alert("自动提示框出错，注意脚本的引入：suggestion.js")
				}
			}
			//条件过滤器
			else if($(this).hasClass("filter")){
				try{
					$(this).attr("trueType","filter");
					$(this).filterRender();
				}
				catch(e){
					alert("条件过滤器出错，注意脚本的引入：filter.js")
				}
			}
			//双向选择器
			else if($(this).hasClass("lister")){
				try{
					$(this).attr("trueType","lister");
					$(this).listerRender();
				}
				catch(e){
					alert("双向选择器出错，注意脚本的引入：lister.js")
				}
			}
			//树形双选器
			else if($(this).hasClass("listerTree")){
				try{
					$(this).attr("trueType","listerTree");
					$(this).listerTreeRender();
				}
				catch(e){
					alert("树形双选器出错，注意脚本的引入：listerTree.js")
				}
			}
			//评星级控件
			else if($(this).hasClass("rating")){
				try{
					$(this).attr("trueType","rating");
					$(this).ratingRender();
				}
				catch(e){
					alert("评星级控件出错，注意脚本的引入：rating.js")
				}
			}
			//弹出式菜单
			else if($(this).hasClass("popupMenu")){
				$(this).popupMenuRender();
			}
			//基本选项卡
			else if($(this).hasClass("basicTab")){
				try{
					$(this).basicTabRender();
				}
				catch(e){
					alert("基本选项卡出错，注意脚本的引入：basicTab.js")
				}
			}
			else if($(this).hasClass("basicTabModern")){
				try{
					$(this).basicTabModernRender();
				}
				catch(e){
					alert("基本选项卡出错，注意脚本的引入：basicTabModern.js")
				}
			}
			//纵向选项卡
			else if($(this).hasClass("verticalTab")){
				try{
					$(this).verticalTabRender();
				}
				catch(e){
					alert("纵向选项卡出错，注意脚本的引入：verticalTab.js")
				}
			}
			//单级纵向导航
			else if($(this).hasClass("singleNav")){
				$(this).singleNavRender();
			}
			//单级纵向导航（迷你）
			else if($(this).hasClass("singleNavMin")){
				$(this).singleNavMinRender();
			}
			//抽屉容器
			else if($(this).hasClass("accordition")){
				try{
					$(this).accorditionRender();
				}
				catch(e){
					alert("抽屉容器出错，注意脚本的引入：accordion.js")
				}
			}
			//图标导航 鼠标移入变色
			else if($(this).hasClass("navIcon")){
				$(this).hover(function(){
					$(this).addClass("navIcon_hover");
				},function(){
					$(this).removeClass("navIcon_hover");
				})
			}
			//图标工具箱 鼠标移入变色
			else if($(this).hasClass("navIconSmall")){
				$(this).hover(function(){
					$(this).addClass("navIconSmall_hover");
				},function(){
					$(this).removeClass("navIconSmall_hover");
				})
			}
			//分页组件
			else if($(this).hasClass("pageNumber")){
				try{
					$(this).pageNumberRender();
				}
				catch(e){
					alert("数字分页组件出错，注意脚本的引入：pageNumber.js")
				}
			}
			else if($(this).hasClass("pageArrow")){
				try{
					$(this).pageArrowRender();
				}
				catch(e){
					alert("箭头分页组件出错，注意脚本的引入：pageArrow.js")
				}
			}
		}
		else if($(this).is("span")){
			if($(this).hasClass("img_light")){
				$(this).addClass("hand");
				$(this).hover(function(){
					$(this).removeClass("img_light");
					$(this).addClass("img_lightOn");
				},function(){
					$(this).addClass("img_light");
					$(this).removeClass("img_lightOn");
				});
			}
		}
	};
	$.fn.setValue = function(value,text){
		var $instance=$(this);
		if($instance.attr("trueType")=="select"){
			$instance.attr("selectedValue",value)
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="selectTree"){
			$instance.attr("selectedValue",value)
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="selectCustom"){
			$instance.selectCustomSetValue(value,text);
		}
		else if($instance.attr("trueType")=="suggestion"){
			$instance.suggestionSetValue(value,text);
		}
		else if($instance.attr("trueType")=="lister"){
			$instance.listerSetValue(value);
		}
		else if($instance.attr("trueType")=="listerTree"){
			$instance.listerTreeSetValue(value);
		}
		else if($instance.attr("trueType")=="filter"){
			$instance.attr("selectedValue",value)
	     	$instance.render();
		}
	};
	$.fn.resetValue = function(){
		var $instance=$(this);
		if($instance.attr("trueType")=="select"){
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="selectTree"){
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="lister"){
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="listerTree"){
	     	$instance.render();
		}
		else if($instance.attr("trueType")=="filter"){
	     	$instance.render();
		}
	};
	$.fn.addItem = function(value){
		var $instance=$(this);
		if($instance.attr("trueType")=="select"){
			$instance.selectAddItem(value);
		}
		else if($instance.attr("trueType")=="selectTree"){
	     	$instance.selectTreeAddItem(value);
		}
		else if($instance.attr("trueType")=="lister"){
	     	$instance.listerAddItem(value);
		}
		else if($instance.attr("trueType")=="listerTree"){
	     	$instance.listerTreeAddItem(value);
		}
	};
	$.fn.removeItem = function(value){
		var $instance=$(this);
		if($instance.attr("trueType")=="select"){
			$instance.selectRemoveItem(value);
		}
		else if($instance.attr("trueType")=="selectTree"){
	     	$instance.selectTreeRemoveItem(value);
		}
		else if($instance.attr("trueType")=="lister"){
	     	$instance.listerRemoveItem(value);
		}
		else if($instance.attr("trueType")=="listerTree"){
	     	$instance.listerTreeRemoveItem(value);
		}
	};
	$.fn.selectValue = function(value){
		var $instance=$(this);
		if($instance.attr("trueType")=="lister"){
	     	$instance.listerSelectValue(value);
		}
		else if($instance.attr("trueType")=="listerTree"){
	     	$instance.listerTreeSelectValue(value);
		}
	};
	$.fn.unSelectValue = function(value){
		var $instance=$(this);
		if($instance.attr("trueType")=="lister"){
	     	$instance.listerUnSelectValue(value);
		}
		else if($instance.attr("trueType")=="listerTree"){
	     	$instance.listerTreeUnSelectValue(value);
		}
	};
	$.fn.box1Render = function() {
		var con;
		if($(this).find(".boxContent").length>0){
			
		}
		else{
			con=$(this).html();
			$(this).empty();
			if($(this).attr("whiteBg")=="true"||$(this).attr("whiteBg")==true||boxWhiteBg==true){
				if($(this).hasClass("box1")){
					$(this).addClass("box1_white");
				}
			}
			$("<div class='box_topcenter'><div class='box_topleft'><div class='box_topright'></div></div></div>").appendTo($(this));
			$("<div class='box_middlecenter'><div class='box_middleleft'><div class='box_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
			$("<div class='box_bottomcenter'><div class='box_bottomleft'><div class='box_bottomright'></div></div></div>").appendTo($(this));
			$(this).find(".boxContent").html(con);
		}
		$(this).box1Build();	
			
	};
	$.fn.box1Build= function(){
		if($(this).attr("panelWidth")!=null){
			var panelWidth=$(this).attr("panelWidth");
			var lastStr=panelWidth.substr(panelWidth.length-1,1);
			if(lastStr=="%"){
				$(this).width(panelWidth);
			}
			else{
				var trueWidth=Number($(this).attr("panelWidth"));
				$(this).width(trueWidth);
			}
			
		}
		
		if($(this).attr("panelHeight")!=null){
			var useHeight
			if($(this).attr("whiteBg")=="true"||$(this).attr("whiteBg")==true){
				//$(this).find(".box1_topcenter2").height(20);
				//$(this).find(".box1_bottomcenter2").height(22);
				useHeight=Number($(this).attr("panelHeight"))-$(this).find(".box1_topcenter2").outerHeight()-$(this).find(".box1_bottomcenter2").outerHeight();
			}
			else{
				//$(this).find(".box1_topcenter").height(20);
				//$(this).find(".box1_bottomcenter").height(22);
				useHeight=Number($(this).attr("panelHeight"))-$(this).find(".box1_topcenter").outerHeight()-$(this).find(".box1_bottomcenter").outerHeight();
			}
			$(this).find(".boxContent").height(useHeight);
		}
		
		if($(this).attr("overflow")=="true"||$(this).attr("overflow")==true){
			$(this).find(".boxContent").css({
				"overflow":"auto"
			})
		}
		else if($(this).attr("overflow")=="false"||$(this).attr("overflow")==false){
			$(this).find(".boxContent").css({
				"overflow":"hidden"
			})
		}
		else{
			$(this).find(".boxContent").css({
				"overflow":"visible"
			})
		}
		if($(this).attr("position")=="center"){
			$(this).addClass("center");
		}	
		else{
			$(this).removeClass("center");
		}
	}
	$.fn.box2Close=function() {
		var state=$(this).box2GetState();
		if(!state){
			return;
		}
		$(this).find(".ss").click();
	};
	$.fn.box2Open=function() {
		var state=$(this).box2GetState();
		if(state){
			return;
		}
		$(this).find(".ss").click();
	};
	$.fn.box2ChangeState= function() {
		$(this).find(".ss").click();
	};
	$.fn.box2GetState=function() {
		//true为展开,false为关闭
		var state;
		if($(this).find(".boxContent")[0].style.display=="none"){
			state=false;
		}
		else{
			state=true;
		}
		return state;
	};
	$.fn.box2Render = function() {
			var con;
		if($(this).find(".boxContent").length>0){}
		else{
			con=$(this).html();
			$(this).empty();
			$("<div class='box_topcenter' id='box_topcenter'><div class='box_topleft'><div class='box_topright'><div class='title'><span></span></div><div class='boxSubTitle'></div>"+
			"<div class='status'><span class='ss'><a></a></span></div><div class='clear'></div></div></div></div>").appendTo($(this));
			$("<div class='box_middlecenter'><div class='box_middleleft'><div class='box_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
			$("<div class='box_bottomcenter' id='box_bottomcenter'><div class='box_bottomleft'><div class='box_bottomright'></div></div></div>").appendTo($(this));
			
			$(this).find(".boxContent").html(con);
		}
			
		$(this).box2Build();					
	};
	$.fn.box2Build= function(){
		var $instance=$(this);
		var $titleDom=$instance.find(".title");
		var $span=$titleDom.find("span");
		if($instance.attr("boxType")==null){
			$instance.attr("boxType","box2");
		}
		if($instance.attr("panelTitle")!=null){
			$span.text($instance.attr("panelTitle"));
		}
		if($instance.attr("iconClass")!=null){
			$span.addClass($instance.attr("iconClass"));
			$span.css({
				"backgroundPosition":"0 50%"
			})
		}
		else if($instance.attr("iconSrc")!=null){
				$span.css({
					"backgroundImage":"url("+$(this).attr("iconSrc")+")",
					"backgroundRepeat":"no-repeat",
					"backgroundPosition":"0 50%",
					"display":"block",
					"paddingLeft":"18px"
				})
		}
		
		if($instance.attr("panelSubTitle")!=null){
			$(this).find(".boxSubTitle").text($instance.attr("panelSubTitle"));
		}
		if($instance.attr("panelSubTitleColor")!=null){
			$(this).find(".boxSubTitle").css({
				"color":$instance.attr("panelSubTitleColor")
			})
		}
		if($(this).attr("panelWidth")!=null){
			var panelWidth=$(this).attr("panelWidth");
			var lastStr=panelWidth.substr(panelWidth.length-1,1);
			if(lastStr=="%"){
				$(this).width(panelWidth);
			}
			else{
				var trueWidth=Number($(this).attr("panelWidth"));
				$(this).width(trueWidth);
			}
			
		}
		
		if($(this).attr("panelHeight")!=null){
			var useHeight=Number($(this).attr("panelHeight"))-$(this).find("#box2_topcenter").outerHeight()-$(this).find("#box2_bottomcenter").outerHeight();
			$(this).find(".boxContent").height(useHeight);
		}
		if($(this).attr("overflow")=="true"||$(this).attr("overflow")==true){
			$(this).find(".boxContent").css({
				"overflow":"auto"
			})
		}
		else if($(this).attr("overflow")=="false"||$(this).attr("overflow")==false){
			$(this).find(".boxContent").css({
				"overflow":"hidden"
			})
		}
		else{
			$(this).find(".boxContent").css({
				"overflow":"visible"
			})
		}
		var showSt="true";
		if ($(this).attr("showStatus") != null) {
			showSt=$(this).attr("showStatus");
		}
		var useUrl="javascript:;";
		if ($(this).attr("panelUrl") != null) {
			useUrl=$(this).attr("panelUrl");
		}
		var useTarget="_self";
		if ($(this).attr("panelTarget") != null) {
			useTarget=$(this).attr("panelTarget");
		}
		var useStatusText="收缩";
		if ($(this).attr("statusText") != null) {
			useStatusText=$(this).attr("statusText");
		}
		var afterStatusText="展开";
		if ($(this).attr("afterStatusText") != null) {
			afterStatusText=$(this).attr("afterStatusText");
		}
		var $ssDom=$(this).find(".ss");
		$ssDom.unbind("click");
		var oldHeight;
		var statusType="visibleChange";
		if ($(this).attr("statusType") != null) {
			statusType=$(this).attr("statusType");
		}
		var startState="open";
		if ($(this).attr("startState") != null) {
			startState=$(this).attr("startState");
		}
		if(statusType=="visibleChange"&&showSt=="true"&&startState=="open"){
			$ssDom.text(useStatusText);
			$ssDom.toggle(function(){
				var obj=$(this).parents('div[boxType="box2"]').find(".boxContent");
				oldHeight=obj.height();
				if(window.navigator.userAgent.indexOf("MSIE") >= 1){
					obj.fadeOut(300,function(){
						$instance.trigger("stateChange","hide");
					});
				}
				else{
					obj.hide(300,function(){
						$instance.trigger("stateChange","hide");
					});
				}
				$(this).text(afterStatusText);
				
			},function(){
				var obj=$(this).parents('div[boxType="box2"]').find(".boxContent");
				obj.height(oldHeight);
				if(window.navigator.userAgent.indexOf("MSIE") >= 1){
					obj.fadeIn(300,function(){
						$instance.trigger("stateChange","show");
					});
				}
				else{
					obj.show(300,function(){
						$instance.trigger("stateChange","show");
					});
				}
				if($(this).parents('div[boxType="box2"]').attr("panelHeight")==null){
					setTimeout(function(){
						obj.css({
						"height":"auto"
					});
					},500);
				}
				$(this).text(useStatusText);
			})
		}
		else if(statusType=="visibleChange"&&showSt=="true"&&startState=="close"){
			$ssDom.text(useStatusText);
			var obj=$(this).find(".boxContent");
			oldHeight=obj.height();
			obj.hide();
			
			$ssDom.toggle(function(){
				var obj=$(this).parents('div[boxType="box2"]').find(".boxContent");
				obj.height(oldHeight);
				if(window.navigator.userAgent.indexOf("MSIE") >= 1){
					obj.fadeIn(300,function(){
						$instance.trigger("stateChange","show");
					});
				}
				else{
					obj.show(300,function(){
						$instance.trigger("stateChange","show");
					});
				}
				if($(this).parents('div[boxType="box2"]').attr("panelHeight")==null){
					setTimeout(function(){
						obj.css({
						"height":"auto"
					});
					},500);
				}
				$(this).text(afterStatusText);
			},function(){
				if(window.navigator.userAgent.indexOf("MSIE") >= 1){
					obj.fadeOut(300,function(){
						$instance.trigger("stateChange","hide");
					});
				}
				else{
					obj.hide(300,function(){
						$instance.trigger("stateChange","hide");
					});
				}
				$(this).text(useStatusText);
			})
		}
		else if(statusType=="link"&&showSt=="true"&&$(this).attr("statusText") != null){
			$ssDom.find("a").attr("href",useUrl);
			$ssDom.find("a").attr("target",useTarget);
			$ssDom.find("a").text(useStatusText);
		}
		else if(showSt=="true"&&$(this).attr("statusText") != null){
			$ssDom.text(useStatusText);
			$ssDom.css({
				"cursor":"auto"
			})
		}
		else{
			$ssDom.hide();
		}
	}
	
	
	$.fn.box4Render = function() {
		var con;
		if ($(this).find(".boxContent").length > 0) {
		}
		else {
			con = $(this).html();
			$(this).empty();
			if(box4Custom){
				$("<div class='box4_topcenter_notitle2' id='box4_notitle'><div class='box4_topleft_notitle2'><div class='box4_topright_notitle2'></div></div></div>").appendTo($(this));
				$("<div class='box4_topcenter2' id='box4_hastitle'><div class='box4_topleft2'><div class='box4_topright2'><div class='title'></div></div></div></div>").appendTo($(this));
				$("<div class='box4_middlecenter2'><div class='box4_middleleft2'><div class='box4_middleright2'><div class='boxContent'></div></div></div></div>").appendTo($(this));
				$("<div class='box4_bottomcenter2' id='box4_bottomcenter'><div class='box4_bottomleft2'><div class='box4_bottomright2'></div></div></div>").appendTo($(this));
			}else{
				$("<div class='box4_topcenter_notitle' id='box4_notitle'><div class='box4_topleft_notitle'><div class='box4_topright_notitle'></div></div></div>").appendTo($(this));
				$("<div class='box4_topcenter' id='box4_hastitle'><div class='box4_topleft'><div class='box4_topright'><div class='title'></div></div></div></div>").appendTo($(this));
				$("<div class='box4_middlecenter'><div class='box4_middleleft'><div class='box4_middleright'><div class='boxContent'></div></div></div></div>").appendTo($(this));
				$("<div class='box4_bottomcenter' id='box4_bottomcenter'><div class='box4_bottomleft'><div class='box4_bottomright'></div></div></div>").appendTo($(this));
			}
			$(this).find(".boxContent").html(con);
		}
			$(this).box4Build();
	};
	
	$.fn.box4Build= function(){
		if($(this).attr("panelTitle")!=null){
			$(this).find(".title").text($(this).attr("panelTitle"));
		}
		var $notilteTop=$(this).find("#box4_notitle");
		var $tilteTop=$(this).find("#box4_hastitle");
		$notilteTop.hide();
		$tilteTop.hide();
		if($(this).attr("noTitle")=="true"||$(this).attr("noTitle")==true){
			$notilteTop.show();
		}
		else{
			$tilteTop.show();
		}	
		if($(this).attr("panelWidth")!=null){
			var panelWidth=$(this).attr("panelWidth");
			var lastStr=panelWidth.substr(panelWidth.length-1,1);
			if(lastStr=="%"){
				$(this).width(panelWidth);
			}
			else{
				var trueWidth=Number($(this).attr("panelWidth"));
				$(this).width(trueWidth);
			}
			
		}			
		if($(this).attr("panelHeight")!=null){
			$(this).find(".box4_topcenter").height(27);
			$(this).find(".box4_bottomcenter").height(5);
			var useHeight;
			if($(this).attr("noTitle")=="true"||$(this).attr("noTitle")==true){
				useHeight=Number($(this).attr("panelHeight"))-$(this).find("#box4_notitle").outerHeight()-$(this).find("#box4_bottomcenter").outerHeight();
			}
			else{
				useHeight=Number($(this).attr("panelHeight"))-$(this).find("#box4_hastitle").outerHeight()-$(this).find("#box4_bottomcenter").outerHeight();
			}
			$(this).find(".boxContent").height(useHeight);
		}
		if($(this).attr("overflow")=="true"||$(this).attr("overflow")==true){
			$(this).find(".boxContent").css({
				"overflow":"auto"
			})
		}
		else if($(this).attr("overflow")=="false"||$(this).attr("overflow")==false){
			$(this).find(".boxContent").css({
				"overflow":"hidden"
			})
		}
		else{
			$(this).find(".boxContent").css({
				"overflow":"visible"
			})
		}
		var $instance=$(this);
		$instance.find("li a").unbind("click");
		$instance.find("li a").each(function(i){
			$(this).click(function(){
				$instance.find("li a").removeClass("current");
				$(this).addClass("current");
			})
		})
	}
	
	$.fn.textinputRender = function() {
		if ($(this).attr("inputMode")) {
			var inputMode = $(this).attr("inputMode");
			if (inputMode == "numberOnly") {
				var field = $(this)[0];
				var processInputValue = function(){
					field.value = field.value.replace(/\D/g, '');
					if (!validateInput(field.value, "^(0|[1-9][0-9]*)$")) {
						field.value = field.value.substring(1);
					}
				};
				$(this)[0].onkeyup = function(){
					processInputValue();
				}
				$(this)[0].onafterpaste = function(){
					processInputValue();
				}
			}
			else if (inputMode == "positiveDecimal") {
				var field = $(this)[0];
				var processInputValue = function(){
					field.value = field.value.replace(/[^0-9\.]/g, '');
					//alert(field.value.length)
					if (!validateInput(field.value, "^(([1-9]+)|([1-9]+)\.{1}|([0-9]+\.{1}[0-9]+))$")) {
						field.value = field.value.substring(0,field.value.length-1);
					}
				};
				$(this)[0].onkeyup = function(){
					processInputValue();
				}
				$(this)[0].onafterpaste = function(){
					processInputValue();
				}
			}
		}
		//if($(this).attr("inputMode")){
			//var inputMode=$(this).attr("inputMode");
			
			/*
//只允许数字但第一位为0无限制
if(inputMode=="numberOnly"){
				$(this)[0].onkeyup=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
				$(this)[0].onafterpaste=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
			}
*/
			/*
else if(inputMode=="normalOnly"){
				$(this)[0].lastValue = $(this)[0].value;
				$(this)[0].onkeyup=function(){
					var value = $(this)[0].value;
					if(validateInput(value,'^[1-9][0-9]*$')){
						$(this)[0].lastValue = value;
					}else{
						$(this)[0].value = $(this)[0].lastValue;
					}
				}
				$(this)[0].onafterpaste=function(){
					var value = $(this)[0].value;
					if(validateInput(value,'^[1-9][0-9]*$')){
						$(this)[0].lastValue = value;
					}else{
						$(this)[0].value = $(this)[0].lastValue;
					}
				}
			}
*/
		//}
		if($(this).attr("class")=="keypad"){
			return;
		}
		$(this).addClass("textinput");
			var focusFlag=null;
			$(this).hover(
				function() {
					if(focusFlag!=$(this)[0]){
						$(this).removeClass("textinput");
						$(this).addClass("textinput_hover");
					}
					},
				function(){
					if(focusFlag!=$(this)[0]){
						$(this).removeClass("textinput_hover");
						$(this).addClass("textinput");
					}
					}
			);
			$(this).focus(function(){
				focusFlag=$(this)[0];
				$(this).removeClass("textinput");
				$(this).removeClass("textinput_hover");
				$(this).addClass("textinput_click");
			});
			$(this).blur(function(){
				focusFlag=null;
				$(this).removeClass("textinput_click");
				$(this).addClass("textinput");
			});
			if($(this).attr("clearable")=="true"){
				$(this).clearableTextField();
			}
			if ($(this).attr("maxNum")!=null) {
				$(this).maxlength({maxCharacters:parseInt($(this).attr("maxNum"))});
			}
			if ($(this).attr("watermark") != null) {
				$(this).watermark('watermark',$(this).attr("watermark"));
			}
	};
	
	$.fn.fileRender = function() {
		var fileInputWidth=188;
		if($(this).attr("fileWidth")){
			fileInputWidth=Number($(this).attr("fileWidth"));
		}
		//$(this).attr("contenteditable",false);
		$(this).addClass("fileComponent");
		$(this).wrap('<div class="file-container"></div>');
		
		var $fileTableContainer=$('<table cellspacing="0" cellpadding="0" style="border-style:none;position:absolute;z-index:10;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"><input type="text" class="textinput"/></td><td class="ali01" style="border-style:none;;padding:0;margin:0;"><input type="button" class="fileBtn" value="" /></td></tr></table>')
		var $fileContainer=$(this).parent()
		$fileContainer.wrap('<div class="file-container-main"></div>');
		var $fileContainerMain=$fileContainer.parent()
		$fileContainer.prepend($fileTableContainer);
		var $fileInput = $fileTableContainer.find("input[type=text]");
		$fileInput.width(fileInputWidth-60);
		if($.browser.msie){
			$(this).width(fileInputWidth);
		}
		$fileContainer.width(fileInputWidth);
		$fileContainerMain.width(fileInputWidth);
		
		$(this).css({
			'position': 'absolute',
			'z-index': 20,
			'font-size': '118px',
			'opacity': '0',
			'left': '0px',
			'top': '0px'
		});
		$(this).change(function() {
			var filePath="";
			if($.browser.msie){
				$(this)[0].select()
				filePath=document.selection.createRange().text;
			}
			else if(broswerFlag=="Firefox"){
				//filePath=$(this)[0].files[0].getAsDataURL()
				//filePath=window.URL.createObjectURL($(this)[0].files[0]);
				filePath=$(this).val();
			}
			else{
				var filePathArray=$(this).val().toString().split("\\");
				filePath=filePathArray[filePathArray.length-1];
				/*
				var ofile=$(this)[0].files[0];
				var oFReader=new FileReader();
				oFReader.onload=function(oFREvent){
					filePath=oFREvent.target.result;
					alert(filePath)
				}
				oFReader.readAsDataURL(ofile);
				*/
				
			}
			$(this).parent().find("input[type=text]").val(filePath);
			if($(this).attr("showInfo")!="false"){
				try {
					$(this).attr("title",filePath);
					addTooltip($(this)[0]);
				}
				catch(e){}
			}
		});
	};
	
	$.fn.textInputStyleRender = function() {
		var focusFlag=null;
		if($(this).attr("inputMode")){
			var inputMode=$(this).attr("inputMode");
			if(inputMode=="numberOnly"){
				$(this)[0].onkeyup=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
				$(this)[0].onafterpaste=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
			}
		}
		$(this).hover(
			function() {
				if(focusFlag!=$(this)[0]){
					$(this).addClass("date_hover");
				}
				},
			function(){
				if(focusFlag!=$(this)[0]){
					$(this).removeClass("date_hover");
				}
				}
		);
		$(this).focus(function(){
			focusFlag=$(this)[0];
			$(this).removeClass("date_hover");
			$(this).addClass("date_click");
		});
		$(this).blur(function(){
			focusFlag=null;
			$(this).removeClass("date_click");
		});
	};
	
	$.fn.passInputRender = function() {
		var focusFlag=null;
		$(this).addClass("textinput");
		if($(this).attr("inputMode")){
			var inputMode=$(this).attr("inputMode");
			if(inputMode=="numberOnly"){
				$(this)[0].onkeyup=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
				$(this)[0].onafterpaste=function(){
					$(this)[0].value=$(this)[0].value.replace(/\D/g,'')
				}
			}
		}
			$(this).hover(
				function() {
					if(focusFlag!=$(this)[0]){
						$(this).removeClass("textinput");
						$(this).addClass("textinput_hover");
					}
					},
				function(){
					if(focusFlag!=$(this)[0]){
						$(this).removeClass("textinput_hover");
						$(this).addClass("textinput");
					}
					}
			);
			$(this).focus(function(){
				focusFlag=$(this)[0];
				$(this).removeClass("textinput");
				$(this).removeClass("textinput_hover");
				$(this).addClass("textinput_click");
			});
			$(this).blur(function(){
				focusFlag=null;
				$(this).removeClass("textinput_click");
				$(this).addClass("textinput");
			});
			if($(this).attr("clearable")=="true"){
				$(this).clearableTextField();
			}
			if ($(this).attr("maxNum")!=null) {
				$(this).maxlength({maxCharacters:parseInt($(this).attr("maxNum"))});
			}
			if($(this).attr("checkStrength")=="true"){
				$(this).password_strength();
			}
			$(this).caps(function(caps){
			    if(jQuery.browser.safari) return; 
			    if(caps){
					$.cursorMessage('注意：大写键开启了');
			    }else{
			    }
			});
	};
	
	$.fn.textareaRender = function() {
		var focusFlag=null;
		$(this).addClass("textarea");
		
		if ($(this).attr("maxNum")!=null) {
			$(this).maxlength({maxCharacters:parseInt($(this).attr("maxNum"))});
		}
		if ($(this).attr("resize")=="true"){
			$(this).TextAreaResizer();
		}
		if ($(this).attr("autoHeight")=="true"){
			$(this).css({
				height:"auto"
			});
			$(this).attr("rows",5);
			$(this).autoGrow();
		}
		if ($(this).attr("watermark") != null) {
			$(this).watermark('watermark',$(this).attr("watermark"));
		}
		
		$(this).hover(
			function() {
				if(focusFlag!=$(this)[0]){
					$(this).removeClass("textarea");
					$(this).addClass("textarea_hover");
				}},
			function(){
				if(focusFlag!=$(this)[0]){
					$(this).removeClass("textarea_hover");
					$(this).addClass("textarea");
				}}
		);
		$(this).focus(function(){
			focusFlag=$(this)[0];
			$(this).removeClass("textarea");
			$(this).removeClass("textarea_hover");
			$(this).addClass("textarea_click");
		});
		$(this).blur(function(){
			focusFlag=null;
			$(this).removeClass("textarea_click");
			$(this).addClass("textarea");
		});
	};
	
	$.fn.buttonInputRender = function() {
		if(!$(this).attr("class")){
			$(this).addClass("button");
		}
		var btnTextNum=_getStrLength($(this).val());
		if($(this).attr("useMinWidth")=="false"||$(this).attr("useMinWidth")==false){}
		else{
			if(btnTextNum<5){
				$(this).width(60);
			}
		}
		$(this).hover(
			function() {
					$(this).addClass("button_hover");
				},
			function(){
					$(this).removeClass("button_hover");
				}
		);

		if($(this).attr("toggle")=="true"||$(this).attr("toggle")==true){
			//创建隐藏域
			var $hidden = $("<input type='hidden'/>");
			if($(this).attr("name")!=null){
				$hidden.attr("name",$(this).attr("name"));
			}
			$(this).after($hidden);
			var toggleValue=0;
			if($(this).attr("relValue")=="1"){
				toggleValue=1;
			}
			$(this).attr("relValue",toggleValue);
			$hidden.attr("relValue",toggleValue);
			if(toggleValue==0){
				$(this).toggle(function(){
					$(this).addClass("toggle");
					$(this).attr("relValue",1);
					$hidden.attr("relValue",1);
				},function(){
					$(this).removeClass("toggle");
					$(this).attr("relValue",0);
					$hidden.attr("relValue",0);
				})
			}
			else{
				$(this).addClass("toggle");
				$(this).toggle(function(){
					$(this).removeClass("toggle");
					$(this).attr("relValue",0);
					$hidden.attr("relValue",0);
				},function(){
					$(this).addClass("toggle");
					$(this).attr("relValue",1);
					$hidden.attr("relValue",1);
				})
			}
		}
	};
	
	$.fn.buttonRender = function() {
			if(!$(this).attr("class")){
				$(this).addClass("button");
			}
			var btnTextNum=_getStrLength($(this).text());
			var textNum=0;
			var textWidth=50;
			textNum=_getStrLength($(this).filter(":has(span)").find("span").text());
			if(textNum!=0){
				textWidth=20+7*textNum+10;
			}
			if(broswerFlag=="Firefox"||broswerFlag=="Opera"||broswerFlag=="Safari"){
				$(this).filter(":has(span)").css({
					"paddingLeft":"5px",
					"width":textWidth+8+"px"
				});
			}
			else{
				$(this).filter(":has(span)").css({
					"paddingLeft":"5px",
					"width":textWidth+"px"
				});
			}
			if(btnTextNum<5){
				$(this).width(66);
			}
			
			$(this).filter(":has(span)").find("span").css({
				"cursor":"default"
			});
			
			$(this).hover(
				function() {
						$(this).addClass("button_hover");
					},
				function(){
						$(this).removeClass("button_hover");
					}
			);
	};
	
	$.fn.dateRender = function() {
		var focusFlag=null;
		var dateFormat="yyyy-MM-dd";
		if($(this).attr("dateFmt")!=null){
			dateFormat=$(this).attr("dateFmt");
		}
		var doubleCal=false;
		if($(this).attr("doubleCal")==true||$(this).attr("doubleCal")=="true"){
			doubleCal=true;
		}
		$(this).hover(
			function() {
				if(focusFlag!=$(this)[0]){
					$(this).addClass("date_hover");
				}
				},
			function(){
				if(focusFlag!=$(this)[0]){
					$(this).removeClass("date_hover");
				}
				}
		);
		$(this).focus(function(){
			try{
				WdatePicker({
					skin:themeColor,isShowClear:true,dateFmt:dateFormat,doubleCalendar:doubleCal,
					onpicked:function(dp){
						$(this).blur();
					}
				});
			}
			catch(e){
				alert("日期选择框出错，注意脚本的引入：WdatePicker.js")
			}
			
			
			focusFlag=$(this)[0];
			$(this).removeClass("date_hover");
			$(this).addClass("date_click");
		});
		
		
		$(this).blur(function(){
			focusFlag=null;
			$(this).removeClass("date_click");
		});
	};
	

	$.fn.popupMenuRender = function() {
		$(this).hover(function(){
			$(this).find(".popupMenu_con").show();
		},function(){
			$(this).find(".popupMenu_con").hide();
		});
	};
	
	$.fn.singleNavRender = function() {
		var $instance=$(this);
		$instance.find(">div span").each(function(){
			$(this).click(function(){
				$instance.find(">div").removeClass("current");
				$(this).parent("div").addClass("current");
			});
			$(this).hover(function(){
				$(this).animate({
					paddingLeft:'40px'
				},'fast');
			},function(){
				$(this).animate({
					paddingLeft:'20px'
				});
			});
		});
	};
	
	$.fn.singleNavMinRender = function() {
		var $instance=$(this);
		$instance.find(">div span").each(function(){
			$(this).click(function(){
				$instance.find(">div").removeClass("current");
				$(this).parent("div").addClass("current");
			});
			$(this).hover(function(){
				$(this).animate({
					paddingLeft:'30px'
				},'fast');
			},function(){
				$(this).animate({
					paddingLeft:'10px'
				});
			});
		});
	};
	
	$.fn.tableRender = function() {
		return this.each(function() {
			if($(this).attr("mode")=="list"){
				//宽度自适应
				if($(this).attr("thTrueWidth")=="true"||$(this).attr("thTrueWidth")==true){
					$("#scrollContent").css({
						overflowX: "auto"
					});
					var tableWidth=0;
					$(this).find("tr").eq(0).find("th").each(function(){
						var thWidth=Number($(this).attr("trueWidth"));
						tableWidth=tableWidth+thWidth;
						$(this).width(thWidth);
					})
					$(this).width(tableWidth);
				}
				else if($(this).attr("tdTrueWidth")=="true"||$(this).attr("tdTrueWidth")==true){
					$("#scrollContent").css({
						overflowX: "auto"
					});
					var tableWidth2=0;
					$(this).find("tr").eq(0).find("td").each(function(){
						var tdWidth=Number($(this).attr("trueWidth"));
						tableWidth2=tableWidth2+tdWidth;
						$(this).width(tdWidth);
					})
					$(this).width(tableWidth2);
				}
				
				if($(this).attr("fixedCellHeight")=="true"||$(this).attr("fixedCellHeight")==true){
					
				}
				else{
					$(this).addClass("tableStyleWordWrap");
				}
				if($(this).find('tr').eq(1).find("td").eq(0).find('input[type="checkbox"]').length==1){
					if($(this).attr("useCheckBox")!="false"){
						$(this).attr("useCheckBox","true");
					}
					if($(this).attr("useMultColor")!="false"){
						$(this).attr("useMultColor","true");
					}
				}
				if($(this).find('tr').eq(1).find("td").eq(0).find('input[type="radio"]').length==1){
					if($(this).attr("useRadio")!="false"){
						$(this).attr("useRadio","true");
					}
				}
				if ($(this).attr("useColor") != "false") {//默认隔行换色
					$(this).find('tr:even').addClass('odd');
				}
				if ($(this).attr("useHover") != "false") {//默认鼠标移入变色
					$(this).find('tr').hover(function(){
						$(this).addClass('highlight');
					}, function(){
						$(this).removeClass('highlight');
					});
				}
				if ($(this).attr("sortMode") == "true") {//排序模式
					$(this).find('th').filter(":has(span)").hover(function(){
						$(this).removeClass("th");
						$(this).addClass("th_over");
					}, function(){
						$(this).removeClass("th_over");
						$(this).addClass("th");
					});
					$(this).find('th span').addClass("sort_off");
					$(this).find('th').click(function(){
					
					});
				}
				
				if ($(this).attr("useClick") != "false") {//useClick默认为true
					$(this).attr("useClick", "true");
				}
				if ($(this).attr("useClick") == "true" && $(this).attr("useMultColor") == "true") {//如果useClick与useMultColor都为true则useClick为false
					$(this).attr("useClick", "false");
				}
				if ($(this).attr("useRadio") != "true") {//useRadio默认为false
					$(this).attr("useRadio", "false");
				}
				if ($(this).attr("useCheckBox") != "true") {//useCheckBox默认为false
					$(this).attr("useCheckBox", "false");
				}
				
				if ($(this).attr("useClick") != "false") {//useClick为false时useRadio即使为true也不生效
					if ($(this).attr("useRadio") == "false") {//如果useRadio不做设置（为false）则采用单行点击变色模式
						$(this).find("tr").click(function(){
							$(this).siblings().removeClass('selected');
							$(this).addClass('selected');
						});
					}
					else {//如果useRadio为true则采取radio模式
						$(this).find('input[type="radio"]:checked').parents('tr').addClass('selected');
						$(this).find("tr").click(function(){
							$(this).siblings().removeClass('selected');
							$(this).addClass('selected');
							$(this).find('input[type="radio"]').attr('checked', 'checked');
						});
					}
				}
				if ($(this).attr("useMultColor") == "true") {
					if ($(this).attr("useCheckBox") == "false") {//如果checkBox为false采用普通多选模式
						$(this).find("tr").click(function(){
							$(this).toggleClass('selected');
						});
					}
					else {//如果checkBox为true采用checkBox多项模式
						$(this).find('input[type="checkbox"]:checked').parents('tr').addClass('selected');
						if($(this).find("th").length>0){
							var $checkIcon=$('<img src='+prePath+'libs/icons/checkAllOff.gif title="点击全选" class="hand"></span>');
							$(this).find("th").eq(0).addClass("ali02").html("").append($checkIcon);
							
							if ($(this).attr("headFixMode") == "true"){
								$checkIcon.toggle(function(){
									$("table:[class=tableStyle]").find("tr").each(function(){
										$(this).addClass('selected');
										$(this).find('input[type="checkbox"]').attr('checked', 'checked');
									});
									$(this).attr("src",prePath+'libs/icons/checkAllOn.gif');
									$(this).attr("title","取消全选");
								},function(){
									$("table:[class=tableStyle]").find("tr").each(function(){
										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
											$(this).find('input[type="checkbox"]').removeAttr('checked');
										}
									});
									$(this).attr("src",prePath+'libs/icons/checkAllOff.gif');
									$(this).attr("title","点击全选");
								});
							}else{
								$checkIcon.toggle(function(){
									$(this).parents('table').find("tr").each(function(){
										$(this).addClass('selected');
										$(this).find('input[type="checkbox"]').attr('checked', 'checked');
									});
									$(this).attr("src",prePath+'libs/icons/checkAllOn.gif');
									$(this).attr("title","取消全选");
								},function(){
									$(this).parents('table').find("tr").each(function(){
										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
											$(this).find('input[type="checkbox"]').removeAttr('checked');
										}
									});
									$(this).attr("src",prePath+'libs/icons/checkAllOff.gif');
									$(this).attr("title","点击全选");
								});
							}
						}
						if($(this).attr("selectRowButtonOnly")==false||$(this).attr("selectRowButtonOnly")=="false"){
							$(this).find("tr:has(td)").each(function(){
								$(this).find('td').eq(0).addClass("ali02");
								$(this).unbind("click");
								$(this).bind("click",function(){
									if ($(this).hasClass('selected')) {
										$(this).removeClass('selected');
										$(this).find('td').eq(0).find('input[type="checkbox"]').attr("checked",false);
									}
									else {
										$(this).addClass('selected');
										$(this).find('td').eq(0).find('input[type="checkbox"]').attr("checked",true);
									}
								})
							})
						}
						else{
							$(this).find("tr:has(td)").find('input[type="checkbox"]').each(function(){
								$(this).parents('td').addClass("ali02");
								$(this).unbind("click");
								$(this).bind("click",function(){
									if ($(this).parents("tr").hasClass('selected')) {
										$(this).parents("tr").removeClass('selected');
									}
									else {
										$(this).parents("tr").addClass('selected');
									}
								})
							})
						}
					}
				}
			}
				
				
				if ($(this).attr("formMode") == "line") {//表单布局模式1
					$(this).attr("useColor", "false");
					$(this).attr("useHover", "false");
					$(this).attr("useClick", "false");
					$(this).find("th").css({
						"fontWeight": "bold",
						"text-align": "center"
					});
					$(this).find("tr").find("td:even").css("text-align", "right");
					
					if($(this).attr("footer")!=null){
						if($(this).attr("footer")=="left"){
							$(this).find("tr:last").find("td").css("text-align", "left");
						}
						else if($(this).attr("footer")=="right"){
							$(this).find("tr:last").find("td").css("text-align", "right");
						}
						else if($(this).attr("footer")=="center"){
							$(this).find("tr:last").find("td").css("text-align", "center");
						}
						else if($(this).attr("footer")=="normal"){
							$(this).find("tr:last").find("td:even").css("text-align", "right");
						}
					}
					else{
						var colspan=$(this).find("tr:last").find("td").eq(0).attr("colspan");
						if(colspan){
							if(colspan.toString()!="1"){
								$(this).find("tr:last").find("td").css("text-align", "center");
							}
						}
					}
					$(this).find("td").css({
						"paddingTop": "3px",
						"paddingBottom": "3px"
					});
				}
				else if ($(this).attr("formMode") == "transparent"){//表单布局模式2
					$(this).attr("useColor", "false");
					$(this).attr("useHover", "false");
					$(this).attr("useClick", "false");
					$(this).find("th").css({
						"fontWeight": "bold",
						"text-align": "center"
					});
					$(this).css({
						"border":"none",
						"backgroundColor":"transparent"
					});
					$(this).find("tr").css({
						"border":"none",
						"backgroundColor":"transparent"
					});
					$(this).find("tr").find("td:even").css("text-align", "right");
					if($(this).attr("footer")!=null){
						if($(this).attr("footer")=="left"){
							$(this).find("tr:last").find("td").css("text-align", "left");
						}
						else if($(this).attr("footer")=="right"){
							$(this).find("tr:last").find("td").css("text-align", "right");
						}
						else if($(this).attr("footer")=="center"){
							$(this).find("tr:last").find("td").css("text-align", "center");
						}
						else if($(this).attr("footer")=="normal"){
							$(this).find("tr:last").find("td:even").css("text-align", "right");
						}
					}
					else{
						var colspan2=$(this).find("tr:last").find("td").eq(0).attr("colspan");
						if(colspan2){
							if(colspan2.toString()!="1"){
								$(this).find("tr:last").find("td").css("text-align", "center");
							}
						}
					}
					$(this).find("td").css({
						"paddingTop": "3px",
						"paddingBottom": "3px",
						"border":"none"
					});
				}
				else if ($(this).attr("formMode") == "view") {//表单布局模式3
					$(this).attr("useColor", "false");
					$(this).attr("useHover", "false");
					$(this).attr("useClick", "false");
					$(this).find("th").css({
						"fontWeight": "bold",
						"text-align": "center"
					});
					$(this).find("tr").find("td:even").addClass("viewModeEven");
					
					if($(this).attr("footer")!=null){
						if($(this).attr("footer")=="left"){
							$(this).find("tr:last").find("td").css({
								"textAlign":"left",
								"backgroundColor":"#ffffff"
							});
						}
						else if($(this).attr("footer")=="right"){
							$(this).find("tr:last").find("td").css({
								"textAlign":"right",
								"backgroundColor":"#ffffff"
							});
						}
						else if($(this).attr("footer")=="center"){
							$(this).find("tr:last").find("td").css({
								"textAlign":"center",
								"backgroundColor":"#ffffff"
							});
						}
						else if($(this).attr("footer")=="normal"){
							$(this).find("tr:last").find("td:even").css({
								"textAlign":"right",
								"backgroundColor":"#ffffff"
							});
						}
					}
					else{
						var colspan=$(this).find("tr:last").find("td").eq(0).attr("colspan");
						if(colspan){
							if(colspan.toString()!="1"){
								$(this).find("tr:last").find("td").css({
									"textAlign":"center",
									"backgroundColor":"#ffffff"
								});
							}
						}
					}
					$(this).find("td").css({
						"paddingTop": "6px",
						"paddingBottom": "6px"
					});
				}
				$(this).find('th').addClass("th"); 
		});
	};
	
	
})(jQuery);


function getPosition(value,array){//获得数组值的索引
		var idx=-1;
		for(var i=0;i<array.length;i++){
			if(value==array[i]){
				idx=i;
				break;
			}
		}
		return idx;
	}


/*单选下拉框start*/
jQuery.fn.extend({
	selectRender: function() {
		return this.each(function() {
			if($(this).prev("div").hasClass("mainCon")){
				$(this).prev("div").remove();
			}
			new jQuery.SelectBox(this);
		});
	},
	selectAddItem: function(el) {
		this.each(function() {
			// var dataStr=$(this).attr("data");
		    //转为json
			var dataObj=$(this).data("data");
			//获取数据前缀
			var dataRoot="list";
			if($(this).attr("dataRoot")){
				dataRoot=$(this).attr("dataRoot");
			}
			//添加数据项
			dataObj[dataRoot].push(el);
			//重新设置data属性
			//$(this).attr("data",JSON.stringify(dataObj));
			$(this).data("data",dataObj);
			//选中该项
			//$(this).attr("selectedValue",100);
			//刷新下拉框
		   $(this).prev(".mainCon").remove();
			new jQuery.SelectBox(this);
		});
	},
	selectRemoveItem: function(el) {
		this.each(function() {
			
			// var dataStr=$(this).attr("data");
		    //转为json
			var dataObj=$(this).data("data");
			//获取要删除的索引
			var delIdx=-1;
			//获取数据前缀
			var dataRoot="list";
			if($(this).attr("dataRoot")){
				dataRoot=$(this).attr("dataRoot");
			}
			$.each(dataObj[dataRoot], function(idx, item){
				if(item.value.toString()==el){
					delIdx=idx;
				}
			})
			//删除项
			if(delIdx!=-1){
				dataObj[dataRoot].splice(delIdx,1);
			}
			//重新设置data属性
			//$(this).attr("data",JSON.stringify(dataObj));
			$(this).data("data",dataObj);
			//取消选中项
			//$(this).attr("selectedValue","");
			//刷新下拉框
		    $(this).prev(".mainCon").remove();
			new jQuery.SelectBox(this);
		});
	}
});
if (!window.console) {
	var console = {
		log: function(msg) { 
	 }
	}
}
var elm_id = 1;
jQuery.SelectBox = function(selectobj) {
	var opt =  {};
	opt.inputClass = opt.inputClass || "selectbox";
	opt.containerClass = opt.containerClass || "selectbox-wrapper";
	opt.hoverClass = opt.hoverClass || "current";
	opt.currentClass = opt.selectedClass || "selected";
	opt.debug = opt.debug || false;
	elm_id++;
	//得到当前点击的input的id
	var curInputId="0_input";
	
	//得到当前点击的button的id
	var curButtonId="0_button";
	var active = 0;
	var inFocus = false;
	var hasfocus = 0;
	var $select = $(selectobj);
	var $container = setupContainer(opt);
	var $mainCon=setupMainCon();
	var $input = setupInput(opt);
	var autoWidth=false;
	var edit=false;
	var colNum=1;
	var colWidth;
	var selTrueWidth;
	var windowsFlag=0;
	var containerClick=0; 
	var selInputHeight=24;
	var selButtonWidth=24;
	if(!splitMode){
		var $parentThemeDom=$(window.top.document.getElementById("theme"));
		if($parentThemeDom.attr("selInputHeight")!=null){
			selInputHeight=Number($parentThemeDom.attr("selInputHeight"));
		}
		if($parentThemeDom.attr("selButtonWidth")!=null){
			selButtonWidth=Number($parentThemeDom.attr("selButtonWidth"));
		}
	}
	
	if(window.navigator.userAgent.indexOf("Windows")>-1){
			windowsFlag=1;
	}
	
	selTrueWidth=$select.width();
	if(selTrueWidth=="0"){
		selTrueWidth=116;
	}
	
	var $selBtn;
	
	$selBtn= $("<input type='button' value=' ' class='selBtn'/>");
	
	
	
	
	$selBtn.attr("id", elm_id+"_button");
	
	var $loader=$("<div class='loader'>数据加载中...</div>");
	
	
	if($select.attr("colNum")!=null){
		colNum=parseInt($select.attr("colNum"));
	}
	if($select.attr("colWidth")!=null){
		colWidth=Number($select.attr("colWidth"));
	}
	else{
		colWidth=100;
	}
	
	//定义文本框默认长度
	var inputWidth=99;
	
	//如果设置了selWidth，则以selWidth长度为准
	if ($select.attr("selWidth")!=null) {
		inputWidth=Number($select.attr("selWidth"))-22;
	}
	$input.width(inputWidth);
	
	
	
	
	
	
	$select.hide().before($mainCon);
	
	var $table=$('<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td><td class="ali01" style="border-style:none;;padding:0;margin:0;"></td></tr></table>')
	$table.find("td").eq(0).append($input);
	$table.find("td").eq(1).append($selBtn);

	$mainCon.append($table);
	$mainCon.append($container);
	$mainCon.append($loader);
	$loader.hide();
	if($select.attr("disabled")=="disabled"||$select.attr("disabled")=="true"||$select.attr("disabled")==true){
		$selBtn.attr("disabled",true);
		$selBtn.addClass("selBtn_disabled");
		$input.addClass("selectbox_disabled");
	}
	
	
	
	init();
	
	if($select.attr("editable")!=null){
		if($select.attr("editable")=="true"){
			edit=true;
		}
		else{
			edit=false;
		}
	}
	if (!edit) {
		$input.css({
			"cursor":"pointer"
		});
		$input.click(function(event){
			curInputId=$(event.target).attr("id");
			
			setHeight()
			
			if($container.attr("hasfocus")==0){
				showMe()
			}
			else{
				hideMe()
			}
		}).keydown(function(event){
			switch (event.keyCode) {
				case 38: // up
					event.preventDefault();
					moveSelect(-1);
					break;
				case 40: // down
					event.preventDefault();
					moveSelect(1);
					break;
				//case 9:  // tab 
				case 13: // return
					event.preventDefault(); // seems not working in mac !
					$('li.' + opt.hoverClass).trigger('click');
					break;
				case 27: //escape
					hideMe();
					break;
			}
		})
	}
	else{
		$input.css({
			"cursor":"text"
		});
		$input.change(function(){
			$select.attr("editValue",$(this).val());
		});
	}
	
	$selBtn
	.click(function(event){
			curButtonId=$(event.target).attr("id");
			
			setHeight()	
			
			if($container.attr("hasfocus")==0){
				showMe()
			}
			else{
				hideMe()
			}
	}).keydown(function(event) {	   
		switch(event.keyCode) {
			case 38: // up
				event.preventDefault();
				moveSelect(-1);
				break;
			case 40: // down
				event.preventDefault();
				moveSelect(1);
				break;
			//case 9:  // tab 
			case 13: // return
				event.preventDefault(); 
				$('li.'+opt.hoverClass).trigger('click');
				break;
			case 27: //escape
			  hideMe();
			  break;
		}
	})
	
	function setHeight(){
		var oldHeight;
			var $lis=$container.find("li").length;
			if (colNum == 1) {
				oldHeight=$lis*26;
			}
			else{
				if($lis%colNum==0){
					oldHeight=$lis*26/colNum;
				}
				else{
					oldHeight=($lis-$lis%colNum)*26/colNum+26;
				}
			}
			$container.height(oldHeight);//每次展开时还原初始高度
			var usefulHeight=200;
			usefulHeight=window.document.documentElement.clientHeight-($mainCon.offset().top-$(window).scrollTop()) - 30;
			
			var trueWidth;
			if (!$select.attr("boxWidth")) {
				trueWidth=$container.width();
			}
			$container.css({
				"overflowY":"auto",
				"overflowX":"hidden"
			});
			
			//容器自适应内容宽度
			
			if(colNum!=1){//多列
				$container.width((colWidth+6)*colNum);
			}
			else{//单列
				if (!$select.attr("boxWidth")) {
					$container.width(trueWidth);
				}
				else{
					$container.width(Number($select.attr("boxWidth"))+1);
				}
			}
			
			
			var boxHeight=0;
			if($select.attr("boxHeight")){
				boxHeight=Number($select.attr("boxHeight"));
			}
			//设置了boxHeight
			if(boxHeight!=0){
				$container.height(boxHeight);
				//强制向上展开
				if ($select.attr("openDirection")  == "top") {
					$container.css({
						top: -boxHeight
					});
				}
				//强制向下展开
				else if ($select.attr("openDirection")  == "bottom") {
					$container.css({
						top:selInputHeight
					});
				}
				//智能判断方向
				else {
					//获取内容页中slect所在位置距离最底部的高度
					if (usefulHeight < boxHeight) {//如果底部容纳不下
						if ($mainCon.offset().top > boxHeight) {//如果上部能容纳下,向上展开
							$container.css({
								top: -boxHeight
							});
						}
						else 
							if (usefulHeight < 100 && $mainCon.offset().top > usefulHeight && $mainCon.offset().top > 100) {//如果上部也容纳不下，并且底部不足100,向上展开并强制高度，出滚动条
								$container.css({
									top: -boxHeight
								});
							}
							else {//上面容纳不下，下面大于100，则向下展开，并强制高度，出滚动条
								$container.css({
									top: selInputHeight
								});
							}
					}
					else {
						$container.css({
							top: selInputHeight
						});
					}
				}
			}
			//没有设置了boxHeight
			else{
				//强制向上展开
				if ($select.attr("openDirection")  == "top") {
					if($mainCon.offset().top>oldHeight){//如果上部能容纳下
						$container.css({
							top: -oldHeight
						});
					}
					else{//如果上部容纳不下，向上展开并强制高度，出滚动条
						$container.height($mainCon.offset().top);
						$container.css({
							top: -oldHeight
						});
					}
				}
				//强制向下展开
				else if ($select.attr("openDirection")  == "bottom") {
					if (usefulHeight < oldHeight) {//如果底部容纳不下，向下展开并强制高度，出滚动条
						$container.css({
							top:selInputHeight
						});
						$container.height(usefulHeight);
					}
					else{//底部能容纳下
						$container.css({
							top: selInputHeight
						});
					}
				}
				//智能判断方向
				else {
					//获取内容页中slect所在位置距离最底部的高度
					if (usefulHeight < oldHeight) {//如果底部容纳不下
						if ($mainCon.offset().top > oldHeight) {//如果上部能容纳下,向上展开
							$container.css({
								top: -oldHeight
							});
						}
						else 
							if (usefulHeight < 100 && $mainCon.offset().top > usefulHeight && $mainCon.offset().top > 100) {//如果上部也容纳不下，并且底部不足100,向上展开并强制高度，出滚动条
								$container.height($mainCon.offset().top);
								$container.css({
									top: -oldHeight
								});
							}
							else {//上面容纳不下，下面大于100，则向下展开，并强制高度，出滚动条
								$container.css({
									top: selInputHeight
								});
								$container.height(usefulHeight);
							}
					}
					else {
						$container.css({
							top: selInputHeight
						});
					}
				}
			}
			
			
			
			//设置最小宽度
			if (!$select.attr("boxWidth")) {
				if($container.width()<inputWidth+selButtonWidth){
					$container.width(inputWidth+selButtonWidth)
				}
			}
	}
	
	function hideMe() { 
		$container.attr("hasfocus",0);
		$container.hide();
		$("body").unbind("mousedown", onBodyDown);
		
	}
	function showMe() {
		//标识内容层是否展开
		$container.attr("hasfocus",1);
		depth++;
		$mainCon.css({
			"zIndex": depth
		});
		$container.show();
		$("body").bind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		
		if($(event.target).attr("id")==curInputId||$(event.target).attr("id")==curButtonId||$(event.target).parent().attr("class")=="selectbox-wrapper"||$(event.target).attr("class")=="selectbox-wrapper"||$(event.target).parents(".selectbox-wrapper").length>0){
		}
		else{hideMe();}
	}
	function init() {
		$container.append(getSelectOptions($input.attr('id'))).hide();
		var width = $input.css('width');
    }
	function setupMainCon() {
		var $con=$("<div></div>");
		$con.addClass("mainCon");
		return $con;
	}
	function setupContainer(options) {
		var $container=$("<div></div>");
		$container.attr('id', elm_id+'_container');
		$container.addClass(options.containerClass);
		$container.css({
		});
		$container.attr("hasfocus",0);
		return $container;
	}
	function setupInput(options) {
		var input = document.createElement("input");
		var $input = $(input);
		$input.attr("id", elm_id+"_input");
		$input.attr("type", "text");
		$input.addClass(options.inputClass);
		if(broswerFlag=="IE8"){
			$input.addClass("selectboxFont");
		}
		$input.attr("autocomplete", "off");
		var seledit=false;
		if($select.attr("editable")!=null){
			if($select.attr("editable")=="true"){
				seledit=true;
			}
			else{
				seledit=false;
			}
		}
		if(!seledit){
			if(broswerFlag=="Firefox"){
				$input.attr("contenteditable", false);
			}
			else{
				$input.attr("readonly", "readonly");
			}
		}
		else{
			$input.attr("readonly", false);
		}
		$input.attr("tabIndex", $select.attr("tabindex")); 
		
		if($select.attr("disabled")=="disabled"||$select.attr("disabled")=="true"||$select.attr("disabled")==true){
			$input.attr("disabled",true);
			$input.addClass("inputDisabled");
		}
		return $input;	
	}
	function moveSelect(step) {
		var lis = $("li", $container);
		if (!lis || lis.length == 0) return false;
		active += step;
		if (active < 0) {
			active = lis.size();
		} else if (active > lis.size()) {
			active = 0;
		}
    	scroll(lis, active);
		lis.removeClass(opt.hoverClass);
		$(lis[active]).addClass(opt.hoverClass);
	}
	function scroll(list, active) {
      var el = $(list[active]).get(0);
      var list = $container.get(0);
      if (el.offsetTop + el.offsetHeight > list.scrollTop + list.clientHeight) {
        list.scrollTop = el.offsetTop + el.offsetHeight - list.clientHeight;      
      } else if(el.offsetTop < list.scrollTop) {
        list.scrollTop = el.offsetTop;
      }
	}
	function setCurrent() {	
		var li = $("li."+opt.currentClass, $container).get(0);
		var ar = (li.id).split('_');
		var idLength=ar[0].length+ar[1].length+2;
		var str=li.id;
		var el=str.substr(idLength,str.length);
		$select.val(el);
		$select.attr("relText",$(li).text());
		$select.attr("relValue",el);
		//$input.val($(li).html());
		var str = $(li).html().trim();
		$input.val(str);
		if(edit==true){
			$select.attr("editValue",$input.val());
		}
		$select.focus();
		return true;
	}
	function getCurrentSelected() {
		return $select.val();
	}
	function getCurrentValue() {
		return $input.val();
	}
	function getSelectOptions(parentid) {
		var select_options = new Array();
		var ul = document.createElement('ul');
		var otpArr=[];
		var idxFix=0;
		//定义联动标识位
		var rel;
		if($select.attr("childId")!=null){
			rel=true;
		}
		var isEditable;
		if($select.attr("editable")!=null){
			if($select.attr("editable")=="true"){
				isEditable=true;
			}
			else{
				isEditable=false;
			}
		}
		var ajaxMode=false;
		var urlStr=$select.attr("url");
		var dataStr=$select.attr("data");
		var dataObj3=$select.data("data");
		if(urlStr!=null||dataStr!=null||dataObj3!=null){
			ajaxMode=true;
		}
		if(ajaxMode==true){
			var dataRoot="list";
			if($select.attr("dataRoot")){
				dataRoot=$select.attr("dataRoot");
			}

			var paramsStr=$select.attr("params");
			var paramsObj;
			if(paramsStr){
				
				try {
					paramsObj=JSON.parse(paramsStr);
				}
				catch(e){
					paramsObj="";
					alert("参数格式有误！（提示：放在标签中的json数据的属性和名称必须以双引号包围）")
				}
			}
			else{
				paramsObj="";
			}
			
			
			//优先使用data
			if(dataObj3){
				createOptions(dataObj3,parentid,colNum,colWidth,isEditable,rel,ul,dataRoot);
			}
			else if(dataStr){
				var dataObj2;
				try {
					dataObj2=JSON.parse(dataStr);
				}
				catch(e){
					dataObj2="";
					alert("参数格式有误！（提示：json数据key与value必须以双引号包围）")
				}
				$select.data("data",dataObj2);
				createOptions(dataObj2,parentid,colNum,colWidth,isEditable,rel,ul,dataRoot);
			}
			else if(urlStr){
				$.ajax({ 
				url: $select.attr("url"), 
				dataType:"json",
				data:paramsObj,
				error:function(){
					alert("单选下拉框数据源出错，请检查url路径")
				},
				success: function(data){
					$select.data("data",data);
					createOptions(data,parentid,colNum,colWidth,isEditable,rel,ul,dataRoot);
					
			    }
				});
			}
		}else{
			//本地模式
			$select.find('option').each(function() {
				otpArr.push($(this)[0]);
				var li = document.createElement('li');
				li.setAttribute('id', parentid + '_' + $(this).val());
				li.innerHTML = $(this).html();
				if ($(this).is(':selected')) {
					
					if(isEditable==true){
						$input.val($(this).html());
						$(li).addClass(opt.currentClass);
					}
					else{
						var str = $(this).html().trim();
						$input.val(str);
						//$input.val($(this).html());
						$(li).addClass(opt.currentClass);
					}
				}
				if(colNum!=1){
					$(li).addClass("li_left");
					if(colWidth!=null){
						$(li).width(colWidth);
					}
					else{
						var selWidth=Number(selTrueWidth);
						$(li).width(selWidth);
					}
				}
				ul.appendChild(li);
				
				$(li)
				.mouseover(function(event) {
					hasfocus = 1;
					if (opt.debug) console.log('over on : '+this.id);
					jQuery(event.target, $container).addClass(opt.hoverClass);
				})
				.mouseout(function(event) {
					hasfocus = -1;
					if (opt.debug) console.log('out on : '+this.id);
					jQuery(event.target, $container).removeClass(opt.hoverClass);
				})
				.click(function(event) {
				    var fl = $('li.'+opt.hoverClass, $container).get(0);
					if (opt.debug) console.log('click on :'+this.id);
					var myId = $(this).attr("id").split('_');
					$('#' + myId[0] + '_container' + ' li.'+opt.currentClass).removeClass(opt.currentClass); 
					$(this).addClass(opt.currentClass);
					setCurrent();
					$select.get(0).blur();
					hideMe();
					
					try{
						$select.trigger("change");
						
					}
					catch(e){}
					
					$input.removeClass("tipColor");
					if(rel){
						ajaxLoad($select,$select.val());
					}
				});
				if($select.attr("editValue")!=null){
					$input.val($select.attr("editValue"));
				}
			});
		}
		
		$select.find('optgroup').each(function(){
			var idx=getPosition($(this).children("option").eq(0)[0],otpArr);
			var groupValue=$(this).attr("label");
			$(ul).find("li").eq(idx+idxFix).before("<li class='group'>"+groupValue+"</li>");
			idxFix++;
		});
		return ul;
	}
	function createOptions(data,parentid,colNum,colWidth,isEditable,rel,ul,dataRoot,paramsObj){
					if(!data){
						return;
					}
					//获取无关项文字内容
					var promptText="请选择";
					if($select.attr("prompt")!=null){
						if($select.attr("prompt")==""){
							promptText="请选择";
						}
						else{
							promptText=$select.attr("prompt")
						}
					}
					
					//获取初始时选中索引
					var selectedIdx=-1;
					var selectedValue="";
					if($select.attr("selectedIdx")){
						selectedIdx=Number($select.attr("selectedIdx"));
					}
					if($select.attr("selectedValue")){
						selectedValue=$select.attr("selectedValue");
					}
					
					$select.attr("length",10);
					
					if ($select.attr("prompt") != null) {
						//创建第一个选项
						var li0 = document.createElement('li');
						li0.setAttribute('id', parentid + '_');
						li0.innerHTML = promptText;
						//如果没设置初始时选中索引
						if (selectedIdx == -1 && selectedValue == "") {
							//默认选中该项
							$(li0).addClass(opt.currentClass);
							$input.val(li0.innerHTML);
						}
						ul.appendChild(li0);
						$select[0].options.length = 0;
						$select[0].options[$select[0].options.length] = new Option(promptText, "");
						//分列
						if (colNum != 1) {
							$(li0).addClass("li_left");
							if (colWidth != null) {
								$(li0).width(colWidth);
							}
							else {
								var selWidth = Number(selTrueWidth);
								$(li0).width(selWidth);
							}
						}
						
						//事件处理
						$(li0).mouseover(function(event){
							hasfocus = 1;
							if (opt.debug) 
								console.log('over on : ' + this.id);
							jQuery(event.target, $container).addClass(opt.hoverClass);
						}).mouseout(function(event){
							hasfocus = -1;
							if (opt.debug) 
								console.log('out on : ' + this.id);
							jQuery(event.target, $container).removeClass(opt.hoverClass);
						}).click(function(event){
							var fl = $('li.' + opt.hoverClass, $container).get(0);
							if (opt.debug) 
								console.log('click on :' + this.id);
							var myId = $(this).attr("id").split('_');
							$('#' + myId[0] + '_container' + ' li.' + opt.currentClass).removeClass(opt.currentClass);
							$(this).addClass(opt.currentClass);
							setCurrent();
							$select.get(0).blur();
							hideMe();
							$select.trigger("change");
							$input.removeClass("tipColor");
							if(rel){
								ajaxLoad($select,$select.val());
							}
						});
					}	
					//没设置prompt时索引默认为0，选中第一项
					if ($select.attr("prompt") == null) {
						if (selectedIdx == -1 && selectedValue == "") {
							selectedIdx=0;
						}
					}
					
					//根据数据创建其他选项
					$.each(data[dataRoot],function(idx,item){
						var li = document.createElement('li');
						li.setAttribute('id', parentid + '_' + item.value);
						li.innerHTML = item.key;
						$(li).data("itemData",item);
						$select[0].options[$select[0].options.length] = new Option(item.key, item.value);
						
						//如果设置了初始时选中索引
						if(selectedIdx==idx){
							//选中该项
							if(isEditable==true){
								$(li).addClass(opt.currentClass);
								$input.val(li.innerHTML);
								$select.val(item.value);
								$select.attr("relText",item.key);
								$select.attr("editValue",item.key);
							}
							else{
								$(li).addClass(opt.currentClass);
								$input.val(li.innerHTML.trim());
								$select.val(item.value);
								$select.attr("relText",item.key);
								$select.attr("relValue",item.value);
							}
							$select.data("selectedNode",item);
							if(rel){
								ajaxLoad($select,item.value);
							}
						}
						else if(selectedValue!=""){
							if(selectedValue==item.value.toString()){
								//选中该项
								if(isEditable==true){
									$(li).addClass(opt.currentClass);
									$input.val(li.innerHTML);
									$select.val(item.value);
									$select.attr("relText",item.key);
									$select.attr("editValue",item.key);
								}
								else{
									$(li).addClass(opt.currentClass);
									$input.val(li.innerHTML.trim());
									$select.val(item.value);
									$select.attr("relText",item.key);
									$select.attr("relValue",item.value);
								}
								$select.data("selectedNode",item);
								if(rel){
									ajaxLoad($select,item.value);
								}
							}
						}
						
						//分列
						if(colNum!=1){
							$(li).addClass("li_left");
							if(colWidth!=null){
								$(li).width(colWidth);
							}
							else{
								var selWidth=Number(selTrueWidth);
								$(li).width(selWidth);
							}
						}
						//事件处理
						$(li)
							.mouseover(function(event) {
								hasfocus = 1;
								if (opt.debug) console.log('over on : '+this.id);
								jQuery(event.target, $container).addClass(opt.hoverClass);
							})
							.mouseout(function(event) {
								hasfocus = -1;
								if (opt.debug) console.log('out on : '+this.id);
								jQuery(event.target, $container).removeClass(opt.hoverClass);
							})
							.click(function(event) {
							    var fl = $('li.'+opt.hoverClass, $container).get(0);
								if (opt.debug) console.log('click on :'+this.id);
								var myId = $(this).attr("id").split('_');
								$('#' + myId[0] + '_container' + ' li.'+opt.currentClass).removeClass(opt.currentClass); 
								
								$(this).addClass(opt.currentClass);
								setCurrent();
								$select.data("selectedNode",$(this).data("itemData"));
								$select.get(0).blur();
								hideMe();
								
								$select.trigger("change");
								$input.removeClass("tipColor");
								if(rel){
									ajaxLoad($select,$select.val());
								}
							});	
						ul.appendChild(li);
						
						if($select.attr("editValue")!=null){
							$input.val($select.attr("editValue"));
						}
					})	
		$select.attr("finished","true");
	}
	function ajaxLoad(obj,value){
				//if (value != "") {
					var child = obj.attr("childId");
					var $childLoader=$("#" + child).prev().find("div[class=loader]");
					$childLoader.show();
					window.setTimeout(function(){loadLater(obj,value);},200);
				//}
	}
	function loadLater(obj,value){
					var dataPath;
					if (obj.attr("childDataType") == null) {
						dataPath = obj.attr("childDataPath") + value;
					}
					else 
						if (obj.attr("childActionType") == "local") {
							dataPath = obj.attr("childDataPath") + value + "." + obj.attr("childDataType");
						}
						else {
							dataPath = obj.attr("childDataPath") + value;
						}
					if (obj.attr("childDataType") == "xml") {
					$.ajax({
						url: dataPath,
						error: function(){
							try {
								top.Dialog.alert("数据加载失败，请检查childDataPath是否正确");
							}
							catch(e){
								alert("数据加载失败，请检查childDataPath是否正确");
							}
						},
						success: function(xml){
							
							var child = obj.attr("childId");
							var $childLoader=$("#" + child).prev().find("div[class=loader]");
							$childLoader.hide();
							var $childUL = $("#" + child).prev().find("ul");
							var childOptId = $("#" + child).prev().find(">div").attr("id").split("_")[0];
							var $childInput = $("#" + child).prev().find("input:text");
							var childSel=$("#" + child)[0];
							$childUL.html("");
							childSel.options.length = 0;
							$(xml).find("node").each(function(){
								var text = $(this).attr("text");
								var value = $(this).attr("value");
								var li = document.createElement('li');
								$(li).text(text);
								$(li).attr("relValue", value);
								$childUL.append($(li));
								childSel.options[childSel.options.length] = new Option(text, value);
								$(li).mouseover(function(event){
									jQuery(event.target).addClass(opt.hoverClass);
								});
								$(li).mouseout(function(event){
									jQuery(event.target).removeClass(opt.hoverClass);
								});
								$(li).mousedown(function(event){
									$('#' + childOptId + '_container' + ' li.' + opt.currentClass).removeClass(opt.currentClass);
									$(this).addClass(opt.currentClass);
									$("#" + child).attr("relText", $(this).text());
									$("#" + child).attr("relValue",$(this).attr("relValue"));
									$("#" + child).val($(this).attr("relValue"));
									$childInput.val($(this).html());
									$("#" + child).prev().find(">div").hide();
									$("#" + child).focus();
									
									if ($("#" + child).attr("onchange") != null) {
										//$("#" + child).trigger("onchange");
									}
									try{
										$("#" + child).trigger("change");
									}
									catch(e){}
									var rel;
									if ($("#" + child).attr("childId") != null) {
										rel = true;
									}
									if (rel) {
										ajaxLoad($("#" + child), $("#" + child).val());
									}
								});
							});
							if($(xml).find("node").length==0){
								var li = document.createElement('li');
								$(li).text("无内容");
								$childUL.append($(li));
							}
							
							var $firstLI = $childUL.find("li").eq(0);
							$childInput.val($firstLI.text());
							$firstLI.addClass(opt.currentClass);
							$("#" + child).val($firstLI.attr("relValue"));
							$("#" + child).attr("relValue", $firstLI.attr("relValue"));
							$("#" + child).attr("relText", $firstLI.text());
							$("#" + child).trigger("ajaxInit");
						}
					});
					}
					else{
						$.getJSON(dataPath,function(data){
							var child_j = obj.attr("childId");
							var $childLoader_j=$("#" + child_j).prev().find("div[class=loader]");
							$childLoader_j.hide();
							var $childUL_j = $("#" + child_j).prev().find("ul");
							var childOptId_j = $("#" + child_j).prev().find(">div").attr("id").split("_")[0];
							var $childInput_j = $("#" + child_j).prev().find("input:text");
							var childSel_j=$("#" + child_j)[0];
							var $childSel_j=$("#" + child_j);
							$childUL_j.html("");
							childSel_j.options.length = 0;
							//获取数据的前缀
							var dataRoot="list";
							if($("#" + child_j).attr("dataRoot")){
								dataRoot=$("#" + child_j).attr("dataRoot");
							}
							//创建第一个选项
							if($("#" + child_j).attr("prompt")){
								var li_j0 = document.createElement('li');
								var text_j0=$("#" + child_j).attr("prompt");
								$(li_j0).text(text_j0);
								$(li_j0).attr("relValue", "");
								$childUL_j.append($(li_j0));
								childSel_j.options[childSel_j.options.length] = new Option(text_j0, "");
								
								$(li_j0).mouseover(function(event){
									jQuery(event.target).addClass(opt.hoverClass);
								});
								$(li_j0).mouseout(function(event){
									jQuery(event.target).removeClass(opt.hoverClass);
								});
								$(li_j0).mousedown(function(event){
									$('#' + childOptId_j + '_container' + ' li.' + opt.currentClass).removeClass(opt.currentClass);
									$(this).addClass(opt.currentClass);
									$("#" + child_j).attr("relText", $(this).text());
									$("#" + child_j).attr("relValue", $(this).attr("relValue"));
									$("#" + child_j).val($(this).attr("relValue"));
									$childInput_j.val($(this).html());
									$("#" + child_j).prev().find(">div").hide();
									$("#" + child_j).focus();
									
									if ($("#" + child_j).attr("onchange") != null) {
										//$("#" + child_j).trigger("onchange");
									}
									try{
										$("#" + child_j).trigger("change");
									}
									catch(e){}
								});
							}
							var selectedIdx_j=-1;
							var selectedValue_j="";
							var isEditable_j;
							var rel_j;
							if($childSel_j.attr("childId")!=null){
								rel_j=true;
							}
							if($childSel_j.attr("selectedIdx")){
								selectedIdx_j=Number($childSel_j.attr("selectedIdx"));
							}
							if($childSel_j.attr("selectedValue")){
								selectedValue_j=$childSel_j.attr("selectedValue");
							}
							
							if($childSel_j.attr("editable")!=null){
								if($childSel_j.attr("editable")=="true"){
									isEditable_j=true;
								}
								else{
									isEditable_j=false;
								}
							}
							$.each(data[dataRoot],function(idx,item){
								var text_j = item.key;
								var value_j = item.value;
								var li_j = document.createElement('li');
								$(li_j).text(text_j);
								$(li_j).attr("relValue", value_j);
								$(li_j).data("itemData",item);
								$childUL_j.append($(li_j));
								childSel_j.options[childSel_j.options.length] = new Option(text_j, value_j);
								//如果设置了初始时选中索引
								if(selectedIdx_j==idx){
									//选中该项
									if(isEditable_j==true){
										$(li_j).addClass(opt.currentClass);
										$childInput_j.val(li_j.innerHTML);
										$childSel_j.val(item.value);
										$childSel_j.attr("relText",item.key);
										$childSel_j.attr("editValue",item.key);
									}
									else{
										$(li_j).addClass(opt.currentClass);
										$childInput_j.val(li_j.innerHTML.trim());
										$childSel_j.val(item.value);
										$childSel_j.attr("relText",item.key);
										$childSel_j.attr("relValue",item.value);
									}
									$childSel_j.data("selectedNode",item);
									if(rel){
										ajaxLoad($childSel_j,item.value);
									}
								}
								else if(selectedValue_j!=""){
									if(selectedValue_j==item.value.toString()){
										//选中该项
										if(isEditable_j==true){
											$(li_j).addClass(opt.currentClass);
											$childInput_j.val(li_j.innerHTML);
											$childSel_j.val(item.value);
											$childSel_j.attr("relText",item.key);
											$childSel_j.attr("editValue",item.key);
										}
										else{
											$(li_j).addClass(opt.currentClass);
											$childInput_j.val(li_j.innerHTML.trim());
											$childSel_j.val(item.value);
											$childSel_j.attr("relText",item.key);
											$childSel_j.attr("relValue",item.value);
										}
										$childSel_j.data("selectedNode",item);
										if(rel_j){
											ajaxLoad($childSel_j,item.value);
										}
									}
								}
								$(li_j).mouseover(function(event){
									jQuery(event.target).addClass(opt.hoverClass);
								});
								$(li_j).mouseout(function(event){
									jQuery(event.target).removeClass(opt.hoverClass);
								});
								$(li_j).mousedown(function(event){
									$('#' + childOptId_j + '_container' + ' li.' + opt.currentClass).removeClass(opt.currentClass);
									$(this).addClass(opt.currentClass);
									$("#" + child_j).attr("relText", $(this).text());
									$("#" + child_j).attr("relValue", $(this).attr("relValue"));
									$("#" + child_j).data("selectedNode", $(this).data("itemData"));
									$("#" + child_j).val($(this).attr("relValue"));
									$childInput_j.val($(this).html());
									$("#" + child_j).prev().find(">div").hide();
									$("#" + child_j).focus();
									
									if ($("#" + child_j).attr("onchange") != null) {
										//$("#" + child_j).trigger("onchange");
									}
									try{
										$("#" + child_j).trigger("change");
									}
									catch(e){}
									//var rel_j;
									//if ($("#" + child_j).attr("childId") != null) {
										//rel_j = true;
									//}
									if (rel_j) {
										ajaxLoad($("#" + child_j), $("#" + child_j).val());
									}
								});
							});
							if(data.length==0){
								var li_j = document.createElement('li');
								$(li_j).text("无内容");
								$childUL_j.append($(li_j));
							}
							if(selectedIdx_j==-1&&selectedValue_j==""){
								var $firstLI_j = $childUL_j.find("li").eq(0);
								$childInput_j.val($firstLI_j.text());
								$firstLI_j.addClass(opt.currentClass);
								$("#" + child_j).val($firstLI_j.attr("relValue"));
								$("#" + child_j).attr("relValue", $firstLI_j.attr("relValue"));
								$("#" + child_j).attr("relText", $firstLI_j.text());
								$("#" + child_j).data("selectedNode", $firstLI_j.data("itemData"));
							}
							$("#" + child_j).trigger("ajaxInit"); 
						}); 
					}
					
	}
};
var quiType="person";

	
/*单选下拉框*/

/*信息提示*/
var tipDirection="down";
function enableTooltips(id){
    var links,links2, i, h;
    if (!document.getElementById || !document.getElementsByTagName) 
        return;
    AddCss();
    h = document.createElement("span");
    h.id = "btc";
    h.setAttribute("id", "btc");
    h.style.position = "absolute";
	h.style.zIndex=9999;
	$("body").append($(h));
}
function _getStrLength(str){
	var i;
	var len;
	for(i=0,len=0;i<str.length;i++){
		if(str.charCodeAt(i)<128){
			len++;
		}
		else{
			len=len+2;
		}
	}
	return len;
}
function addTooltip(el){
    var tooltip, t, b, s, l;
    t = el.getAttribute("title");
	
	if(t==" "){
		el.removeAttribute("title");
		 el.onmouseover = null;
	    el.onmouseout = null;
	    el.onmousemove = null;
		return;
	}
     if (t != null && t.length != 0) {
	    el.removeAttribute("title");
	    if (_getStrLength(t) > 37||_getStrLength(t) ==37) {
	        tooltip = CreateEl("span", "tooltip");
	    }
		else  if (_getStrLength(t) > 10&&_getStrLength(t)<37) {
	        tooltip = CreateEl("span", "tooltip_mid");
	    }
	    else {
	        tooltip = CreateEl("span", "tooltip_min");
	    }
	    s = CreateEl("span", "top");
	   $(s).html(t);
	    tooltip.appendChild(s);
	    b = CreateEl("b", "bottom");
	    tooltip.appendChild(b);
	    setOpacity(tooltip);
	    el.tooltip = tooltip;
		//$(el).bind("mouseover",function(e){showTooltip(e)});
		//$(el).bind("mouseout",function(e){hideTooltip()});

	    el.onmouseover = showTooltip;
	    el.onmouseout = hideTooltip;
	    el.onmousemove = Locate2;
	 }
}
function hideTip(e){
	var d = document.getElementById("btc");
    if (d.childNodes.length > 0) 
        d.removeChild(d.firstChild);
}
function showTooltip(e){
    document.getElementById("btc").appendChild(this.tooltip);
		
    Locate(e);
}
function hideTooltip(){
    var d = document.getElementById("btc");
    if (d.childNodes.length > 0) 
        d.removeChild(d.firstChild);
}
function setOpacity(el){
    el.style.filter = "alpha(opacity:95)";
    el.style.KHTMLOpacity = "0.95";
    el.style.MozOpacity = "0.95";
    el.style.opacity = "0.95";
}
function CreateEl(t, c){
    var x = document.createElement(t);
    x.className = c;
    x.style.display = "block";
    return (x);
}
function AddCss(){}
function Locate(e){
    var posx = 0, posy = 0;
    if (e == null) 
        e = window.event;
    if (e.pageX || e.pageY) {
        posx = e.pageX;
        posy = e.pageY;
    }
    else 
        if (e.clientX || e.clientY) {
            if (document.documentElement.scrollTop) {
                posx = e.clientX + document.documentElement.scrollLeft;
                posy = e.clientY + document.documentElement.scrollTop;
            }
            else {
                posx = e.clientX + document.body.scrollLeft;
                posy = e.clientY + document.body.scrollTop;
            }
        }
    
    var clientWidth=window.document.documentElement.clientWidth;
    var clientHeight=window.document.documentElement.clientHeight;
	var tipWidth=$("#btc").width();
	var tipHeight=$("#btc").height();
	var oldClass=$("#btc >span")[0].className;
	if(clientWidth-tipWidth<posx - 20){//向右撞墙
		document.getElementById("btc").style.left = (clientWidth-tipWidth) + "px";
		if(oldClass=="tooltip"){
			$("#btc >span")[0].className="tooltip_s";
		}
		else if(oldClass=="tooltip_min"){
			$("#btc >span")[0].className="tooltip_min_s";
		}
		else if(oldClass=="tooltip_mid"){
			$("#btc >span")[0].className="tooltip_mid_s";
		}
	}
	else{
		document.getElementById("btc").style.left = (posx - 20) + "px";
	}
	
	if($(window).scrollTop()+clientHeight-tipHeight<posy){//向右撞墙
		document.getElementById("btc").style.top = (posy-tipHeight-10) + "px";
		
		if(oldClass=="tooltip"){
			$("#btc >span")[0].className="tooltip_r";
		}
		else if(oldClass=="tooltip_min"){
			$("#btc >span")[0].className="tooltip_min_r";
		}
		else if(oldClass=="tooltip_mid"){
			$("#btc >span")[0].className="tooltip_mid_r";
		}
		tipDirection="up";
	}
	else{
		document.getElementById("btc").style.top = (posy + 10) + "px";
		if(oldClass=="tooltip_r"){
			$("#btc >span")[0].className="tooltip";
		}
		else if(oldClass=="tooltip_min_r"){
			$("#btc >span")[0].className="tooltip_min";
		}
		else if(oldClass=="tooltip_mid_r"){
			$("#btc >span")[0].className="tooltip_mid";
		}
		tipDirection="down";
	}
}
function Locate2(e){
    var posx = 0, posy = 0;
    if (e == null) 
        e = window.event;
    if (e.pageX || e.pageY) {
        posx = e.pageX;
        posy = e.pageY;
    }
    else 
        if (e.clientX || e.clientY) {
            if (document.documentElement.scrollTop) {
                posx = e.clientX + document.documentElement.scrollLeft;
                posy = e.clientY + document.documentElement.scrollTop;
            }
            else {
                posx = e.clientX + document.body.scrollLeft;
                posy = e.clientY + document.body.scrollTop;
            }
        }
    
    var clientWidth=window.document.documentElement.clientWidth;
    var clientHeight=window.document.documentElement.clientHeight;
	var tipWidth=$("#btc").width();
	var tipHeight=$("#btc").height();
	if(clientWidth-tipWidth<posx - 20){
		document.getElementById("btc").style.left = (clientWidth-tipWidth) + "px";
	}
	else{
		document.getElementById("btc").style.left = (posx - 20) + "px";
	}
	
	if(tipDirection=="up"){
		document.getElementById("btc").style.top = (posy-tipHeight-10) + "px";
		
	}
	else{
		document.getElementById("btc").style.top = (posy + 10) + "px";
	}
}
/*信息提示*/






/*文本域改变高度*/
(function($) {
	var textarea, staticOffset;  // added the var declaration for 'staticOffset' thanks to issue logged by dec.
	var iLastMousePos = 0;
	var iMin = 32;
	var grip;
	$.fn.TextAreaResizer = function() {
		return this.each(function() {
		    textarea = $(this).addClass('processed'), staticOffset = null;
		    $(this).wrap('<div class="resizable-textarea"><span></span></div>')
		      .parent().append($('<div class="grippie"></div>').bind("mousedown",{el: this} , startDrag)).wrap('<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td></tr></table>');
		    var grippie = $('div.grippie', $(this).parent());
		    //grippie.style.marginRight = (grippie.offsetWidth - $(this)[0].offsetWidth) +'px';
			//grippie.width($(this)[0].offsetWidth)
			//alert($(this).width())
		});
	};
	function startDrag(e) {
		textarea = $(e.data.el);
		textarea.blur();
		iLastMousePos = mousePosition(e).y;
		staticOffset = textarea.height() - iLastMousePos;
		textarea.css('opacity', 0.25);
		$(document).mousemove(performDrag).mouseup(endDrag);
		return false;
	}
	function performDrag(e) {
		var iThisMousePos = mousePosition(e).y;
		var iMousePos = staticOffset + iThisMousePos;
		if (iLastMousePos >= (iThisMousePos)) {
			iMousePos -= 5;
		}
		iLastMousePos = iThisMousePos;
		iMousePos = Math.max(iMin, iMousePos);
		textarea.height(iMousePos + 'px');
		if (iMousePos < iMin) {
			endDrag(e);
		}
		return false;
	}
	function endDrag(e) {
		$(document).unbind('mousemove', performDrag).unbind('mouseup', endDrag);
		textarea.css('opacity', 1);
		textarea.focus();
		textarea = null;
		staticOffset = null;
		iLastMousePos = 0;
	}

	function mousePosition(e) {
		return { x: e.clientX + document.documentElement.scrollLeft, y: e.clientY + document.documentElement.scrollTop };
	};
})(jQuery);
/*文本域改变高度*/

/*水印*/
(function($) {
	$.fn.watermark = function(css, text) {
		return this.each(function() {
			var i = $(this), w;
			i.focus(function() {
				w && !(w=0) && i.removeClass(css).data('w',0).val('');
			})
			.blur(function() {
				!i.val() && (w=1) && i.addClass(css).data('w',1).val(text);
			})
			.closest('form').submit(function() {
				w && i.val('');
			});
			i.blur();
		});
	};
	$.fn.removeWatermark = function() {
		return this.each(function() {
			$(this).data('w') && $(this).val('');
		});
	};
})(jQuery);
/*水印*/

/*可调用的提示*/
if(jQuery) {
	( function($) {
	$.cursorMessageData = {}; // needed for e.g. timeoutId

	$(window).ready(function(e) {
		if ($('#cursorMessageDiv').length==0) {
			
			   $('body').append('<div id="cursorMessageDiv">&nbsp;</div>');
			  $('#cursorMessageDiv').hide();
		}

		$('body').mousemove(function(e) {
			$.cursorMessageData.mouseX = e.pageX;
			$.cursorMessageData.mouseY = e.pageY;
			if ($.cursorMessageData.options != undefined) $._showCursorMessage();
		});
	});
	$.extend({
		cursorMessage: function(message, options) {
			if( options == undefined ) options = {};
			if( options.offsetX == undefined ) options.offsetX = 5;
			if( options.offsetY == undefined ) options.offsetY = 5;
			if( options.hideTimeout == undefined ) options.hideTimeout = 3000;

			$('#cursorMessageDiv').html(message).show();
			if (jQuery.cursorMessageData.hideTimeoutId != undefined)  clearTimeout(jQuery.cursorMessageData.hideTimeoutId);
			if (options.hideTimeout>0) jQuery.cursorMessageData.hideTimeoutId = setTimeout($.hideCursorMessage, options.hideTimeout);
			jQuery.cursorMessageData.options = options;
			$._showCursorMessage();
		},
		hideCursorMessage: function() {
			$('#cursorMessageDiv').hide();
		},
		_showCursorMessage: function() {
			$('#cursorMessageDiv').css({ top: ($.cursorMessageData.mouseY + $.cursorMessageData.options.offsetY)+'px', left: ($.cursorMessageData.mouseX + $.cursorMessageData.options.offsetX) });
		}
	});
})(jQuery);
}
/*可调用的提示*/

/*监测Caps键*/
jQuery.fn.caps = function(cb){
    return this.keypress(function(e){
        var w = e.which ? e.which : (e.keyCode ? e.keyCode : -1);
        var s = e.shiftKey ? e.shiftKey : (e.modifiers ? !!(e.modifiers & 4) : false);
        var c = ((w >= 65 && w <= 90) && !s) || ((w >= 97 && w <= 122) && s);
        cb.call(this, c);
    });
};
/*监测Caps键*/

/*iframe自适应高度*/
function iframeHeight(iframeId){
	var frm=document.getElementById(iframeId);
	frm.style.height =frm.contentWindow.document.body.scrollHeight+"px";
}
/*iframe自适应高度*/



/*文本框文字清除功能*/
(function($) {
  $.fn.clearableTextField = function() {
    if ($(this).length>0) {
      $(this).bind('keyup change paste cut', onSomethingChanged);
    
      for (var i=0; i<$(this).length; i++) {
        trigger($($(this)[i]));
      }
    }
  }
  
  function onSomethingChanged() {
    trigger($(this));
  }
  
  function trigger(input) {
    if(input.val().length>0){
      add_clear_button(input);
    } else {
      remove_clear_button(input);
    }    
  }
  
  function add_clear_button(input) {
    if (!input.next().hasClass('text_clear_button')) {
      // appends div
      input.after("<div class='text_clear_button'></div>");
    
      var clear_button = input.next();
      var w = clear_button.outerHeight(), h = clear_button.outerHeight();
      
      input.css('padding-right', parseInt(input.css('padding-right')) + w + 1);
      input.width(input.width() - w - 1);
          
      var pos = input.position();
      var style = {};  
      style['left'] = pos.left + input.outerWidth(false) - (w+2);
      var offset = Math.round((input.outerHeight(true) - h)/2.0);
     // style['top'] = pos.top + offset;
      style['top'] = pos.top +$("#scrollContent").scrollTop() + offset;
      clear_button.css(style);
          
      clear_button.click(function(){
        input.val('');
        trigger(input);
      });
    }
  }
  
  function remove_clear_button(input) {
    var clear_button = input.next();
    
    if (clear_button.hasClass('text_clear_button')) {
      clear_button.remove();
      var w = clear_button.width();

      input.css('padding-right', parseInt(input.css('padding-right')) - w -1);
      input.width(input.width() + w + 1);
    }
  }
})(jQuery);
/*文本框文字清除功能*/

/*剩余字数功能*/
(function($) 
{
	$.fn.maxlength = function(options)
	{
		var settings = jQuery.extend(
		{
			events:				      [], 
			maxCharacters:		  10, 
			status:				      true, 
			statusClass:		    "maxNum", 
			statusText:			    "剩余字数",
			notificationClass:	"notification",	
			showAlert: 			    false,
			alertText:			    "输入字符超出限制.", 
			slider:				      true
		}, options );
		$.merge(settings.events, ['keyup']);
		return this.each(function() 
		{
			var item = $(this);
			var charactersLength = $(this).val().length;
			function updateStatus()
			{
				var charactersLeft = settings.maxCharacters - charactersLength;
				
				if(charactersLeft < 0) 
				{
					charactersLeft = 0;
				}
				item.next("div").html(settings.statusText+ " :"+ charactersLeft  );
			}
			function checkChars() 
			{
				var valid = true;
				if(charactersLength >= settings.maxCharacters) 
				{
					valid = false;
					item.addClass(settings.notificationClass);
					item.val(item.val().substr(0,settings.maxCharacters));
					showAlert();
				} 
				else 
				{
					if(item.hasClass(settings.notificationClass)) 
					{
						item.removeClass(settings.notificationClass);
					}
				}

				if(settings.status)
				{
					updateStatus();
				}
			}
			function showAlert() 
			{
				if(settings.showAlert)
				{
					alert(settings.alertText);
				}
			}
			function validateElement() 
			{
				var ret = false;
				
				if(item.is('textarea')) {
					ret = true;
				} else if(item.filter("input[type=text]")) {
					ret = true;
				} else if(item.filter("input[type=password]")) {
					ret = true;
				}

				return ret;
			}
			if(!validateElement()) 
			{
				return false;
			}
			$.each(settings.events, function (i, n) {
				item.bind(n, function(e) {
					charactersLength = item.val().length;
					checkChars();
				});
			});
			if(settings.status) 
			{
				item.after($("<div/>").addClass(settings.statusClass).html('-'));
				updateStatus();
			}
			if(!settings.status) 
			{
				var removeThisDiv = item.next("div."+settings.statusClass);
				
				if(removeThisDiv) {
					removeThisDiv.remove();
				}
			}
			if(settings.slider) {
				item.next().hide();
				
				item.focus(function(){
					item.next().slideDown('fast');
				});
				item.blur(function(){
					item.next().slideUp('fast');
				}); 
			}
		});
	};
})(jQuery);
/*剩余字数功能*/

/*文本域自适应高度*/
var colsDefault = 0;
var rowsDefault = 5;
function setDefaultValues(txtArea)
{
	colsDefault = txtArea.cols;
	rowsDefault = $(txtArea).attr("rows");
}
function bindEvents(txtArea)
{
	txtArea.onkeyup = function() {
		grow(txtArea);
	}
}
function grow(txtArea)
{
    var linesCount = 0;
    var lines = txtArea.value.split('\n');

    for (var i=lines.length-1; i>=0; --i)
    {
        linesCount += Math.floor((lines[i].length / colsDefault) + 1);
    }

    if (linesCount >= rowsDefault)
        txtArea.rows = linesCount + 1;
	else
        txtArea.rows = rowsDefault;
}
jQuery.fn.autoGrow = function(){
	return this.each(function(){
		setDefaultValues(this);
		bindEvents(this);
	});
};
/*文本域自适应高度*/

/*密码强度*/
(function($){
var passwordStrength = new function()
{
	this.countRegexp = function(val, rex)
	{
		var match = val.match(rex);
		return match ? match.length : 0;
	};
	
	this.getStrength = function(val, minLength)
	{	
		var len = val.length;
		
		// too short =(
		if (len < minLength)
		{
			return 0;
		}
		
		var nums = this.countRegexp(val, /\d/g),
			lowers = this.countRegexp(val, /[a-z]/g),
			uppers = this.countRegexp(val, /[A-Z]/g),
			specials = len - nums - lowers - uppers;
		
		// just one type of characters =(
		if (nums == len || lowers == len || uppers == len || specials == len)
		{
			return 1;
		}
		
		var strength = 0;
		if (nums)	{ strength+= 2; }
		if (lowers)	{ strength+= uppers? 4 : 3; }
		if (uppers)	{ strength+= lowers? 4 : 3; }
		if (specials) { strength+= 5; }
		if (len > 10) { strength+= 1; }
		
		return strength;
	};
	
	this.getStrengthLevel = function(val, minLength)
	{
		var strength = this.getStrength(val, minLength);
		switch (true)
		{
			case (strength <= 0):
				return 1;
				break;
			case (strength > 0 && strength <= 4):
				return 2;
				break;
			case (strength > 4 && strength <= 8):
				return 3;
				break;
			case (strength > 8 && strength <= 12):
				return 4;
				break;
			case (strength > 12):
				return 5;
				break;
		}
		
		return 1;
	};
};

$.fn.password_strength = function(options)
{
	var settings = $.extend({
		'container' : null,
		'minLength' : 6,
		'texts' : {
			1 : '非常弱',
			2 : '弱密码',
			3 : '强度一般',
			4 : '强密码',
			5 : '非常强'
		}
	}, options);
	
	return this.each(function()
	{
		if (settings.container)
		{
			var container = $(settings.container);
		}
		else
		{
			var container = $('<span/>').attr('class', 'password_strength');
			$(this).after(container);
		}
		
		$(this).keyup(function()
		{
			var val = $(this).val();
			if (val.length > 0)
			{
				var level = passwordStrength.getStrengthLevel(val, settings.minLength);
				var _class = 'password_strength_' + level;
				
				if (!container.hasClass(_class) && level in settings.texts)
				{
					container.text(settings.texts[level]).attr('class', 'password_strength ' + _class);
				}
			}
			else
			{
				container.text('').attr('class', 'password_strength');
			}
		});
	});
};

})(jQuery);
/*密码强度*/

/*Cookie*/
jQuery.jCookie = function(sCookieName_, oValue_, oExpires_, oOptions_) {
	if (!navigator.cookieEnabled) { return false; }
	
	var oOptions_ = oOptions_ || {};
	if (typeof(arguments[0]) !== 'string' && arguments.length === 1) {
		oOptions_ = arguments[0];
		sCookieName_ = oOptions_.name;
		oValue_ = oOptions_.value;
		oExpires_ = oOptions_.expires;
	}
	
	sCookieName_ = encodeURI(sCookieName_);
	
	if (oValue_ && (typeof(oValue_) !== 'number' && typeof(oValue_) !== 'string' && oValue_ !== null)) { return false; }
	
	var _sPath = oOptions_.path ? "; path=" + oOptions_.path : "";
	var _sDomain = oOptions_.domain ? "; domain=" + oOptions_.domain : "";
	var _sSecure = oOptions_.secure ? "; secure" : "";
	var sExpires_ = "";
	
	if (oValue_ || (oValue_ === null && arguments.length == 2)) {
	
		oExpires_ = (oExpires_ === null || (oValue_ === null && arguments.length == 2)) ? -1 : oExpires_;
		
		if (typeof(oExpires_) === 'number' && oExpires_ != 'session' && oExpires_ !== undefined) {
			var _date = new Date();
			_date.setTime(_date.getTime() + (oExpires_ * 24 * 60 * 60 * 1000));
			sExpires_ = ["; expires=", _date.toGMTString()].join("");
		}
		document.cookie = [sCookieName_, "=", encodeURI(oValue_), sExpires_, _sDomain, _sPath, _sSecure].join("");
		
		return true;
	}
	
	if (!oValue_ && typeof(arguments[0]) === 'string' && arguments.length == 1 && document.cookie && document.cookie.length) {
		var _aCookies = document.cookie.split(';');
		var _iLenght = _aCookies.length;
		while (_iLenght--) {
			var _aCurrrent = _aCookies[_iLenght].split("=");
			if (jQuery.trim(_aCurrrent[0]) === sCookieName_) { return decodeURI(_aCurrrent[1]); }
		}
	}
	
	return false;
};
/*Cookie*/

function showProgressBar(str,type){
	var titleStr="正在加载中...";
	if(str){
		titleStr=str;
	}
	var progressType="simple";
	if(type){
		if(type=="normal"){
			progressType=type;
		}
	}
	if(progressType=="simple"){
		top.progressFlag=2;
		top.showSimpleProgress(titleStr,0,true,"#ffffff");
	}
	else{
		top.progressFlag=1;
		var diag = new top.Dialog();
		diag.Width = 360;
		diag.Height = 70;
		diag.Title = titleStr;
		diag.InvokeElementId="progress"
		diag.show();
	}
}
function closeProgress(){
	try {
		if(top.progressFlag==1){
			top.Dialog.close();
			top.progressFlag=0;
		}
		else if(top.progressFlag==2){
			top.hideSimpleProgress();
			top.progressFlag=0;
		}
	}
	catch(e){}
}
function _initComplete(){
	try {
		initComplete();
	}
	catch(e){}
}
String.prototype.trim = function()
{
    // 用正则表达式将前后空格，用空字符串替代。
    return this.replace(/(^\s*)|(\s*$)/g, "");
} 



//遮罩
;(function($){
	$.fn.mask = function(label, delay, loading, bgcolor){
		$(this).each(function() {
			if(loading==null){
				loading=true;
			}
			var bgcolorValue="#cccccc";
			if(bgcolor){
				bgcolorValue=bgcolor;
			}
			if(delay !== undefined && delay > 0 && delay !=null) {
		        var element = $(this);
		        element.data("_mask_timeout", setTimeout(function() { $.maskElement(element, label ,loading, bgcolorValue)}, delay));
			} else {
				$.maskElement($(this), label, loading, bgcolorValue);
			}
		});
	};
	
	$.fn.unmask = function(){
		$(this).each(function() {
			$.unmaskElement($(this));
		});
	};
	
	$.fn.isMasked = function(){
		return this.hasClass("masked");
	};

	$.maskElement = function(element, label,loading ,bgcolor){
	
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}

		if(element.isMasked()) {
			$.unmaskElement(element);
		}
		
		if(element.css("position") == "static") {
			element.addClass("masked-relative");
		}
		
		element.addClass("masked");
		
		var maskDiv = $('<div class="loadmask"></div>');
		
		
		maskDiv.css({
			"backgroundColor":bgcolor
		})
		
		if(navigator.userAgent.toLowerCase().indexOf("msie") > -1){
			maskDiv.height(element.height() + parseInt(element.css("padding-top")) + parseInt(element.css("padding-bottom")));
			maskDiv.width(element.width() + parseInt(element.css("padding-left")) + parseInt(element.css("padding-right")));
		}
		
		if(navigator.userAgent.toLowerCase().indexOf("msie 6") > -1){
			element.find("select").addClass("masked-hidden");
		}
		
		element.append(maskDiv);
		maskDiv.show();
		if(label !== undefined&&label!=null) {
			var maskMsgDiv = $('<div class="loadmask-msg" style="display:none;"></div>');
			if(loading){
				maskMsgDiv.append('<div class="mask_lading">' + label + '</div>');
			}
			else{
				maskMsgDiv.append('<div  class="normal">' + label + '</div>');
			}
			element.append(maskMsgDiv);
			
			maskMsgDiv.css("top", Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2)+"px");
			maskMsgDiv.css("left", Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2)+"px");
			
			maskMsgDiv.show();
		}
		
	};
	
	$.unmaskElement = function(element){
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}
		
		element.find(".loadmask-msg,.loadmask").remove();
		element.removeClass("masked");
		element.removeClass("masked-relative");
		element.find("select").removeClass("masked-hidden");
	};
 
})(jQuery);


//JSON处理,用法：JSON.parse();JSON.stringify()
var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    'use strict';

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf())
                ? this.getUTCFullYear()     + '-' +
                    f(this.getUTCMonth() + 1) + '-' +
                    f(this.getUTCDate())      + 'T' +
                    f(this.getUTCHours())     + ':' +
                    f(this.getUTCMinutes())   + ':' +
                    f(this.getUTCSeconds())   + 'Z'
                : null;
        };

        String.prototype.toJSON      =
            Number.prototype.toJSON  =
            Boolean.prototype.toJSON = function (key) {
                return this.valueOf();
            };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string'
                ? c
                : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {


        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }


        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

            return String(value);

        case 'object':

            if (!value) {
                return 'null';
            }

            gap += indent;
            partial = [];

            if (Object.prototype.toString.apply(value) === '[object Array]') {

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

                v = partial.length === 0
                    ? '[]'
                    : gap
                    ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']'
                    : '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

            v = partial.length === 0
                ? '{}'
                : gap
                ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}'
                : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

            var i;
            gap = '';
            indent = '';

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

            } else if (typeof space === 'string') {
                indent = space;
            }

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

            return str('', {'': value});
        };
    }

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

            function walk(holder, key) {
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }

            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

            if (/^[\],:{}\s]*$/
                    .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
                j = eval('(' + text + ')');
                return typeof reviver === 'function'
                    ? walk({'': j}, '')
                    : j;
            }
            throw new SyntaxError('JSON.parse');
        };
    }
}());

//限制输入类型的公共方法
function validateInput(value,reg){
		var re = new RegExp(reg);
		return re.test(value);
	}

//动态创建所在位置
function createPosition(str,type){
		var $position;
		if(type=="normal"){
			$position=$('<div class="position">'+
				'<div class="center">'+
				'<div class="left">'+
				'<div class="right">'+
					'<span></span>'+
				'</div>'+
				'</div>'+	
				'</div>'+
			'</div>');
		}
		else if(type=="simple"){
			$position=$('<div class="positionSimple">'+
					'<span></span>'+
			'</div>');
		}
		$position.find("span").append(str);
		$("body").prepend($position);
}
$.fn.createBoxItem = function(data,frmid) {
	var $instance=$(this);
	var $boxinstance=$instance.parents(".box4");
	$instance.empty();
	var firstSrc;
	var boxType="double";
	if(data["type"]){
		if(data["type"]=="single"){
			boxType="single";
		}
	}
	if(boxType=="single"){
		$boxinstance.attr("noTitle","false");
		$boxinstance.attr("panelTitle",data["title"]);
		$boxinstance.box4Build();
		var $ulStr=$("<ul></ul>");
		$instance.append($ulStr);
		$.each(data["list"],function(idx,item){
			var $childdom2=$('<li><a><span class="text_slice"></span></a></li>');
			if(item.link!=""){
				var $itemA2=$childdom2.find("a");
				$itemA2.attr("href",item.link);
				$itemA2.attr("target",frmid);
			}
			$childdom2.find(".text_slice").text(item.name);
			$ulStr.append($childdom2);
		})
	}
	else{
		$boxinstance.attr("noTitle","true");
		$boxinstance.box4Build();
		$.each(data["list"],function(idx,item){
			if(idx==0){
				firstSrc=item.link;
			}
			if(item.type=="parent"){
				var $parentdom=$('<div class="subtitle"></div>');
				var $parentCon;
				if(item.link!=""){
					$parentCon=$('<a><div class="subtitle_con"></div></a>');
					$parentCon.attr("href",item.link);
					$parentCon.attr("target",frmid);
				}
				else{
					$parentCon=$('<div class="subtitle_con"></div>');
				}
				$parentdom.append($parentCon);
				$parentdom.find(".subtitle_con").text(item.name);
				$parentdom.attr("id","boxitem_"+item.id);
				$instance.append($parentdom);
				$instance.append("<ul></ul>");
			}
		})
		$.each(data["list"],function(idx,item){
			if(item.type=="child"){
				var $childdom=$('<li><a><span class="text_slice"></span></a></li>');
				if(item.link!=""){
					var $itemA=$childdom.find("a");
					$itemA.attr("href",item.link);
					$itemA.attr("target",frmid);
				}
				
				$childdom.find(".text_slice").text(item.name);
				var pid=item.pid;
				$("#boxitem_"+pid).next("ul").append($childdom);
			}
		})
		$instance.find(".subtitle a").each(function(){
			$(this).unbind("click");
			$(this).click(function(){
				$instance.find("li a").removeClass("current");
			})
		})
	}
	
	
	$instance.find("li a").each(function(i){
		$(this).unbind("click");
		$(this).click(function(){
			$instance.find("li a").removeClass("current");
			$(this).addClass("current");
			if($(this).attr("href")!=null){
				showProgressBar();
				if(boxType=="single"){
					if(data["title"]){
						top.positionContent="【当前位置："+data["title"]+">>"+$(this).text()+"】";
					}
					else{
						top.positionContent="【当前位置："+$(this).text()+"】";
					}
				}
				else{
					if(data["title"]){
						top.positionContent="【当前位置："+data["title"]+">>"+$(this).parents("ul").prev(".subtitle").eq(0).text()+">>"+$(this).text()+"】";
					}
					else{
						top.positionContent="【当前位置："+$(this).parents("ul").prev(".subtitle").eq(0).text()+">>"+$(this).text()+"】";
					}
				}
				top.positionType="simple";
			}
		})
	})
	$("#"+frmid).attr("src",firstSrc);
	
}
function showCodePage(src,tile){
	var diag = new top.Dialog();
	diag.Title = tile;
	diag.Modal=false;
	diag.ID="code1";
	diag.URL = src;
	diag.ShowMaxButton=true;
	diag.ShowMinButton=true;
	diag.Width=900;
	diag.Height=540;
	diag.MaxEvent = function(){
			diag.innerFrame.contentWindow.changeCodeHeight($(top.document.getElementById("_DialogBGDiv")).height()-55);
	};	
	diag.DecreaseEvent = function(){
			diag.innerFrame.contentWindow.changeCodeHeight(530);
	};
	diag.show();
} 