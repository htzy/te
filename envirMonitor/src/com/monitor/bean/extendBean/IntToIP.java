package com.monitor.bean.extendBean;

public class IntToIP {
	public String IP(int a){
		String sb="";
		int b=(a>>0)&0xff;
		sb="."+b;
		b=(a>>8)&0xff;
		sb="."+b+sb;
		b=(a>>16)&0xff;
		sb="."+b+sb;
		b=(a>>24)&0xff;
		sb=b+sb;
		return sb;
	}

}
