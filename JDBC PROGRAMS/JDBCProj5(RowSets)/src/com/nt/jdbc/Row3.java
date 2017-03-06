package com.nt.jdbc;

import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class Row3 {
	public static void main(String[] args){
		OracleWebRowSet wrowset=null;
		FileWriter writer=null;
		StringWriter sw=null;
		try{
		//create RowSet obj
		 wrowset=new OracleWebRowSet();
		//set values
		wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		wrowset.setUsername("system");
		wrowset.setPassword("manager");
		wrowset.setCommand("select * from student");
		wrowset.execute();
		//process the RowSet and write records to xml file
		writer=new FileWriter("d:/student.xml");
		wrowset.writeXml(writer);
		//write to String...
		 sw=new StringWriter();
		wrowset.writeXml(sw);
		System.out.println(sw);
		
		//close rowset
		wrowset.close();
    	}//try
	catch(SQLException se){
		se.printStackTrace();
	}
   catch(Exception e){
	   e.printStackTrace();
   }
	}//main
}//class
