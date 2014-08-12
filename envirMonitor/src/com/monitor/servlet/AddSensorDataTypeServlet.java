package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.SensorDataType_functionDAO;
import com.monitor.factory.SensorDataType_functionDAOFactory;

public class  AddSensorDataTypeServlet extends HttpServlet {

	
	public AddSensorDataTypeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		// Just puts "destroy" string in log
		// Put your code here
	}

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
		
		boolean flag = false;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String href=""; 
		//request.getContextPath()应该是得到项目的名字
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

		//设置编码方式
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		
		SensorDataType_functionDAO SensorDataType_functionDAOImpl = SensorDataType_functionDAOFactory.createSensorDataType_functionDAOImplm();
		
		//从页面获取参数SensorDataTypeNameS
		String SensorDataTypeNames = request.getParameter("sensorDataTypeNames");
       //System.out.println("SensorDataTypeNameS = "+SensorDataTypeNameS);
		
        //System.out.println("SensorDataTypeNameS"+SensorDataTypeNameS);
		if(!SensorDataTypeNames.isEmpty()){
			flag = SensorDataType_functionDAOImpl.addSensorDataType(SensorDataTypeNames);
		}
		if(flag == true){
			href =basePath+"servlet/ShowAllSensorDataTypeServlet";
			
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/ShowAllSensorDataTypeServlet";
			
			out.print("<script language='javascript'>alert('数据增加失败，请重新操作！');window.location.href='"
					+ href + "';</script>;");
		}
		//System.out.println(flag);
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
