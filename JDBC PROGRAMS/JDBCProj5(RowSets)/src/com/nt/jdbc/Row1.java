package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class Row1 {

	public static void main(String[] args) {
		OracleJDBCRowSet jrowset=null;
			try{
		//Create RowSet obj
		 jrowset=new OracleJDBCRowSet();
		 //set properties
		jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		jrowset.setUsername("system");
		jrowset.setPassword("manager");
		jrowset.setCommand("select sno,sname,sadd from student");
		jrowset.execute(); //Execute the SQL Query
		//process the RowSet
		while(jrowset.next()){
			System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
		}
		
Thread.sleep(40000); //pause DB during this period using services.msc
		
	
		//close rowset
		jrowset.close();
		}//try
		catch(SQLException se){
				se.printStackTrace();
			}
	   catch(Exception e){
		   e.printStackTrace();
	   }
	}//main
}//class
