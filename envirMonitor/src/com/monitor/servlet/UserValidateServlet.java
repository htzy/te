package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monitor.dao.User_functionDAO;
import com.monitor.factory.User_functionDAOFactory;
import com.monitor.java.Escape;


public class UserValidateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserValidateServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)  //验证用户，如果是管理员，则进入管理界面，如果是普通用户，则进入查看界面。
			throws ServletException, IOException {
		
        request.setCharacterEncoding("UTF-8");  //设置编码方式
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String username=request.getParameter("username");//得到用户名
		String password=request.getParameter("password");//得到密码
		HttpSession session = request.getSession(true);
		User_functionDAO User_functionDAOImpl=User_functionDAOFactory.createUser_functionDAOImplm();
		int UserID=User_functionDAOImpl.findUser(username, password);//根据用户的名称和密码，查找该用户，如果存在该用户则返回该用户的ID号,如果为-1，则说明不存在该用户
		if(UserID==-1){//说明不存在该用户
			href = basePath+"system/login/login.jsp";
			out.print("<script language='javascript'>alert('该用户不存在，请重新登陆！');window.location.href='"
					+ href + "';</script>;");
		}else{ //存在该用户，则判断该用户是普通用户，还是管理员
			int roleID=User_functionDAOImpl.getUserRole(UserID);//根据用户的ID号，得到该用户的角色ID号,如果该用户角色不存在，返回-1
			if(roleID==-1){ //该用户角色不存在
				href = basePath+"system/login/login.jsp";
				out.print("<script language='javascript'>alert('不存在该用户的角色，请重新登陆！');window.location.href='"
						+ href + "';</script>;");
				
			}else if(roleID==1){ //该用户为管理员
				session.setAttribute("UserID", new Integer(UserID));// 把用户的ID号放到session中	
				href =basePath+ "system/default_accordion/main.html?username="+Escape.escape(username);  
				out.print("<script language='javascript'>window.location.href='"
						+ href + "';</script>;");
			}else if(roleID==2){ //该用户为普通用户
				session.setAttribute("UserID", new Integer(UserID));// 把用户的ID号放到session中	
				href = basePath+"system/layout_html/main.html?username="+Escape.escape(username);  
				out.print("<script language='javascript'>window.location.href='"
						+ href + "';</script>;");
			}else{ //其他未定义
			}
			
		}
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
