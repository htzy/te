package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monitor.dao.SensorData_functionDAO;
import com.monitor.factory.SensorData_functionDAOFactory;


public class ChangeSensorDataServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeSensorDataServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      
		System.out.println("+++++++++++++++++++++++");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String sensorDataID = request.getParameter("sensorDataID");
		System.out.println("sensorDataID = "+sensorDataID);
		String samplingTime = request.getParameter("samplingTime");
		System.out.println("samplingTime = "+samplingTime);
		String value1 = request.getParameter("value1");
		System.out.println("value1 = "+value1);
		String value2 = request.getParameter("value2");
		System.out.println("value2 = "+value2);
		String value3 = request.getParameter("value3");
		System.out.println("value3 = "+value3);
		
		HttpSession session = request.getSession();
		
		String startTime = (String)session.getAttribute("startTime1");
		String endTime = (String)session.getAttribute("endTime1");
		
		PrintWriter out = response.getWriter();

		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//实例化一个单元类
		SensorData_functionDAO sensorData_functionDAOImplm = 
				SensorData_functionDAOFactory.createSensorData_functionDAOImplm();
		
		boolean flag = sensorData_functionDAOImplm.changeSensorData(sensorDataID,samplingTime, value1, value2, value3);
		
		if(flag == true){
			href =basePath+"servlet/SearchByTimeSensorDataServlet?startTime="+startTime+"&endTime="+endTime;
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/SearchByTimeSensorDataServlet?startTime="+startTime+"&endTime="+endTime;
			out.print("<script language='javascript'>alert('数据修改失败，请重新操作！');window.location.href='"
					+ href + "';</script>;");
		}
	    System.out.println(flag);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
