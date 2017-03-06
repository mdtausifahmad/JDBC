package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsFxTest2 {
private static  final String
             FX_STUDENT_FOR_DELETION_QRY="{?=call FX_GET_STUDENT_FOR_DELETION(?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		int no=0;
		CallableStatement cs=null;
		ResultSet rs=null;
		int result=0;
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
			//create CallableStatement obj
			if(con!=null)
			  cs=con.prepareCall(FX_STUDENT_FOR_DELETION_QRY);
			//register OUT params with jdbc types
			if(cs!=null){
				cs.registerOutParameter(1,OracleTypes.CURSOR);
				cs.registerOutParameter(3,Types.INTEGER);
			}
			//set IN param values
			if(cs!=null){
				cs.setInt(2,no);
			}
			//call pl/sql Function
			if(cs!=null)
			  cs.execute();
			//gather results
			if(cs!=null){
				rs=(ResultSet)cs.getObject(1);
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
			
			if(cs!=null)
			result=cs.getInt(3);
			
			if(result==0)
				System.out.println("Record not deleted");
			else
				System.out.println("Record deleted");
			
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
				if(cs!=null)
					cs.close();
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
		}//finally
	}//main
}//class
