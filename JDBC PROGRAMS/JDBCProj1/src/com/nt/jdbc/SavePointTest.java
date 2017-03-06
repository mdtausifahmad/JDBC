package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
	     Savepoint sp=null;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//Begin Tx
			 if(con!=null)
				 con.setAutoCommit(false);
			  //execute queries
			   //query1
			   if(st!=null)
				   st.executeUpdate("insert into student values(587,'ramesh','hyd')");
			   //set save point
			   if(con!=null)
				 sp=con.setSavepoint("mysp");
			   //query2
			   if(st!=null)
				   st.executeUpdate("update student set sadd='hyd' where sno=200");
			   if(con!=null){
				   con.rollback(sp);
				   con.commit();
			   }
			   System.out.println("query1 is committed and query2 is rolleback");
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs and streams
			
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
