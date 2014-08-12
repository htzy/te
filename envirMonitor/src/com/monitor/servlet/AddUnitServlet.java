package com.monitor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.monitor.dao.Unit_functionDAO;
import com.monitor.factory.Unit_functionDAOFactory;
import com.monitor.java.Escape;

public class AddUnitServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public AddUnitServlet(){
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

		boolean flag = false;
		int i = 0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String href=""; 
		String path = request.getContextPath();
		String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

		//设置编码方式
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		
		Unit_functionDAO Unit_functionDAOImpl = Unit_functionDAOFactory.createUnit_functionDAOImplm();
		
		//准备上传的目录
		String uploadPath =  basePath + "images";
		System.out.println("上传的路径为："+uploadPath);
		java.io.File fpath = new java.io.File(uploadPath);
		if(!fpath.exists()){
			fpath.mkdir();
		}
		//实例化组件
		SmartUpload su = new SmartUpload();
		su.initialize(getServletConfig(), request, response);
		try{
			su.upload();
		}catch(Exception e){
			out.print("您选择的文件非法或者长度超限，请检查！");
			e.printStackTrace();
		}
		//得到关键字
		String[] unitName = su.getRequest().getParameterValues("unitName");
		String[] unitTypeName = su.getRequest().getParameterValues("unitTypeName");
		String[] fatherUnitName = su.getRequest().getParameterValues("fatherUnitName");
		String[] x = su.getRequest().getParameterValues("x");
		String[] y = su.getRequest().getParameterValues("y");
		String[] width = su.getRequest().getParameterValues("width");
		String[] height = su.getRequest().getParameterValues("height");
		String[] pictureName = su.getRequest().getParameterValues("pictureName"); 
		String[] thumbnailsName = su.getRequest().getParameterValues("thumbnailsName");; 
		
		//获得总记录条数，
		int sum = unitName.length;
		//units数组为增加的记录
		String[][] units= new String[sum][9];
		
		try{
			System.out.println("共接收到文件数为："+su.getFiles().getCount());
			System.out.println("共接收到的记录数为："+sum);
			for(i = 0; i < sum; i++){
				//unitName解码
				unitName[i] = Escape.unescape(unitName[i]);
				units[i][0] = unitName[i];
				units[i][1] = unitTypeName[i];
				units[i][2] = fatherUnitName[i];
				units[i][3] = x[i];         
				units[i][4] = y[i];
				units[i][5] = width[i];
				units[i][6] = height[i];
				
   				//开始取pictureName文件
   				File file = su.getFiles().getFile(i * 2);
    			//因为文件可以为空，所以需要判断是否为空,如果不为空的话，执行下面
    			if(!file.isMissing()){
    				Random rd = new Random();
    				Calendar cd = Calendar.getInstance();
    				//重新定义文件名，原来的pictureName为文件的后缀
    				pictureName[i] = "images/" + String.valueOf(cd.get(Calendar.YEAR))
    						+ String.valueOf(cd.get(Calendar.MONTH) + 1)
    						+ String.valueOf(cd.get(Calendar.DATE))
							+ String.valueOf(cd.get(Calendar.MINUTE))
							+ String.valueOf(cd.get(Calendar.SECOND))
							+ String.valueOf(rd.nextInt(100)) + "."
							+ pictureName[i];
    				//上传文件
    				System.out.println("上传文件名为：pictureName"+i+","+pictureName[i]);
    				file.saveAs(pictureName[i] , SmartUpload.SAVE_VIRTUAL);
   				}
    			//如果文件不存在，则往数据库中写null
    			else{
    				pictureName[i] = "null";
   				}
    			units[i][7] = pictureName[i];
    			//开始取thumbnailsName文件
   				file = su.getFiles().getFile(2 * i + 1);
    			//因为文件可以为空，所以需要判断是否为空,如果不为空的话，执行下面
    			if(!file.isMissing()){
    				Random rd = new Random();
    				Calendar cd = Calendar.getInstance();
    				thumbnailsName[i] ="images/" +  String.valueOf(cd.get(Calendar.YEAR))
    						+ String.valueOf(cd.get(Calendar.MONTH) + 1)
    						+ String.valueOf(cd.get(Calendar.DATE))
							+ String.valueOf(cd.get(Calendar.MINUTE))
							+ String.valueOf(cd.get(Calendar.SECOND))
							+ String.valueOf(rd.nextInt(100)) + "."
							+ thumbnailsName[i];
    				//上传文件
    				System.out.println("上传文件名为：thumbnailsName"+i+","+thumbnailsName[i]);
    				file.saveAs(thumbnailsName[i] , SmartUpload.SAVE_VIRTUAL);
    				
   				}
    			//如果文件不存在，则往数据库中写null
    			else{
    				thumbnailsName[i] = "null";
   				}
    			units[i][8] = thumbnailsName[i];
    				
			}
		}catch(Exception e){
			System.out.println("文件操作失败");
			e.printStackTrace();
		}
		

		flag = Unit_functionDAOImpl.addUnit(units, sum);
//		if(!units.isEmpty()){
//			flag = Unit_functionDAOImpl.addUnit(units);
//		}
		if(flag == true){
			
			//成功后跳转界面
			href =basePath+"servlet/ShowAllUnitServlet";
			out.print("<script language='javascript'>window.location.href='"
					+ href + "';</script>;");
		}else{
			href = basePath + "servlet/ShowAllUnitServlet";
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
	public void init() throws ServletException{
		// Put your code here
	}

}
