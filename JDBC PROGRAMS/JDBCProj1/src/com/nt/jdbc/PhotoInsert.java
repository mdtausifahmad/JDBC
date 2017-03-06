package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsert {
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null;
		float salary=0.0f;
		String filePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		InputStream is=null; 
		File file=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("enter emp no:");
				no=sc.nextInt();
				System.out.println("Enter emp name:");
				name=sc.next();
				System.out.println("Enter emp salary");
				salary=sc.nextFloat();
				System.out.println("Enter img File path");
				filePath=sc.next();
			}//if
			// create InputStream pointing to file
			file=new File(filePath);
			 is=new FileInputStream(file);
	//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","manager");
			
	/*	 //register jdbc driver
			 Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");*/
			 
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement("insert into empall values(?,?,?,?)");
			if(ps!=null){
			//set values to Query params
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setFloat(3, salary);
			ps.setBinaryStream(4,is,file.length());
			}
			//execute the SQL Query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//process the results
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
