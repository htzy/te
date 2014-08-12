<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>UnitTypeOperation</title>
	<script type="text/javascript" src="../../libs/js/jquery.js"></script>
	<script type="text/javascript" src="../../libs/js/framework.js"></script>
    <script type="text/javascript" src="../../libs/js/form/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../../libs/js/nav/pageNumber.js"></script>
	<script type="text/javascript" src="../../libs/js/tree/ztree/ztree.js"></script>
	<script type="text/javascript" src="../../libs/js/popup/drag.js"></script>
	<script type="text/javascript" src="../../libs/js/popup/dialog.js"></script>
	
	<link href="../../libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
	<link href="../../libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="../../"/>
	<link rel="stylesheet" type="text/css" id="customSkin"/>
    <link href="../../libs/skins/blue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="blue" />
	<style>
	.le{
		float:left;
		width:49%;
	}
	.ri{
		float:right;
		width:49%;
	}
	p{
		font-size:20px;
	}
	</style>
</head>

<body>
	<div class="box2" panelTitle="操作"  ><!-- startState="close" -->
        <div class="le hand" onclick="addUnitType()">
       		<div class="le">
       			<div align="right">
       				<img src="../../libs/icons/png/add.png" />
       			</div>
            </div>
        </div>
        <div class="ri hand" onclick="searchUnitType()">
        	<div align="center" >
            	<div>
		            <img src="../../libs/icons/png/search.png" />
			    </div>
            </div>
        </div>
	</div>

    <div id="scrollContent" style="overflow-x:hidden;">
        <table class="tableStyle" mode="list">
            <tr>
                <th>UnitTypeID</th>
                <th>UnitTypeName</th>
                <th>修改</th>
                <th>删除</th>
            </tr>
            <tr>
                <td class="title0" id="title0"></td>
                <td class="content0" id="content0"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span>                </td>
            </tr>
            <tr>
                <td class="title1" id="title1"></td>
                <td class="content1" id="content1"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title2" id="title2"></td>
                <td class="content2" id="content2"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title3" id="title3"></td>
                <td class="content3" id="content3"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title4" id="title4"></td>
                <td class="content4" id="content4"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title5" id="title5"></td>
                <td class="content5" id="content5"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title6" id="title6"></td>
                <td class="content6" id="content6"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title7" id="title7"></td>
                <td class="content7" id="content7"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title8" id="title8"></td>
                <td class="content8" id="content8"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title9" id="title9"></td>
                <td class="content9" id="content9"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <!-- <!-- 如果仅仅显示十条记录，则下面部分的表格部分则不需要 -/->
            <tr>
                <td class="title10" id="title10"></td>
                <td class="content10" id="content10"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title11" id="title11"></td>
                <td class="content11" id="content11"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title12" id="title12"></td>
                <td class="content12" id="content12"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title13" id="title13"></td>
                <td class="content13" id="content13"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
            <tr>
                <td class="title14" id="title14"></td>
                <td class="content14" id="content14"></td>
                <td><span id="" class="img_edit hand" title="修改" onclick=""></span></td>
                <td><span id="" class="img_delete hand" title="删除" onclick=""></span></td>
            </tr>
              -->
        </table>
	</div>
        <div class="float_right padding5">
            <div class="pageNumber" total="15" showInput="true" id="page" pageSize="15"></div>
        </div>
    
        <script type="text/javascript">
            /**
            var showNumBegin = 0;
            var showNumEnd = 15;
            var str = "";
            var str1 = "";
            var str2 = "";
            var str3 = "";
            var str4 = "";
            var userName;
            var num;
            $(function deal() {
                //显示用户真实姓名和待处理信息的条数
                $.post("${path}/beginMessage_1/forUserName", {}, function(result) {
                    userName = result.userName;
                    $("#userName").show();
                    $("#userName").html(userName);
                    num = result.num;
                    $(".num").show();
                    $(".num").html(num);
                }, "json");
            });
            $(function searchM() {
                //进入界面时显示查询到的信息
                $(".num").show();
                $(".num").html("0");
                $.post("${path}/searchMessage_1/searchOneselfM", {}, function(result) {
                    $("#page").attr("total", result.args.length);
                    $("#page").render();
                    for (var i = 0; i < 15; i++) {
                        if (i < result.args.length) {
                            str = result.args[i];
                            str1 = result.args1[i];
                            str2 = result.args2[i];
                            str3 = result.args3[i];
                            str4 = result.args4[i];
                            $("#caozuo" + i + "0").show();
                            $("#caozuo" + i + "1").show();
                            $("#caozuo" + i + "2").show();
                        } else {
                            str = str1 = str2 = str3 = str4 = "";
                            $("#caozuo" + i + "0").hide();
                            $("#caozuo" + i + "1").hide();
                            $("#caozuo" + i + "2").hide();
                        }
                        $(".title" + i).show();
                        $(".title" + i).html(str);
                        $(".content" + i).show();
                        $(".content" + i).html(str1);
                        $(".person" + i).show();
                        $(".person" + i).html(str2);
                        $(".faqishijian" + i).show();
                        $(".faqishijian" + i).html(str3);
                        $(".zhichengbumenyijian" + i).show();
                        $(".zhichengbumenyijian" + i).html(str4);
                    }
                    $("#page").bind("pageChange", function(e, index) {
                        //设置分页"监听"                              
                        showNumBegin = index * 15;
                        showNumEnd = showNumBegin + 15;
                        var j = 0;
                        for (i = showNumBegin; i < showNumEnd; i++) {
                            if (i < result.args.length) {
                                str = result.args[i];
                                str1 = result.args1[i];
                                str2 = result.args2[i];
                                str3 = result.args3[i];
                                str4 = result.args4[i];
                                $("#caozuo" + j + "0").show();
                                $("#caozuo" + j + "1").show();
                                $("#caozuo" + j + "2").show();
                            } else {
                                str = str1 = str2 = str3 = str4 = "";
                                $("#caozuo" + j + "0").hide();
                                $("#caozuo" + j + "1").hide();
                                $("#caozuo" + j + "2").hide();
                            }
                            $(".title" + j).show();
                            $(".title" + j).html(str);
                            $(".content" + j).show();
                            $(".content" + j).html(str1);
                            $(".person" + j).show();
                            $(".person" + j).html(str2);
                            $(".faqishijian" + j).show();
                            $(".faqishijian" + j).html(str3);
                            $(".zhichengbumenyijian" + j).show();
                            $(".zhichengbumenyijian" + j).html(str4);
                            j = j + 1;
                        }
                    });
                }, "json");
            });

            function login() {
                window.location = "${path}/beginMessage_1/login";
            }

            function logOut() {
                window.location = "${path}/beginMessage_1/logOut";
            }

            function dealMessage() {
                window.location = "${path}/replyMessage_1/replyMessage";
            }

            function showDetailMessage(n) {
                window.location = "${path}/showDetailMessage";
            }

            function oneMessaneC(i) {
                var t = i + showNumBegin;
                $.post("${path}/searchMessage_1/showOneMessage", {
                    "whichOne": t
                }, "json");
                window.location = "${path}/searchMessage_1/showDetailMessage";
            }

            function searchMessageByCondition() {
                var sqlstrTitle = document.getElementById("searchTitle");
                var sqlstrContent = document.getElementById("searchContent");
                var sqlstrStartPerson = document.getElementById("searchStartPerson");
                var sqlstrStartTime = document.getElementById("searchStartTime");
                var sqlstrDepartment = document.getElementById("searchDepartment");
                var sql = "Theme_Title like '%" + sqlstrTitle.value + "%' and Theme_Contend like '%" + sqlstrContent.value + "%' and Theme_StartTime like '%" + sqlstrStartTime.value + "%' and User_RealName like '%" + sqlstrStartPerson.value + "%' and Theme_Department like '%" + sqlstrDepartment.value + "%'";
                $(".num").show();
                $(".num").html("0");
                $.post("${path}/searchMessage_1/searchOneselfMessageByCondition", {
                    "conditionContentForSearch": sql
                }, function(result) {
                    $("#page").attr("total", result.args.length);
                    $("#page").render();
                    var j = 0;
                    for (var i = 0; i < 15; i++) {
                        if (i < result.args.length) {
                            str = result.args[i];
                            str1 = result.args1[i];
                            str2 = result.args2[i];
                            str3 = result.args3[i];
                            str4 = result.args4[i];
                            $("#caozuo" + j + "0").show();
                            $("#caozuo" + j + "1").show();
                            $("#caozuo" + j + "2").show();
                        } else {
                            str = str1 = str2 = str3 = str4 = "";
                            $("#caozuo" + j + "0").hide();
                            $("#caozuo" + j + "1").hide();
                            $("#caozuo" + j + "2").hide();
                        }
                        $(".title" + j).show();
                        $(".title" + j).html(str);
                        $(".content" + j).show();
                        $(".content" + j).html(str1);
                        $(".person" + j).show();
                        $(".person" + j).html(str2);
                        $(".faqishijian" + j).show();
                        $(".faqishijian" + j).html(str3);
                        $(".zhichengbumenyijian" + j).show();
                        $(".zhichengbumenyijian" + j).html(str4);
                        j = j + 1;
	                    $("#userName").show();
	                    $("#userName").html(userName);
	                    $(".num").show();
	                    $(".num").html(num);
                    }
                    $("#page").bind("pageChange", function(e, index) {
                        //设置分页"监听"                              
                        showNumBegin = index * 15;
                        showNumEnd = showNumBegin + 15;
                        var j = 0;
                        for (i = showNumBegin; i < showNumEnd; i++) {
                            if (i < result.args.length) {
                                str = result.args[i];
                                str1 = result.args1[i];
                                str2 = result.args2[i];
                                str3 = result.args3[i];
                                str4 = result.args4[i];
                                $("#caozuo" + j + "0").show();
                                $("#caozuo" + j + "1").show();
                                $("#caozuo" + j + "2").show();
                            } else {
                                str = str1 = str2 = str3 = str4 = "";
                                $("#caozuo" + j + "0").hide();
                                $("#caozuo" + j + "1").hide();
                                $("#caozuo" + j + "2").hide();
                            }
                            $(".title" + j).show();
                            $(".title" + j).html(str);
                            $(".content" + j).show();
                            $(".content" + j).html(str1);
                            $(".person" + j).show();
                            $(".person" + j).html(str2);
                            $(".faqishijian" + j).show();
                            $(".faqishijian" + j).html(str3);
                            $(".zhichengbumenyijian" + j).show();
                            $(".zhichengbumenyijian" + j).html(str4);
                            j = j + 1;
                        }
                    });
                }, "json");
            }
			function customHeightSet(contentHeight){
				$("#openContent").height(contentHeight);
			}
			function addNewForm(){
				var $table=$(' <form id="form2" method="post" action="" failAlert="表单填写不正确，请按要求填写！">'+
				'<table class="tableStyle" formMode="line" style="width:500px;">'+
				'<tr><th colspan="2">动态添加验证功能</th></tr>'+
				'<tr><td>用户名：</td><td><input type="text" id="inputa1" class="validate[required,custom[noSpecialCaracters]]" /><span class="star">*</span></td></tr>'+
				'<tr><td>密码：</td><td><input type="password" id="inputa2" class="validate[required,length[6,11],custom[noSpecialCaracters]]"/><span class="star">*</span></td></tr>	'+
				'<tr><td>籍贯：</td><td><select id="inputa3" class="validate[required]" prompt="请选择"></select><span class="star">*</span></td></tr>'+
				'<tr><td colspan="2"><input type="submit" value="提交" id="inputa4"/>&nbsp;<input type="reset" value="重置" id="inputa5"/></td></tr>'+	'</table></form>')
				$("#testBtn").after($table); 
				$table.find("table").render();
				$table.find("#inputa1").render();
				$table.find("#inputa2").render();
				$table.find("#inputa3").data("data",{"list":[{"value":"1","key":"北京"},{"value":"2","key":"黑龙江"}]});
				$table.find("#inputa3").render();
				$table.find("#inputa4").render();
				$table.find("#inputa5").render();
				
				$table.validationEngine();
			}
			*/
			
			//
			function addUnitType(){
				var diag = new top.Dialog();
				diag.Title = "增加";
				diag.URL = "../../sample_html/popup/addUnitType.html";
				diag.show();
			}
			function searchUnitType(){
				var diag = new top.Dialog();
				diag.Title = "查找";
				diag.URL = "../../sample_html/popup/searchUnitType.html";
				diag.show();
			}
			
			/**
			// 下面两行用于将在显示上一页下一页的选择框时将显示内容的窗口
			// 放在一个固定大小的窗体内，方便翻页，在仅仅有十条消息时，则
			// 这一部分是不必要的
			var fixObjHeight=130;
			function customHeightSet(contentHeight){
				var windowWidth=document.documentElement.clientWidth;
				$("#scrollContent").height(contentHeight-fixObjHeight);
				$("#scrollContent").width(windowWidth-4);
			}
			 */
	</script>
</body>

</html>