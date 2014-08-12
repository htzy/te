package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorDataType_functionDAO;
import com.monitor.factory.SensorDataType_functionDAOFactory;

public class DelSensorDataTypeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DelSensorDataTypeServlet() {
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
		request.setCharacterEncoding("UTF-8");  //设置编码方式
		response.setContentType("text/html;charset=UTF-8");
		SensorDataType_functionDAO SensorDataType_functionDAOImpl = SensorDataType_functionDAOFactory.createSensorDataType_functionDAOImplm();
		
		
		PrintWriter out = response.getWriter();
		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String SensorDataTypeID = request.getParameter("sensorDataTypeID");
		
		System.out.println("SensorDataTypeID"+SensorDataTypeID);
		
		boolean flag = SensorDataType_functionDAOImpl.delSensorDataType(SensorDataTypeID);
		
		if(flag == true){
			href =basePath+"servlet/ShowAllSensorDataTypeServlet";
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/ShowAllSensorDataTypeServlet";
			System.out.println("href"+href);
			out.print("<script language='javascript'>alert('该数据受保护，不可删 ！');window.location.href='"
					+ href + "';</script>;");
			return ;
		}
		//System.out.println(flag);
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
