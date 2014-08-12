package com.monitor.java;

import java.util.ArrayList;


import com.monitor.bean.unit;
import com.monitor.bean.extendBean.UnitHelp;
import com.monitor.bean.extendBean.UnitHelpExtend;
import com.monitor.dao.Unit_functionDAO;
import com.monitor.dao.Unit_functionDAOImplm;
import com.monitor.factory.Unit_functionDAOFactory;
import com.monitor.util.DBConnection;

public class ShowUnit {
	
	//static int circleLevel=0;
	public void loopCircle(int fartherID,int circleLevel,ArrayList listAdd){
		   circleLevel++;
		   int arri,unitID;
		   unit un;
		   Unit_functionDAO  Unit_functionDAOImplm=Unit_functionDAOFactory.createUnit_functionDAOImplm();
		 
		   //得到其对应的顶层单元
		   ArrayList list=Unit_functionDAOImplm.findTopUnit(1,fartherID);
		  if(list.size()!=0){  
		    for(arri=0;arri<list.size();arri++){
		      un=(unit)list.get(arri);
		    //得到一级节点的ID号
		      unitID=un.getUnitID();
		      
		      UnitHelp up=new UnitHelp();
		      up.setUnitName(un.getUnitName());
		      up.setLevel(circleLevel); 
		      up.setUnitID(un.getUnitID());
		      listAdd.add(up);
		      loopCircle(unitID,circleLevel,listAdd);
		    }
		  } 
	 }
	//对该链表进行排序
	public ArrayList  sortFarther(){	
		
		ArrayList list=new ArrayList();
		ArrayList listAdd=new ArrayList();
		loopCircle(0,0,listAdd);
		int fartherID=3000;
		int childID=101;
		int first=0;
		int arr[]={3000,101,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};//共有二十级
		for(int i=0;i<listAdd.size();i++){
			   UnitHelp up=(UnitHelp)listAdd.get(i);
			   UnitHelpExtend upex=new UnitHelpExtend();
			   
			   //得到级别
			   int level=up.getLevel();
			   String UnitName=up.getUnitName();
			   upex.setLevel(level);
			   upex.setUnitName(UnitName);
			   
			   //节点的ID号
			   upex.setUnitID(up.getUnitID());
			   
			   //第一个父节点
			   if(level==1){
				 //第一次父节点的值不变，从第二次为止，每次父节点的号增加一
			     first++; 
			     if(first==1){		     
			     }else{
			       fartherID++;
			     } 	
			     arr[level-1]=fartherID;			     
			   
			     /*添加链表 start*/
			     upex.setId(arr[level-1]);		   
			     upex.setParentId(0);
			     list.add(upex);
			     /*添加链表 end*/
			     
			   }else{ //不是父节点
			   
				   arr[level-1]=childID;
				   upex.setId(arr[level-1]);		   
				   upex.setParentId(arr[level-2]);
				   list.add(upex);	
				   
			       childID++;		   		   
			   }
			}
		return list;
	}

	
	public static void main(String[] args) {
		ShowUnit sh=new ShowUnit();
		ArrayList testList=new ArrayList();
		testList=sh.sortFarther();
		
		for(int i=0;i<testList.size();i++){
			   UnitHelpExtend up=(UnitHelpExtend)testList.get(i);
			   int level=up.getLevel();//得到级别
			   String unitNameID=up.getUnitName();
			   int id=up.getId();
			   int parentId=up.getParentId();
			   System.out.println("id="+id+"  parentId="+parentId+" level="+level+" unitName="+unitNameID);
			}
		
		
	}
	
}
