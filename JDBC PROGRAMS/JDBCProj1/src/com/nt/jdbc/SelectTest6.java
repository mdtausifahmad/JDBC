package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		//register jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement obj
		if(con!=null)
		  st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
		  rs=st.executeQuery("select, empno,ename,job,sal from emp where sal=(select max(sal) from emp)");
		//process the ResultSet
		if(rs!=null){
			while(rs.next()){
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
				flag=true;
			}//while
		}//if
		
		if(flag==false){
			System.out.println("Records not found");
		}
	}//try
	catch(SQLException se){
		se.printStackTrace();
	}
  catch(ClassNotFoundException cnf ){
	  cnf.printStackTrace();
  }
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		//close jdbc objs
		try{
		  if(rs!=null)
			  rs.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		try{
			if(st!=null)
				st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
		try{
			if(con!=null)
				con.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
	}//finally
 }//main
}//class
