package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {
 private static final String  INSERT_QRY="INSERT INTO STUDENTALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
	    Reader reader=null;
	    File file=null;
	    int result=0;
		try{
		//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no:");
				no=sc.nextInt();
				System.out.println("Enter student name:");
				name=sc.next();
				System.out.println("Enter student addrs");
				addrs=sc.next();
				System.out.println("Enter resume path");
				resumePath=sc.next();
			   }//if
			//create Character stream pointing to resume doc
			file=new File(resumePath);
			reader=new FileReader(file);
		/*	//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");
			
			//create Prearedstatement obj
			if(con!=null)
			   ps=con.prepareStatement(INSERT_QRY);
			//set values to IN paramss
			if(ps!=null){
				ps.setInt(1,no);
				ps.setString(2, name);
				ps.setString(3, addrs);
				ps.setCharacterStream(4, reader,(int)file.length());
			}//if
			//execute the query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//process the Result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
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
