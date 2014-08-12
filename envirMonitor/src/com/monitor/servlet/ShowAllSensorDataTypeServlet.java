package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorDataType_functionDAO;
import com.monitor.dao.SensorDataType_functionDAOImplm;
import com.monitor.factory.SensorDataType_functionDAOFactory;

public class ShowAllSensorDataTypeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowAllSensorDataTypeServlet() {
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
		
		//设置编码方式
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		SensorDataType_functionDAO SensorDataType_functionDAOImpl = SensorDataType_functionDAOFactory.createSensorDataType_functionDAOImplm();
	
		String[] str = SensorDataType_functionDAOImpl.findSensorDataType();
	    System.out.println(str);
	    
	    request.setAttribute("str",str);
	    request.getRequestDispatcher("../jsp/sensorDataType/showAllSensorDataType.jsp").forward(request, response);
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
