package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PsBatchTest {
 private static final String INSERT_STUDENT="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
        PreparedStatement ps=null;
        int result[]=null;
        int sum=0;
		try{
		//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
        //create PreparedStatement 
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENT);
		//add multiple sets of values to query params
			if(ps!=null){
				//add multiple query params to batch
				  ps.setInt(1,100);
				  ps.setString(2,"raja");
				  ps.setString(3,"hyd");
				  ps.addBatch(); //adds 1st set of value to Batch

				  ps.setInt(1,200);
				  ps.setString(2,"ravi");
				  ps.setString(3,"vizag");
				  ps.addBatch(); //adds 2nd set of value to Batch
			}//if
			//execute the batch
			if(ps!=null){
				result=ps.executeBatch();
			}
			//process the results
			for(int i=0;i<result.length;++i){
				sum=sum+result[i];
			}
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
		}//finally
	}//main
}//class
