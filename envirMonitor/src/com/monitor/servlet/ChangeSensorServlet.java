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

public class ChangeSensorServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeSensorServlet() {
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

		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		Sensor_functionDAO Sensor_functionDAOImpl = Sensor_functionDAOFactory.createSensor_functionDAOImplm();
		
		PrintWriter out = response.getWriter();

		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//获取从页面发回的参数
		String sensorID = request.getParameter("sensorID");
		String newUnitName = request.getParameter("unitName");
		String newEmbededIP  = request.getParameter("embededIP");
		String newNodeIP = request.getParameter("nodeIP");
		String newChannelNo = request.getParameter("channelNo");
		String newPortType = request.getParameter("portType");
		String newSensorTypeName1 = request.getParameter("sensorTypeName1");
		String newSensorDataTypeName1 = request.getParameter("sensorDataTypeName1");
		String newMinValue1 = request.getParameter("minValue1");
		String newMaxValue1 = request.getParameter("maxValue1");
		String newSensorTypeName2 = request.getParameter("sensorTypeName2");
		String newSensorDataTypeName2 = request.getParameter("sensorDataTypeName2");
		String newMinValue2 = request.getParameter("minValue2");
		String newMaxValue2 = request.getParameter("maxValue2");
		String newSensorTypeName3 = request.getParameter("sensorTypeName3");
		String newSensorDataTypeName3 = request.getParameter("sensorDataTypeName3");
		String newMinValue3 = request.getParameter("minValue3");
		String newMaxValue3 = request.getParameter("maxValue3");
		String newX = request.getParameter("x");
		String newY = request.getParameter("y");
		String newWidth = request.getParameter("width");
		String newHeight = request.getParameter("height");
		
		boolean flag = Sensor_functionDAOImpl.changeSensor(sensorID, newUnitName, newEmbededIP, newNodeIP, newChannelNo, newPortType,
				newSensorTypeName1, newSensorDataTypeName1, newMinValue1, newMaxValue1, 
				newSensorTypeName2, newSensorDataTypeName2, newMinValue2, newMaxValue2,
				newSensorTypeName3, newSensorDataTypeName3, newMinValue3, newMaxValue3,
				newX, newY, newWidth, newHeight);
		
		if(flag == true){
			href =basePath+"servlet/ShowAllSensorServlet";
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/ShowAllSensorServlet";
			out.print("<script language='javascript'>alert('数据修改失败，请重新操作！');window.location.href='"
					+ href + "';</script>;");
		}
	    System.out.println(flag);
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
