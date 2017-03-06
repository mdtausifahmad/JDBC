package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

 

public class DateInsert {
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		String pname=null,dob=null,doj=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null;
		java.sql.Date sdob=null,sdoj=null;
		Connection con=null;
		long ms=0;
		PreparedStatement ps=null;
		int result=0;
		try{
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter person id");
				pid=sc.nextInt();
				System.out.println("Enter person name ");
				pname=sc.next();
				System.out.println("Enter DOB (dd-MM-yyyy)");
				dob=sc.next();
				System.out.println("Enter DOJ (yyyy-MM-dd)");
				doj=sc.next();
			}//if
			//Convert String date vlaues to java.sql.Date class objs
			  //for DOB
			       //convert String date to java.util.Date class obj
	              sdf=new SimpleDateFormat("dd-MM-yyyy");
	              udob=sdf.parse(dob);
	              //convert java.util.Date class obj to java.sql.Date class obj
	              ms=udob.getTime();
	              sdob=new java.sql.Date(ms);
	         //for DOJ
	              sdoj=java.sql.Date.valueOf(doj);
	        /*   //register jdbc driver
	              Class.forName("oracle.jdbc.driver.OracleDriver");
	            //establish the connection
	              con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
                  
	              //register driver
	              Class.forName("com.mysql.jdbc.Driver");
	            //establish the connection
	              con=DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");
	              
	             //create PreparedStatement obj
	              if(con!=null)
	              ps=con.prepareStatement("insert into person_Tab values(?,?,?,?)");
	              //set input vlaues to  Query params
	              if(ps!=null){
	              ps.setInt(1,pid);
	              ps.setString(2, pname);
	              ps.setDate(3,sdob);
	              ps.setDate(4,sdoj);
	              }
	              //send and  execute SQL Query in Db s/w
	              if(ps!=null){
	            	 result=ps.executeUpdate(); 
	              }
	              //process the result
	              if(result==0){
	            	  System.out.println("record not inserted");
	              }
	              else{
	            	  System.out.println("Record inserted");
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
			//close jdbc objs and streams
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
			}//try
			catch(Exception e){
				e.printStackTrace();
			}//catch
		}//finally
	}//main
}//class
