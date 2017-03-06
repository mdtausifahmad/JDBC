package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieve {
  public static void main(String[] args) {
	  Connection con=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  int no=0;
	  String name=null;
	  java.sql.Date sdob=null,sdoj=null;
	  java.util.Date udob=null,udoj=null;
	  SimpleDateFormat sdf=null;
	  String dob=null,doj=null;
	  
	  try{
		/*  //register driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
		  //register driver
		  Class.forName("com.mysql.jdbc.Driver");
		  //Establish the connection
		  con=DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");
		  // create PrpeareStatement obj
		  if(con!=null)
			  ps=con.prepareStatement("select *  from person_tab");
		  //Create ResultSet obj
		  if(ps!=null)
			  rs=ps.executeQuery();
		  //process the ResultSet 
		  if(rs!=null){
			  while(rs.next()){
				  no=rs.getInt(1);
				  name=rs.getString(2);
				  sdob=rs.getDate(3);
				  sdoj=rs.getDate(4);
				  //Convert java.sql.Date class objs to java.util.Date objs
				  udob=(java.util.Date)sdob;
				  udoj=(java.util.Date)sdoj;
				  //Convert java.util.Date class objs to String date value
				  sdf=new SimpleDateFormat("MMM-yyyy-dd");
				  dob=sdf.format(udob);
				  doj=sdf.format(udoj);
				  System.out.println(no+"   "+name+"   "+dob+"    "+doj);
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
			}//finally
   }//main
}//class
