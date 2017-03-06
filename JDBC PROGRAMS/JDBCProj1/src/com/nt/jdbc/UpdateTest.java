package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student no:");
			no=sc.nextInt(); //gives 101
			System.out.println("Enter student name:");
			newName=sc.next(); //gives raja
			System.out.println("Enter studnent addrs");
			newAddrs=sc.next(); //gives hyd
		}//if
		//Convert input values as required for the SQL Query
		newName="'"+newName+"'"; //gives 'raja'
		newAddrs="'"+newAddrs+"'"; //gives 'hyd'
		//register jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
		//create Statement obj
		if(con!=null)
		  st=con.createStatement();
		//prepare SQL Query
		     //update student set sname='raja',sadd='hyd' where sno=101
	  query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
	  System.out.println(query);
	  //send and execte SQL Query in DB s/w
	  if(st!=null)
	    result=st.executeUpdate(query);
	  //process the result
	   if(result==0)
		   System.out.println("Record not found to update");
	   else
		   System.out.println("Record updated");
	    
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
