<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,
                 com.monitor.bean.sensordata,
                 com.monitor.dao.Sensor_functionDAO,
                 com.monitor.factory.Sensor_functionDAOFactory,
                 org.jfree.data.category.DefaultCategoryDataset,
                 org.jfree.chart.plot.PlotOrientation,
                 org.jfree.chart.plot.CategoryPlot,
                 org.jfree.chart.axis.NumberAxis,
                 org.jfree.chart.axis.TickUnits,
                 org.jfree.chart.renderer.category.BarRenderer,
                 org.jfree.chart.servlet.ServletUtilities,
                 org.jfree.data.category.CategoryDataset,
                 org.jfree.data.xy.*,
                 org.jfree.chart.plot.XYPlot,
                 org.jfree.chart.axis.NumberAxis,
                 org.jfree.chart.renderer.xy.XYLineAndShapeRenderer,
                 org.jfree.chart.*,
                 java.awt.Color,
                 org.jfree.chart.entity.StandardEntityCollection"%>               
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<!--框架必需start-->
		<script type="text/javascript" src="../libs/js/jquery.js"></script>
		<script type="text/javascript" src="../libs/js/framework.js"></script>
		<link href="../libs/css/import_basic.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
		<link rel="stylesheet" type="text/css" id="customSkin" />
		<!--框架必需end-->

		<!-- 日期控件start -->
		<script type="text/javascript"
			src="../libs/js/form/datePicker/WdatePicker.js"></script>
		<!-- 日期控件end -->

		<!-- 自定义js start -->
		<script type="text/javascript" src="../js/recordShow.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/style.css"/>
		<!-- 自定义js end -->
		
	</head>
	<body  onload="loadShow();">
		<br/>
		
	<form action="../servlet/SearchByTimeServlet" method="get">	
    <div id="scrollContent"   style="overflow-x:hidden;">
	<table  class="tableStyle" id="recordSelect" width="1000px">
										<!-- -下面展示查询条件 start -->
										<tr >
											<td width="8%" height="25px">
												时间类型：
											</td>
											<td width="15%">
												<select style="width: 100%;" onchange="changeByTime()"
													id="dataShowByTime">
												
													<option value="1">
														年
													</option>

													<option value="2">
														月
													</option>
													<option value="3" selected>
														日
													</option>
													<option value="4">
														时
													</option>
													<option value="5">
														分
													</option>
												</select>
											</td>



											<td width="6%">
												时间:
											</td>
											
											<script type="text/javascript">
												var d = new Date();
		                                        var vYear = d.getFullYear();
		                                        var vMon = d.getMonth() + 1;
		                                        var vDay = d.getDate();
		                                        var h = d.getHours(); 
		                                        var m = d.getMinutes();
		                                        var se = d.getSeconds(); 
	                                            s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
												
												// s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+(h<10 ? "0"+ h : h)+(m<10 ? "0" + m : m)+(se<10 ? "0" +se : se)
											
											   /*年的书写 start*/
												document.write("<td  width=\"15%\"><select name=\"year\"><option value="+(parseInt(vYear)-1)+">"+(parseInt(vYear)-1)+"</option>"
												+"<option value="+vYear+" selected>"+vYear+"</option><option value="+(parseInt(vYear)+1)+">"+(parseInt(vYear)+1)+"</option></select></td>");
												/*年的书写 end*/
												
												 /*月的书写 start*/
												document.write("<td  width=\"15%\"><select name=\"month\"><option value="+(parseInt(vMon)-1)+">"+(parseInt(vMon)-1)+"</option>"
												+"<option value="+vMon+" selected>"+vMon+"</option><option value="+(parseInt(vMon)+1)+">"+(parseInt(vMon)+1)+"</option></select></td>");
												/*月的书写 end*/
												
											    /*日的书写 start*/
												document.write("<td width=\"15%\"><input type=\"text\" class=\"date\" name=\"day\" id=\"day\" style=\"width: 100%\" value="+s+" /></td>");
												/*日的书写 end*/
												
												
												/*时的书写 start*/
												document.write("<td  width=\"15%\"><select name=\"hour\"><option value="+(parseInt(h)-1)+">"+(parseInt(h)-1)+"</option>"
												+"<option value="+h+" selected>"+h+"</option><option value="+(parseInt(h)+1)+">"+(parseInt(h)+1)+"</option></select></td>")
												/*时的书写 end*/
												
												/*分的书写 start*/
												document.write("<td  width=\"10%\"><select name=\"second\"><option value="+(parseInt(m)-1)+">"+(parseInt(m)-1)+"</option>"
												+"<option value="+m+" selected>"+m+"</option><option value="+(parseInt(m)+1)+">"+(parseInt(m)+1)+"</option></select></td>")
												/*分的书写 end*/
											</script>					
										
										  <td></td>
										</tr>
																		
										<!-- -下面展示查询条件 end -->

									</table>
									
									<table class="tableStyle">
									<tr>
										
										  <td width="8%" height="25px">数据类型：</td>
											<td width="15%">
											
												<select style="width: 100%;" >
												
													<option value="1">
														最大值
													</option>

													<option value="2">
														最小值
													</option>
													<option value="3" selected>
														平均值
													</option>
													
													<option value="3" selected>
														报警值
													</option>
													
												 </select>
											</td>
											
											 <td width="6%">数据展示：</td>
											<td width="15%">
											
												<select style="width: 100%;" id="graphShow"  onchange="changeByGraph()">
												
													<option value="1" selected>
														报表
													</option>

													<option value="2">
														折线图
													</option>
													<option value="3">
														饼状图
													</option>
													
													<option value="4">
														柱图
													</option>
													
												 </select>
											</td>
											
											
											<td width="10%" align="right">
												<input type="submit" value=" 检索 " />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" value=" 重置" />
											</td>
											<td></td>
										</tr>
									
									</table>
									
<!-- 隐藏域 start -->

	<table>
	 <tr>
	  <td><input name="timeChange" id="timeChange" type="hidden" value="3"></input></td>
	  <td><input name="first" id="timeChange" type="hidden" value="2"></input></td>
	  <td><input name="sensorID" id="timeChange" type="hidden" value="<%=request.getParameter("sensorID")%>"></input></td>
	 </tr>
	</table>											
<!-- 隐藏域 end -->															
<br/><br/><hr/>
	  </div>	
	  
	  </form>
		
  <div id="scrollContent"   style="overflow-x:hidden;">
    上位机IP：192.168.1.1  节点IP：192.168.2.1 通道号：2  ID：112
	<!-- -报表的展示 start  -->
<%

ArrayList list=(ArrayList)session.getAttribute("searchSensorDataList");

%>
	<table class="tableStyle" mode="list" id="reportGraph">
		<tr>
			<th>值一</th>
			<th>值二</th>
			<th>值三</th>
			<th>时间</th>
		</tr>
<%
 if(list.size()!=0){
 for(int i=0;i<list.size();i++){
    sensordata sd=(sensordata)list.get(i);
 %>
		<tr>
			<td><%=sd.getValue1()%></td>
			<td><%=sd.getValue2()%></td>
			<td><%=sd.getValue3()%></td>
			<td><%=sd.getSamplingTime()%></td>
		</tr>
		 
<% }
}
%>		 
      </table>
	<!-- -报表的展示 end  -->
	
	<!-- -折线图的展示 start  -->
<%
             XYSeries xyseries = new XYSeries("value1");
             XYSeries xyseries1 = new XYSeries("value2");
             XYSeries xyseries2 = new XYSeries("value3");
             if(list.size()!=0){
              for(int j=0;j<list.size();j++){
              sensordata sd1=(sensordata)list.get(j); 
               xyseries.add(j,sd1.getValue1());
               xyseries1.add(j,sd1.getValue2());
               xyseries2.add(j,sd1.getValue3());
               }
               }
               XYSeriesCollection xyseriescollection = new XYSeriesCollection();
               xyseriescollection.addSeries(xyseries);
               xyseriescollection.addSeries(xyseries1);
               xyseriescollection.addSeries(xyseries2);
              
               XYDataset xydataset = xyseriescollection;
               JFreeChart jfreechart = ChartFactory.createXYLineChart(
                              "line",
                              "time",
                              "value",
                              xydataset,
                              PlotOrientation.VERTICAL,
                              true,
                              true,
                              false);
       jfreechart.setBackgroundPaint(Color.white);
       XYPlot xyplot = (XYPlot)jfreechart.getPlot(); //获得 plot：XYPlot！！
       xyplot.setBackgroundPaint(Color.lightGray); //设定图表数据显示部分背景色
       //xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D)); //设定坐标轴与图表数据显示部分距离
       xyplot.setDomainGridlinePaint(Color.white); //网格线颜色
       xyplot.setRangeGridlinePaint(Color.white);
           //获得 renderer 注意这里是XYLineAndShapeRenderer ！！
       XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
       xylineandshaperenderer.setShapesVisible(true); //数据点可见
       xylineandshaperenderer.setShapesFilled(true); //数据点被填充即不是空心点
       NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
       numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       StandardEntityCollection sec = new StandardEntityCollection(); 
       ChartRenderingInfo info = new ChartRenderingInfo(sec);
       PrintWriter w = new PrintWriter(out);//输出MAP信息 
       String filename = ServletUtilities.saveChartAsJPEG(jfreechart,750,450,info,session);
       ChartUtilities.writeImageMap(w,"map0",info,false); 
       String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;   
%>
	<table  width="100%" id="lineShow">
		<tr>
			<th>图一</th>
			<th>图二</th>
		</tr>
		<tr>
			<td><img src="<%=graphURL%>" width=750 height=450 border=0 usemap="#map0"></img></td>
			<td><img src="../images/lineGraph.jpg"></img></td>
		</tr>
		
	</table>
<!-- -折线图的展示end  -->

	<!-- -饼状图的展示 start  -->
	<table class="tableStyle" mode="list" id="pieShow">
		<tr>
			<th>图一</th>
			<th>图二</th>
			<th>图三</th>
		</tr>
		<tr>
			<td><img src="../images/pie.jpg"></img></td>
			<td><img src="../images/pie.jpg"></img></td>
			<td><img src="../images/pie.jpg"></img></td>
		</tr>
		
	</table>
<!-- -饼状图的展示end  -->



<!-- -柱图的展示 start  -->
	<table class="tableStyle" mode="list" id="histogramShow">
		<tr>
			<th>图一</th>
			<th>图二</th>
			<th>图三</th>
		</tr>
		<tr>
			<td><img src="../images/histogram.jpg"></img></td>
			<td><img src="../images/histogram.jpg"></img></td>
			<td><img src="../images/histogram.jpg"></img></td>
		</tr>
		
	</table>
<!-- -柱图的展示end  -->


</div>
	</body>
</html>