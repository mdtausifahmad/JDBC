package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveTest {
	private static final String GET_STUDENT_DETAILS_QRY="select sno,sname,sadd from student";

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                   ResultSet.CONCUR_UPDATABLE);
			//send and excute SQL query
			if(st!=null)
				rs=st.executeQuery(GET_STUDENT_DETAILS_QRY);
			//process the ReusltSet
			if(rs!=null){
				while(rs.next()){
					if(cnt==1){
						System.out.println("Sleeping....");
						Thread.sleep(40000);
					}
					rs.refreshRow();
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3));
					cnt++;
				}//while
			}//if
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

