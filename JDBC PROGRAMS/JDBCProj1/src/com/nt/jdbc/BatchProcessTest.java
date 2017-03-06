package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create STatement obj
			if(con!=null)
			  st=con.createStatement();
			//Add queries to the batch
			if(st!=null){
				st.addBatch("insert into student values(3452,'ramesh','hyd')");
				st.addBatch("update student set sadd='delhi2' where sno>=500");
				st.addBatch("delete from student where sadd='mumbai'");
			}
			
			
			//execute the batch
			if(st!=null){
				result=st.executeBatch();
			}
			//process the results
			if(result!=null){
				for(int i=0;i<result.length;++i)
				  sum=sum+result[i];
			}//if
			System.out.println("No.of records that are effected"+sum);
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
