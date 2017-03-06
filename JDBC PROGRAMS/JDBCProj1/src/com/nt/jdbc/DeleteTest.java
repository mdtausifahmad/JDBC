package com.nt.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int no=0;
		String query=null;
		int result=0;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student no for deletion");
			no=sc.nextInt();
		}
		
		//register jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement obj
		if(con!=null)
		  st=con.createStatement();
		//prpeare SQL Query
		    //delete from student where sno=101
		query="delete from student where sno="+no;
		System.out.println(query);
		//send and execute SQL Query in Db s/w
		if(st!=null)
		 result=st.executeUpdate(query);
		//process the result
		if(result==0)
			System.out.println("Record not found to delete");
		else
			System.out.println(no+"Record  found for deletion");
		
		
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
