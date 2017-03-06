package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferCustomers {
	private static final String   GET_CUSTOMERS_QRY=
			               "SELECT CID,CNAME,BALANCE FROM CUSTOMER";
	private static  final String INSERT_CUSTOMER_QRY=
			                           "INSERT INTO BANK_CUSTOMER VALUES(?,?,?)";
	
	public static void main(String[] args) {
		Connection oraCon=null;
		Connection mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int cno=0;
		String name=null;
		float bal=0.0f;
		try{
			//register drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//establish the  connections
			oraCon=DriverManager
			       .getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=
			     DriverManager.getConnection("jdbc:mysql:///ntaj94db1","root","root");
			//create Statement objs
			if(mysqlCon!=null)
				st=mysqlCon.createStatement();
			if(oraCon!=null)
				ps=oraCon.prepareStatement(INSERT_CUSTOMER_QRY);
			//send and execute SQL Query in Mysql Db s/w (to get all customers)
			if(st!=null)
				rs=st.executeQuery(GET_CUSTOMERS_QRY);
			//copy the Records of ResutlSet(mysql) to oracle Db  table
			if(rs!=null){
				while(rs.next()){
					//get each record from mysql db table
					cno=rs.getInt(1);
					name=rs.getString(2);
					bal=rs.getFloat(3);
					//set each record to oracle DB table
					ps.setInt(1,cno);
					ps.setString(2,name);
					ps.setFloat(3,bal);
					ps.executeUpdate();
				}//while
			}//if
			System.out.println("Customers are transfered");
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
				if(ps!=null)
					ps.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				
			try{
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
