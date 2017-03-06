package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null; 
		int sno=0;
		String sname=null,sadd=null;
		int result=0;
		try{
	  //read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student count");
			count=sc.nextInt();
		}//if
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//estalbihs the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		//create Pareparedstatement obj having pre-compiled SQL Query
		if(con!=null)
		   ps=con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?)");
		 //read multiple students
		if(ps!=null){
		  for(int i=1;i<=count;++i){
			  System.out.println("Enter "+i+" student details");
			  System.out.println("Enter no:");
			  sno=sc.nextInt(); //gives 101
			  System.out.println("Enter name:");
			  sname=sc.next(); //gives raja
			  System.out.println("Enter Address:");
			  sadd=sc.next(); //gives hyd
			  //set these values to Query params
			  ps.setInt(1,sno);
			  ps.setString(2,sname);
			  ps.setString(3,sadd);
			  //execute the SQL Query
			  result=ps.executeUpdate();
			  //process the results
			  if(result==0)
				  System.out.println(i+" Record not inserted");
			  else
				  System.out.println(i+" Record  inserted");
		  }//for
	  }//if
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
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally		
	}//main
}//class
