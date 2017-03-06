package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
private static final  String  STUDENT_DETAILS_QRY="SELECT SNO,SNAME,SADD,RESUME FROM STUDENTALL WHERE SNO=?"; 
public static void main(String[] args) {
	Scanner sc=null;
	int no=0;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Reader reader=null;
	Writer writer =null;
	char[] buffer;
	int charsRead=0;
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
			if(rs.next())
		    	reader=rs.getCharacterStream(4);
			else{
				System.out.println("Record not found");
			}
		}//if
		//Create Dest file
		writer =new FileWriter("E:\\apps\\new_resume.txt");
	    //writer buffer based to read data from CLOB obj and to write data to DEST file	
		if(reader!=null && writer!=null){
			//buffer
			buffer=new char[1024];
			charsRead=0;
			while((charsRead=(reader.read(buffer)))!=-1){
				writer.write(buffer,0,charsRead);
			}//while
		}//if
		System.out.println("Resume retrived");
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
			if(reader!=null)
				reader.close();
			}//try
			catch(Exception e){
				e.printStackTrace();
			}//catch
		
		try{
			if(writer!=null)
				writer.close();
			}//try
			catch(Exception e){
				e.printStackTrace();
			}//catch
		
		
	}//finally	
 } 
}
