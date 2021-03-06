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

public class LoadAboutSensorServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadAboutSensorServlet() {
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
		String[][] strSelect = null; 
		strSelect = Sensor_functionDAOImpl.loadAboutSensor();
		
		request.setAttribute("strSelect", strSelect);
		request.getRequestDispatcher("../jsp/sensor/popup/addSensor.jsp").forward(request, response);
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
