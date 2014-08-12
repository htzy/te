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

public class DelByIDSensorDataServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DelByIDSensorDataServlet() {
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
	 * @param session 
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("///////////////////////////////");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession hs=request.getSession(true);
		
		PrintWriter out = response.getWriter();
		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("+++++++++++++++++++++++++++");
		
		String sensorDataID = request.getParameter("sensorDataID");
		System.out.println("sensorDataID = "+sensorDataID);
		String startTime = (String)hs.getAttribute("startTime1");
		System.out.println("startTime = "+startTime);
		String endTime = (String)hs.getAttribute("endTime1");
		System.out.println("endTime = "+endTime);
		
		//实例化单元类
		SensorData_functionDAO sensorData_functionDAOImplm = SensorData_functionDAOFactory.createSensorData_functionDAOImplm();
		boolean flag = sensorData_functionDAOImplm.delSeneorData(sensorDataID);
		
		if(flag){
			href = basePath + "servlet/SearchByTimeSensorDataServlet?startTime="
					+startTime+"&endTime="+endTime;
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/SearchByTimeSensorDataServlet?startTime="
						+startTime+"&endTime="+endTime;
			System.out.println("href"+href);
			out.print("<script language='javascript'>alert('数据库操作失败，请重新操作！');window.location.href='"
					+ href + "';</script>;");
			return ;
		}
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
