package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLApp {
 private static final String GET_PRODUCTS="SELECT PID,PNAME,QTY FROM PRODUCT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//register driver
			Class.forName("org.postgresql.Driver");
			//Establish the conenction
			//con=DriverManager.getConnection("jdbc:postgresql:ntja94db","postgres","root");
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntja94db","postgres","root");
			//create statement obj
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query
			if(st!=null)
				rs=st.executeQuery(GET_PRODUCTS);
			//process the ResultSet 
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getFloat(3));
				}//while
			}//if
          if(flag==false)
        	  System.out.println("No Records found");
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
