/**
 * 
 */
function loadShow() {
	$("#recordSelect  tr:eq(0)>td:eq(3)").hide(); //年隐藏
	$("#recordSelect  tr:eq(0)>td:eq(4)").hide(); //月隐藏
	//$("#recordSelect  tr:eq(0)>td:eq(5)").hide();//日  
	$("#recordSelect  tr:eq(0)>td:eq(6)").hide();  //时
	$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
	$("#recordSelect  tr:eq(0)>td:eq(6)").hide();  //时
	$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
	
	/*初始化报表的显示*/  
	$("#lineShow").hide();//折线图的隐藏
	$("#pieShow").hide();//饼状图的隐藏
	$("#histogramShow").hide();//柱状图的隐藏
	$("#reportGraph").show();//报表的显示
	
}
function changeByTime() {//根据年月日的不同，展示不同的展示方式

	var time = document.getElementById("dataShowByTime").value;
	if (time == "1") { //用户选择了按年进行查询
		
		document.getElementById("timeChange").value=1;
		
		$("#recordSelect  tr:eq(0)>td:eq(6)").hide();  //时
		$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
		$("#recordSelect  tr:eq(0)>td:eq(4)").hide(); //月隐藏
		$("#recordSelect  tr:eq(0)>td:eq(5)").hide(); //日隐藏

		$("#recordSelect  tr:eq(0)>td:eq(3)").show(); //年  	

		//该select加载不上样式，需要手动写
	} else if (time == "2") {//用户选择了按月进行查询
		document.getElementById("timeChange").value=2;
		
		$("#recordSelect  tr:eq(0)>td:eq(6)").hide();  //时
		$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
		$("#recordSelect  tr:eq(0)>td:eq(5)").hide(); //日隐藏
		
		$("#recordSelect  tr:eq(0)>td:eq(3)").show(); //年
		$("#recordSelect  tr:eq(0)>td:eq(4)").show(); //月隐藏

	} else if (time == "3") {//用户选择了按日进行查询
		document.getElementById("timeChange").value=3;
		$("#recordSelect  tr:eq(0)>td:eq(3)").hide(); //年
		$("#recordSelect  tr:eq(0)>td:eq(4)").hide(); //月隐藏
		$("#recordSelect  tr:eq(0)>td:eq(6)").hide();  //时
		$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
		
		$("#recordSelect  tr:eq(0)>td:eq(5)").show(); //日隐藏

	}else if (time == "4") {//用户选择了按时进行查询
		document.getElementById("timeChange").value=4;
		$("#recordSelect  tr:eq(0)>td:eq(3)").hide(); //年
		$("#recordSelect  tr:eq(0)>td:eq(4)").hide(); //月隐藏
		$("#recordSelect  tr:eq(0)>td:eq(7)").hide();  //分
		
		$("#recordSelect  tr:eq(0)>td:eq(6)").show();  //时
		$("#recordSelect  tr:eq(0)>td:eq(5)").show(); //日隐藏

	}else if (time == "5") {//用户选择了按时进行查询
		document.getElementById("timeChange").value=5;
		$("#recordSelect  tr:eq(0)>td:eq(3)").hide(); //年
		$("#recordSelect  tr:eq(0)>td:eq(4)").hide(); //月隐藏
		
		$("#recordSelect  tr:eq(0)>td:eq(7)").show();  //分
		$("#recordSelect  tr:eq(0)>td:eq(6)").show();  //时
		$("#recordSelect  tr:eq(0)>td:eq(5)").show(); //日隐藏

	}
}
function changeByGraph(){
 
  var graphShow=document.getElementById("graphShow").value;
  if(graphShow=="1"){//报表
	  $("#lineShow").hide();//折线图的隐藏
	  $("#pieShow").hide();//饼状图的隐藏
	  $("#histogramShow").hide();//柱状图的隐藏
	  
	  $("#reportGraph").show();//报表的显示
	 
	  
  }else if(graphShow=="2"){ //折线图
	
	  $("#pieShow").hide();//饼状图的隐藏
	  $("#histogramShow").hide();//柱状图的隐藏
	  
	  $("#reportGraph").hide();//报表的隐藏
	  $("#lineShow").show();//折线图的显示
	  
	  
  }else if(graphShow=="3"){ //饼状图
	  
	
	  $("#histogramShow").hide();//柱状图的隐藏
	  $("#reportGraph").hide();//报表的隐藏
	  $("#lineShow").hide();//折线图的隐藏
	  $("#pieShow").show();//饼状图的显示
	  
  }else if(graphShow=="4"){ //柱图
	
	  $("#reportGraph").hide();//报表的隐藏
	  $("#lineShow").hide();//折线图的隐藏
	  $("#pieShow").hide();//饼状图的隐藏
	  $("#histogramShow").show();//柱状图的显示
  }
	
}




function changeByTime2() {//根据年月日的不同，展示不同的展示方式

	//alert($("#recordSelect  tr:eq(2)").html());  //取得第3行的值
	//alert($("#recordSelect  tr:eq(3)>td:eq(0)").html());//取得第3行，第一列的值
	//alert($("#recordSelect  tr:eq(0)").html());  //取得第3行的值

	var time = document.getElementById("dataShowByTime").value;

	var d = new Date();
	var vYear = d.getFullYear();
	var vMon = d.getMonth() + 1;
	var vDay = d.getDate();
	var h = d.getHours();
	var m = d.getMinutes();
	var se = d.getSeconds();
	s = vYear + (vMon < 10 ? "0" + vMon : vMon)
			+ (vDay < 10 ? "0" + vDay : vDay) + (h < 10 ? "0" + h : h)
			+ (m < 10 ? "0" + m : m) + (se < 10 ? "0" + se : se);
	//		document.write(s);//输出时间

	if (time == "1") { //用户选择了按年进行查询

		$("#recordSelect  tr:eq(0)>td:eq(3)").html(
				"<select  style=\"width:100%;\">" + "<option value=\"-1\">"
						+ (parseInt(vYear) - 1)
						+ "</option><option value=\"0\">" + vYear
						+ "</option> <option value=\"1\">"
						+ (parseInt(vYear) + 1) + "</option></select>");

	} else if (time == "2") {//用户选择了按月进行查询
		//添加年
		$("#recordSelect  tr:eq(0)>td:eq(3)").html(
				"<select  style=\"width:100%;\">" + "<option value=\"-1\">"
						+ (parseInt(vYear) - 1)
						+ "</option><option value=\"0\">" + vYear
						+ "</option> <option value=\"1\">"
						+ (parseInt(vYear) + 1) + "</option>" + "</select>");
		//添加月
		$("#recordSelect  tr:eq(0)>td:eq(4)").html(
				"<select  style=\"width:100%;\">" + "<option value=\"-1\">"
						+ (parseInt(vMon) - 1)
						+ "</option><option value=\"0\">" + vMon
						+ "</option> <option value=\"1\">"
						+ (parseInt(vMon) + 1) + "</option>");

	} else if (time == "3") {//用户选择了按日进行查询

		$("#recordSelect  tr:eq(0)>td:eq(3)")
				.html(
						"<input type=\"text\" class=\"date\" name=\"startTime\" id=\"startTime\" style=\"width: 100%\" />");
		$("#recordSelect  tr:eq(0)>td:eq(4)").html("");
	}
}
