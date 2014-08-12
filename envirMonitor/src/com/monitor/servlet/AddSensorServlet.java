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

public class AddSensorServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddSensorServlet() {
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

		boolean flag = false;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

		//设置编码方式
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		
		Sensor_functionDAO Sensor_functionDAOImpl = Sensor_functionDAOFactory.createSensor_functionDAOImplm();
		
		//从页面获取参数unitTypeNameS
		/*
		String unitNames = request.getParameter("unitNames");
		String embededIPs  = request.getParameter("embededIPs");
		String nodeIPs = request.getParameter("nodeIPs");
		String channelNos = request.getParameter("channelNos");
		String portTypes = request.getParameter("portTypes");
		String sensorTypeName1s = request.getParameter("sensorTypeName1s");
		String sensorDataTypeName1s = request.getParameter("sensorDataTypeName1s");
		String minValue1s = request.getParameter("minValue1s");
		String maxValue1s = request.getParameter("maxValue1s");
		String sensorTypeName2s = request.getParameter("sensorTypeName2s");
		String sensorDataTypeName2s = request.getParameter("sensorDataTypeName2s");
		String minValue2s = request.getParameter("minValue2s");
		String maxValue2s = request.getParameter("maxValue2s");
		String sensorTypeName3s = request.getParameter("sensorTypeName3s");
		String sensorDataTypeName3s = request.getParameter("sensorDataTypeName3s");
		String minValue3s = request.getParameter("minValue3s");
		String maxValue3s = request.getParameter("maxValue3s");
		String xs = request.getParameter("xs");
		String ys = request.getParameter("ys");
		String widths = request.getParameter("widths");
		String heights = request.getParameter("heights");
		*/
//		System.out.println("unitTypeNameS = "+unitTypeNameS);
		
//		System.out.println("unitTypeNameS"+unitTypeNameS);
		//flag = Sensor_functionDAOImpl.addSensor(unitNames, embededIPs, nodeIPs, channelNos, portTypes,
			//	sensorTypeName1s, sensorDataTypeName1s, minValue1s, maxValue1s, sensorTypeName2s, sensorDataTypeName2s,
				//minValue2s, maxValue2s, sensorTypeName3s, sensorDataTypeName3s, minValue3s, maxValue3s,
				//xs, ys, widths, heights);
		String sensorAll = request.getParameter("sensorAll");
		String[] sensorAllArray = sensorAll.split(",");////////////取变量名，传过去后用循环接受
		flag = Sensor_functionDAOImpl.addSensor(sensorAllArray);
		System.out.println(sensorAll);
		
		if(flag == true){
			href =basePath+"servlet/ShowAllSensorServlet";
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/ShowAllSensorServlet";
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
