package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monitor.dao.Sensor_functionDAO;
import com.monitor.factory.Sensor_functionDAOFactory;
import com.monitor.java.GetTime;

public class SearchByTimeServlet extends HttpServlet {

	public SearchByTimeServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  // 根据时间来搜索该记录
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int sensorID=Integer.parseInt(request.getParameter("sensorID"));
		String first=request.getParameter("first");
		String time = "";
		String timeEnd="";
		String href="";
		HttpSession session=request.getSession();
		ArrayList list=new ArrayList();
		if(first.equals("1")){//从左侧目录进行了第一次的加载
			GetTime getTime=new GetTime();
			String day=getTime.getTime(); //2014-02-01
			time=day+" 00:00:00";
			timeEnd=day+" 23:59:59";
		}else{
			String timeChange = request.getParameter("timeChange");// 根据时间的类型来搜索该记录
			
			if (timeChange.equals("1")) {// 按年进行搜索
				String year = request.getParameter("year");// 得到年
				time=year+"-00-00 00:00:00";                            
				timeEnd=year+"-12-31 23:59:59";        
				
			} else if (timeChange.equals("2")) {// 按月----为某年某月
				String year = request.getParameter("year");// 得到年
				String month = request.getParameter("month");// 得到月
				if(Integer.parseInt(month)<10){
					month="0"+month;
				}
				time=year+"-"+month+"-00 00:00:00";
				
				timeEnd=year+"-"+month+"-31 23:59:59";

			} else if (timeChange.equals("3")) {// 按日
				String day = request.getParameter("day");// 得到日
				time=day+" 00:00:00";
				timeEnd=day+" 23:59:59";
				
			} else if (timeChange.equals("4")) {// 按时
				String day = request.getParameter("day");// 得到日
				String hour = request.getParameter("hour");// 得到时
				if(Integer.parseInt(hour)<10){
					hour="0"+hour;
				}
				time=day+" "+hour+":00:00";
				timeEnd=day+" "+hour+":59:59";

			} else if (timeChange.equals("5")) {// 按分
				String day = request.getParameter("day");// 得到日
				String hour = request.getParameter("hour");// 得到时
				String second = request.getParameter("second");// 得到时
				if(Integer.parseInt(hour)<10){
					hour="0"+hour;
				}
				if(Integer.parseInt(second)<10){
					second="0"+second;
				}
				time=day+" "+hour+":"+second+":00";
				timeEnd=day+" "+hour+":"+second+":59";
			}
		}
		
		Sensor_functionDAO Sensor_functionDAOImplm=Sensor_functionDAOFactory.createSensor_functionDAOImplm();
		list=Sensor_functionDAOImplm.findSensorDataBySensorID(sensorID,time,timeEnd);
		session.setAttribute("searchSensorDataList",list);
		
		href="../jsp/layout.jsp?sensorID="+sensorID;
		out.print("<script language='javascript'>window.location.href='"+ href + "';</script>;");		
		
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		
		/*
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int sensorID=Integer.parseInt(request.getParameter("sensorID"));
		String first=request.getParameter("first");
		String time = "";
		String timeEnd="";
		String href="";
		HttpSession session=request.getSession();
		ArrayList list=new ArrayList();
		if(first.equals("1")){//从左侧目录进行了第一次的加载
			GetTime getTime=new GetTime();
			String day=getTime.getTime(); //2014-02-01
			time=day+" 00:00:00";
			timeEnd=day+" 23:59:59";
		}else{
			String timeChange = request.getParameter("timeChange");// 根据时间的类型来搜索该记录
			
			if (timeChange.equals("1")) {// 按年进行搜索
				String year = request.getParameter("year");// 得到年
				time=year+"-00-00 00:00:00";                            
				timeEnd=year+"-12-31 23:59:59";        
				
			} else if (timeChange.equals("2")) {// 按月----为某年某月
				String year = request.getParameter("year");// 得到年
				String month = request.getParameter("month");// 得到月
				if(Integer.parseInt(month)<10){
					month="0"+month;
				}
				time=year+"-"+month+"-00 00:00:00";
				
				timeEnd=year+"-"+month+"-31 23:59:59";

			} else if (timeChange.equals("3")) {// 按日
				String day = request.getParameter("day");// 得到日
				time=day+" 00:00:00";
				timeEnd=day+" 23:59:59";
				
			} else if (timeChange.equals("4")) {// 按时
				String day = request.getParameter("day");// 得到日
				String hour = request.getParameter("hour");// 得到时
				if(Integer.parseInt(hour)<10){
					hour="0"+hour;
				}
				time=day+" "+hour+":00:00";
				timeEnd=day+" "+hour+":59:59";

			} else if (timeChange.equals("5")) {// 按分
				String day = request.getParameter("day");// 得到日
				String hour = request.getParameter("hour");// 得到时
				String second = request.getParameter("second");// 得到时
				if(Integer.parseInt(hour)<10){
					hour="0"+hour;
				}
				if(Integer.parseInt(second)<10){
					second="0"+second;
				}
				time=day+" "+hour+":"+second+":00";
				timeEnd=day+" "+hour+":"+second+":59";
			}
		}
		
		Sensor_functionDAO Sensor_functionDAOImplm=Sensor_functionDAOFactory.createSensor_functionDAOImplm();
		list=Sensor_functionDAOImplm.findSensorDataBySensorID(sensorID,time,timeEnd);
		session.setAttribute("searchSensorDataList",list);
		
		href="../jsp/layout.jsp?sensorID="+sensorID;
		out.print("<script language='javascript'>window.location.href='"+ href + "';</script>;");		
		
		out.flush();
		out.close();
		
		*/
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
