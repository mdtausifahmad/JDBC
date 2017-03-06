package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create PreparedStatement obj
		if(con!=null){
		  ps=con.prepareStatement("select empno,ename,job,sal from emp where sal=(select max(sal) from emp)");
  	}
		//send and execute SQL Query
		if(ps!=null)
		  rs=ps.executeQuery();
		
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
			if(ps!=null)
				ps.close();
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
