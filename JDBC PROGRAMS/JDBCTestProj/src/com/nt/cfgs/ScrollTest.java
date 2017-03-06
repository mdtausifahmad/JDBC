package com.nt.cfgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollTest {
	private static final String  GET_STUDENTS_QRY="SELECT SNO,SNAME,SADD FROM STUDENT ";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
       try{
    	//register jdbc driver
    	   //Class.forName("oracle.jdbc.driver.OracleDriver");
    	   //Establish the connection
    	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
    	  //create Statement obj with type,mode values
    	  if(con!=null)
    		  ps=con.prepareStatement(GET_STUDENTS_QRY,
    				                                        ResultSet.TYPE_SCROLL_SENSITIVE,
    				                                        ResultSet.CONCUR_UPDATABLE);
    	     		  
    	  
    	   //create Scrollable ResultSet obj
    	  if(ps!=null)
    	  rs=ps.executeQuery(GET_STUDENTS_QRY);
    	  //display records top to botton
    	  if(rs!=null){
    		  System.out.println("Records (top---->Bottom)");
    		  while(rs.next()){
    			  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    		  }//while
    	  }//if
    	  
    	  //display records bottom to top
    	  if(rs!=null){
    		  System.out.println("Records (Bottom---->Top)");
    		  rs.afterLast();
    		  while(rs.previous()){
    			  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    		  }//while
    	  }//if
    	  //display record randomly
    	 rs.first();
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 rs.last();
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 rs.absolute(3);
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 rs.relative(2);
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 rs.relative(-4);
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 rs.absolute(-2);
    	 System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
    	 System.out.println("--------------------------------------------");
    	 


    	 
       }//try
       catch(SQLException se){
			se.printStackTrace();
		}
	/*	catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}*/
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
		}//finally
	}

}
