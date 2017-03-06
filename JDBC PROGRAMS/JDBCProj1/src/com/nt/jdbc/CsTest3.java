package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest3 {
private static final String AUTH_QRY="{ call p_Auth_pro(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
		
		//read inputs
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter username");
				user=sc.next(); //gives raja
				System.out.println("Enter password");
				pass=sc.next(); // gives rani
			}//if
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            //create CallableStatement obj
			if(con!=null)
				cs=con.prepareCall(AUTH_QRY);
			//register OUT params with jdbc types
			if(cs!=null)
				cs.registerOutParameter(3,Types.VARCHAR);
			//set values to IN params
			if(cs!=null){
				cs.setString(1,user);
				cs.setString(2,pass);
			}
			//execute pl/sql procedure
			if(cs!=null)
			  cs.execute();
			//gather result from OUT params
			if(cs!=null)
				result=cs.getString(3);
				
			//display results
			System.out.println(result);
			
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
