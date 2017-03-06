package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest1 {
 private static final String  CALL_FIRST_PRO1_QUERY="{call   first_pro1(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		//read inputs
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter number:");
				no=sc.nextInt();
			}//fi
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
           // create CallableStatement representing Pl/sql procedure
			if(con!=null)
				cs=con.prepareCall(CALL_FIRST_PRO1_QUERY);
			//register OUT params with JDBC types
			if(cs!=null)
			cs.registerOutParameter(2,Types.INTEGER);
			//set Values to IN params
			if(cs!=null)
				cs.setInt(1,no);
			//execute Pl/sql procedure
			if(cs!=null)
				cs.execute();
			//Gather results from OUT params
			if(cs!=null)
			result=cs.getInt(2);
			System.out.println("SQUARE:::"+result);
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

