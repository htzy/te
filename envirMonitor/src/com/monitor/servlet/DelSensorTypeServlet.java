package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorType_functionDAO;
import com.monitor.factory.SensorType_functionDAOFactory;

@SuppressWarnings("serial")
public class DelSensorTypeServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public DelSensorTypeServlet(){
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy(){
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public
			void
			doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public
			void
			doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8"); // 设置编码方式
		response.setContentType("text/html;charset=UTF-8");// 使客户端浏览器气氛不同种类的数据
		SensorType_functionDAO SensorType_functionDAOImpl = SensorType_functionDAOFactory
				.createSensorType_functionDAOImplm();

		PrintWriter out = response.getWriter();
		String href = "";
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		String sensorTypeID = request.getParameter("SensorTypeID");

		boolean flag = SensorType_functionDAOImpl.delSensorType(sensorTypeID);

		if(flag == true){
			href = basePath + "servlet/ShowAllSensorTypeServlet";
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
			out.flush();
			out.close();
		}else{
			href = basePath + "servlet/ShowAllSensorTypeServlet";
			System.out.println("href" + href);
			out.print("<script language='javascript'>alert('该数据受保护，不可删 ！');window.location.href='"
					+ href + "';</script>;");
			out.flush();
			out.close();
			return;
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException{
		// Put your code here
	}

}
