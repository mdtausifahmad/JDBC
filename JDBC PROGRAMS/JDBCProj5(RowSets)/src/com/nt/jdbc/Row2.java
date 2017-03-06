package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class Row2 {
	public static void main(String[] args) {
		OracleCachedRowSet crowset=null; 
		try{
		// Create RowSet obj
		crowset=new OracleCachedRowSet();
		//set values
		crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		crowset.setUsername("system");
		crowset.setPassword("manager");
		crowset.setCommand("select * from student");
		//execute Query
		crowset.execute();
		//process the RowSet
		while(crowset.next()){
			System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
		}
		
		Thread.sleep(40000); //pause DB during this period using services.msc
		
		//update opeations  (offline mode)
		crowset.setReadOnly(false);
		crowset.absolute(4);
		crowset.updateString(3,"vizag123");
		crowset.updateRow();
		
		System.out.println("Updating changes...");
		Thread.sleep(40000); //Activate DB s/w
		
		//revert the modications of Rowset to DB table
		crowset.acceptChanges();
	
		//Process the RowSet
		while(crowset.next()){
			System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
		}
	
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}//main
}//class

