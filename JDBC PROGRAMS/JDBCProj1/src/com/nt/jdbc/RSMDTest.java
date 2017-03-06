package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RSMDTest{
	private static final String GET_STUDENT_DETAILS_QRY="select sno,sname from student";

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//send and excute SQL query
			if(st!=null)
				rs=st.executeQuery(GET_STUDENT_DETAILS_QRY);
			//create ResultSetMetaData obj
             if(rs!=null)
            	 rsmd=rs.getMetaData();
             //Display col names
             if(rsmd!=null)
               colCount=rsmd.getColumnCount();
             //display col names
             if(rsmd!=null){
            	 for(int i=1;i<=colCount;++i){
            		System.out.print(rsmd.getColumnLabel(i)+"     "); 
            	 }
             }
             System.out.println();
			//process the ReusltSet
			if(rs!=null){
				while(rs.next()){
					for(int i=1;i<=colCount;++i){
						System.out.print(rs.getString(i)+"    ");
					}//for
					System.out.println();
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

