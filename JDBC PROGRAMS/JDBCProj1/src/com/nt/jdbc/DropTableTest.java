package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result=0;
		try{
		//register jdbc driver
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
				//create Statement obj
				if(con!=null)
				  st=con.createStatement();
				//send and execute SQL Query in Db s/w
				if(st!=null){
					result=st.executeUpdate("drop table mytab1");
				}
				if(result==0)
					System.out.println("Table not dropped");
				else
					System.out.println("Table  dropped");
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
