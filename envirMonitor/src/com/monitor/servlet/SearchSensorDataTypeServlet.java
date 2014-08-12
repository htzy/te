package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorDataType_functionDAO;
import com.monitor.factory.SensorDataType_functionDAOFactory;

public class SearchSensorDataTypeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchSensorDataTypeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
		// Just puts "destroy" string in log
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
		
		//从页面得到参数，并且访问数据库
		//改动下面第二条语句大写S改成了小写。因为showAllSensorDataType提交上的name是小写
		String SensorDataTypeID = request.getParameter("sensorDataTypeID");
		String SensorDataTypeName = request.getParameter("sensorDataTypeName");
		
		//System.out.print("查询的输入\n"+SensorDataTypeName) ;
		String[] str = SensorDataType_functionDAOImpl.searchSensorDataType(SensorDataTypeID, SensorDataTypeName);
		
		System.out.println(str);
		//System.out.print("结果集长度\n"+str.length) ;
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
