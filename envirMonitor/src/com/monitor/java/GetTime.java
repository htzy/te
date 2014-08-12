package com.monitor.java;

import java.text.SimpleDateFormat;
import java.util.Date;
public class GetTime {//得到自己所需要的时间
	 public String getDetaileTime(){//得到系统的时间，年月日时分秒	 
		  Date date = new Date(); 
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=sdf.format(date.getTime());
		 // System.out.print(time);
		  return time;
	 }
	public String getTime(){//得到系统的时间，格式为年月日
		  Date date = new Date(); 
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  String time=sdf.format(date.getTime());
		  return time;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetTime time=new GetTime();
		String timeOne=time.getDetaileTime();
		String timeTwo=time.getTime();
		
		String timeTest=timeOne.substring(0, 16);
		System.out.print(timeTest+"------"+timeTwo+"---"+timeOne);
	}
}
