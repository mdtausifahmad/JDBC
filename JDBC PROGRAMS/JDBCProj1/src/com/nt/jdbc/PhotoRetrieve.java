package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		int bytesRead=0;
		byte buffer[]=new byte[4096];
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter eemployee no:");
				no=sc.nextInt();
			}//if
			
			//register jdbc driver
			 Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");
					
		/*	//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","manager");*/
			
			//	create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement("select * from empall where empno=?");
			// set Values to IN params
			if(ps!=null)
				ps.setInt(1,no);
			//execut Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet
			if(rs!=null){
    	 		if(rs.next())
		     	 is=rs.getBinaryStream(4);
     			else
	        		System.out.println("Record not found");
			}//if
			//create output stream pointing to dest file
			os=new FileOutputStream("e:\\apps\\new_img.jpg");
			if(is!=null && os!=null){
			//perform buffer based file copy operations through streams
		while((bytesRead=(is.read(buffer)))!=-1){
				os.write(buffer,0,bytesRead);
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
			//close jdbc objs
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
				}
				catch(Exception e){
					e.printStackTrace();
				}
				try{
					if(is!=null)
						is.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				try{
					if(os!=null)
						os.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
