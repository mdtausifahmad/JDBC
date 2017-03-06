package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
	//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student no:");
			no=sc.nextInt();  //gives 456
			System.out.println("Enter student name:");
			name=sc.next(); //gives raja
			System.out.println("Enter student addrs:");
			addrs=sc.next(); // gives hyd
		}
		//convert input vlaues as required fomr SQL Query
		name="'"+name+"'";//gives 'raja'
		addrs="'"+addrs+"'"; //gives 'hyd'
		
		//register jdbc driver
				Class.forName("oracle.jdbc.OracleDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//create Statement obj
				if(con!=null)
				  st=con.createStatement();
				//prpeare SQL Query
				  //insert into student values(456,'raja','hyd')
			query="insert into student values("+no+","+name+","+addrs+")";
			System.out.println(query);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				result=st.executeUpdate(query);
			//process the result
			if(result==0)
				System.out.println("Record not insered");
			else
				System.out.println("Record inserted");
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
