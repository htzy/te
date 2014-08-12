package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.Sensor_functionDAO;
import com.monitor.dao.UnitType_functionDAO;
import com.monitor.factory.Sensor_functionDAOFactory;
import com.monitor.factory.UnitType_functionDAOFactory;

public class SearchSensorServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchSensorServlet() {
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

		doPost(request, response);
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

		
		//设置编码方式
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");
	    Sensor_functionDAO Sensor_functionDAOImpl = Sensor_functionDAOFactory.createSensor_functionDAOImplm();
		
		//从页面得到参数，并且访问数据库
		String embededIP = request.getParameter("embededIP");
		String nodeIP = request.getParameter("nodeIP");
		String channelNo = request.getParameter("channelNo");
		String[] str = Sensor_functionDAOImpl.searchSensor(embededIP, nodeIP, channelNo);
	    
		System.out.println(str);
		request.setAttribute("str",str);
		request.getRequestDispatcher("../jsp/sensor/showAllSensor.jsp").forward(request, response);
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
