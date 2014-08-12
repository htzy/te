package com.monitor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monitor.dao.SensorData_functionDAO;
import com.monitor.factory.SensorData_functionDAOFactory;

public class SearchByTimeSensorDataServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchByTimeSensorDataServlet() {
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
		System.out.println("/////////////////////////");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		
		//从页面获取参数
		HttpSession session = request.getSession();
		
		String startTime = request.getParameter("startTime");
		session.setAttribute("startTime1", startTime);
		System.out.println("startTime = "+startTime);
		String endTime = request.getParameter("endTime");
		session.setAttribute("endTime1", endTime);
		System.out.println("endTime = "+endTime);
		
		SensorData_functionDAO sensorData_functionDAOImplm = 
				     SensorData_functionDAOFactory.createSensorData_functionDAOImplm();
		String[] str = sensorData_functionDAOImplm.searchSensorData(startTime, endTime);
		
//		System.out.println(rs);
		request.setAttribute("str",str);
		request.getRequestDispatcher("../jsp/sensorData/showAllSensorData.jsp").forward(request, response);
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
