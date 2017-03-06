package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableTest {
  private static final String GET_STUDENTS_QRY="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null; 
		Statement st=null;
		ResultSet rs=null;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
			  st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					                                     ResultSet.CONCUR_UPDATABLE);
			//send SQL Query to DB s/w
			if(st!=null)
				rs=st.executeQuery(GET_STUDENTS_QRY);
			//read operations
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
		
		/*	//insert operation
			if(rs!=null){
				rs.moveToInsertRow(); //Empty Row
				rs.updateInt(1,3001);
				rs.updateString(2,"rajesh");
				rs.updateString(3,"hyd");
				rs.insertRow();
				System.out.println("Record inserted");
			}*/
/*			//update operation
			if(rs!=null){
				rs.absolute(3);
				rs.updateString(3,"delhi");
				rs.updateRow();
				System.out.println("Record updated");
			}
*/			//delete operation
			if(rs!=null){
				rs.absolute(3);
				rs.deleteRow();
				System.out.println("Record deleted");
			}
			
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
