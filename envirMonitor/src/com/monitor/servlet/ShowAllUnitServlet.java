package com.monitor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.dao.UnitType_functionDAO;
import com.monitor.dao.Unit_functionDAO;
import com.monitor.factory.UnitType_functionDAOFactory;
import com.monitor.factory.Unit_functionDAOFactory;

public class ShowAllUnitServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public ShowAllUnitServlet(){
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public
			void
			doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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
	public
			void
			doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//设置编码方式
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		
		Unit_functionDAO Unit_functionDAOImpl = Unit_functionDAOFactory.createUnit_functionDAOImplm();
		UnitType_functionDAO UnitType_functionDAOImpl = UnitType_functionDAOFactory.createUnitType_functionDAOImplm();
		
		//dataForAll用于showAllUnit.jsp中显示所有信息
		String[] dataForAll = Unit_functionDAOImpl.findUnit();
		
		int type = 1;
		//dataForFatherUnitID用于showAllUnit.jsp中显示所有的父节点信息，用于支持修改,返回类型为json
		String dataForFatherUnitID = Unit_functionDAOImpl.findUnitID_Name(type);
		
		type = 2;
		//optionsForFather用于addUnit.jsp中显示所有的父节点信息，用于下拉框,返回类型为option
		String optionsForFather = Unit_functionDAOImpl.findUnitID_Name(type);
		
		//dataForUnitTypeID用于showAllUnit.jsp中显示所有的单元类型信息，用于支持修改
		String[] temp = UnitType_functionDAOImpl.findUnitType();
		
		//optionForUnitType用于addUnit.jsp中显示单元类型信息，用于下拉框，
		String optionsForUnitType = "";
		
		//临时变量
		String str = "";
		//将字符串数组temp转换成对应的option数据
		for(int i = 0; i < temp.length; i += 2){
			//<option value=''>请选择功能</option>
			str = " <option value='" + temp[i] + "'>" + temp[i+1] + "</option>";
			optionsForUnitType += str;
		}
		
		//将字符串数组temp转换成对应的json数据
		String dataForUnitTypeID = "{\"list\":[";
		
		for(int i = 0; i < temp.length; i += 2){
			str = "{\"value\":\"" + temp[i] + "\",\"key\":\"" + temp[i+1] + "\"},";
			// 拼接json型数据
			dataForUnitTypeID += str;
		}
		//去掉最后一个多余的“,”
		dataForUnitTypeID = dataForUnitTypeID.substring(0, dataForUnitTypeID.length()-1);
		dataForUnitTypeID = dataForUnitTypeID + "]}";
		
		request.setAttribute("optionsForFather",optionsForFather);
		request.setAttribute("optionsForUnitType",optionsForUnitType);
		
	    request.setAttribute("dataForAll",dataForAll);
	    request.setAttribute("dataForUnitTypeID",dataForUnitTypeID);
	    request.setAttribute("dataForFatherUnitID",dataForFatherUnitID);
	    
		request.getRequestDispatcher("../jsp/unit/showAllUnit.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException{
		// Put your code here
	}

}
