package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class FlexTest {
	private static final String  GET_STUDENTS_QRY="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		 
		InputStream is=null;
		Properties props=null;
		String driver=null,url=null,user=null,pwd=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//locate properties
			is=new FileInputStream("src/com/nt/commons/DBDetails.properties");
			//Load jdbc properties of properties into java.util.Properties class obj
			props=new Properties();
			props.load(is);
			// get jdbc properites from properties file
			driver=props.getProperty("jdbc.driver");
			url=props.getProperty("jdbc.url");
			user=props.getProperty("db.user");
			pwd=props.getProperty("db.pwd");
			//register jdbc driver
			Class.forName(driver);
			//estalibhs the connection
			con=DriverManager.getConnection(url,user,pwd);
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//execute SQL Query
			if(st!=null)
				rs=st.executeQuery(GET_STUDENTS_QRY);
			//gather results and process Results
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
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
		}//finally
	}//main
}//class
