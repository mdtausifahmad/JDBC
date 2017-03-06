package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve1 {
private static final  String  STUDENT_DETAILS_QRY="SELECT SNO,SNAME,SADD,RESUME FROM STUDENTALL WHERE SNO=?"; 
public static void main(String[] args) {
	Scanner sc=null;
	int no=0;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Clob clob=null;
	InputStream is=null;
	OutputStream os=null;
	byte[] buffer;
	int bytesRead=0;
	//read inputs
	try{
	sc=new Scanner(System.in);
	 if(sc!=null){
		System.out.println("Enter student no:"); 
		no=sc.nextInt();
	 }//if
	 
	//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	    //create PreparedStatement obj
		if(con!=null)
			ps=con.prepareStatement(STUDENT_DETAILS_QRY);
		//set input values to SQL Query
		if(ps!=null){
		 ps.setInt(1,no);
		}
		//execute the Query
		if(ps!=null){
			rs=ps.executeQuery();
		}
		//process the ResultSet
		if(rs!=null){
			if(rs.next()){
				clob=rs.getClob(4);
			}
			else{
				System.out.println("Record not found");
			}//else
		}//if
		if(clob!=null){
			is=clob.getAsciiStream();
		}
		//create output stream for Dest file
		os=new FileOutputStream("E:\\apps\\new_resume1.txt");
		if(is!=null && os!=null){
		//buffer base logic
			buffer=new byte[4096];
		while((bytesRead=is.read(buffer))!=-1){
			os.write(buffer,0,bytesRead);
		 }//while
		}
		System.out.println("Resume retrieved");
		
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
		try{
		if(sc!=null)
			sc.close();
		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch
		try{
			if(is!=null)
				is.close();
			}//try
			catch(Exception e){
				e.printStackTrace();
			}//catch
		
		try{
			if(os!=null)
				os.close();
			}//try
			catch(Exception e){
				e.printStackTrace();
			}//catch
		
		
	}//finally	
 } 
}
