package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest2 {
 private static final String  CALL_P_GET_EMP_DETAILS_QUERY="{call P_GET_EMP_DETAILS(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		String name=null,desg=null;
		int salary=0;
			//read inputs
		try{
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter emp number:");
				no=sc.nextInt();
			}//fi
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
           // create CallableStatement representing Pl/sql procedure
			if(con!=null)
				cs=con.prepareCall(CALL_P_GET_EMP_DETAILS_QUERY);
			//register OUT params with JDBC types
			if(cs!=null){
			 cs.registerOutParameter(2,Types.VARCHAR);
			 cs.registerOutParameter(3,Types.VARCHAR);
			 cs.registerOutParameter(4,Types.INTEGER);
			}
			//set Values to IN params
			if(cs!=null)
				cs.setInt(1,no);
			//execute Pl/sql procedure
			if(cs!=null)
				cs.execute();
			//Gather results from OUT params
			if(cs!=null){
			 name=cs.getString(2);
			desg=cs.getString(3);
			salary=cs.getInt(4);
			}
			System.out.println("Name:::"+name);
			System.out.println("Desg:::"+desg);
			System.out.println("Salary:::"+salary);
			
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

