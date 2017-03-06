package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
	  Scanner sc=null;
	  String query=null;
	  Connection con=null;
	  Statement st=null;
	  boolean flag=false;
	  ResultSet rs=null;
	  int count=0;
		try{
			//read inputs		
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter SQL Query (select or non-select)");
				query=sc.nextLine();
			}//if
			//register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement obj
			if(con!=null)
			  st=con.createStatement();
			//send and execute SQL Query 
			if(st!=null)
				flag=st.execute(query);
			//process the Result
			if(flag){
				System.out.println("Select SQL Query is executed");
				if(st!=null)
				  rs=st.getResultSet();
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
			else{
				System.out.println("Non-Select SQL Query is executed");
				count=st.getUpdateCount();
				System.out.println("No.of records tha are updated"+count);
			}//else
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
			try{
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception e){
				e.printStackTrace();
			}
		}//finally

	}//main
}//class
