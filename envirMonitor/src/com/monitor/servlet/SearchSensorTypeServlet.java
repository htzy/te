package com.monitor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorType_functionDAO;
import com.monitor.factory.SensorType_functionDAOFactory;

@SuppressWarnings("serial")
public class SearchSensorTypeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchSensorTypeServlet() {
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
		SensorType_functionDAO sensorType_functionDAOImpl = SensorType_functionDAOFactory.createSensorType_functionDAOImplm();
		
		//从页面得到参数，并且访问数据库
		String sensorTypeName = request.getParameter("SensorTypeName");
		String[] str = sensorType_functionDAOImpl.searchSensorType(sensorTypeName);
	    
		System.out.println(str);
		request.setAttribute("str",str);
		request.getRequestDispatcher("../jsp/sensorType/showAllSensorType.jsp").forward(request, response);
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
